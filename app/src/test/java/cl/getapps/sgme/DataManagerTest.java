package cl.getapps.sgme;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.data.remote.SgmeService;
import rx.Observable;
import rx.observers.TestSubscriber;
import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.local.DatabaseHelper;
import cl.getapps.sgme.data.local.PreferencesHelper;
import cl.getapps.sgme.test.common.TestDataFactory;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock
    SgmeService mMockSgmeService;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockSgmeService, mMockPreferencesHelper,
                mMockDatabaseHelper);
    }

    @Test
    public void syncRibotsEmitsValues() {
        List<Menu> menus = Arrays.asList(TestDataFactory.makeRibot("r1"),
                TestDataFactory.makeRibot("r2"));
        stubSyncRibotsHelperCalls(menus);

        TestSubscriber<Menu> result = new TestSubscriber<>();
        mDataManager.syncMenu().subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(menus);
    }

    @Test
    public void syncRibotsCallsApiAndDatabase() {
        List<Menu> menus = Arrays.asList(TestDataFactory.makeRibot("r1"),
                TestDataFactory.makeRibot("r2"));
        stubSyncRibotsHelperCalls(menus);

        mDataManager.syncMenu().subscribe();
        // Verify right calls to helper methods
        verify(mMockSgmeService).getRibots();
        verify(mMockDatabaseHelper).setMenus(menus);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
        when(mMockSgmeService.getRibots())
                .thenReturn(Observable.<List<Menu>>error(new RuntimeException()));

        mDataManager.syncMenu().subscribe(new TestSubscriber<Menu>());
        // Verify right calls to helper methods
        verify(mMockSgmeService).getRibots();
        verify(mMockDatabaseHelper, never()).setMenus(anyListOf(Menu.class));
    }

    private void stubSyncRibotsHelperCalls(List<Menu> menus) {
        // Stub calls to the ribot service and database helper.
        when(mMockSgmeService.getRibots())
                .thenReturn(Observable.just(menus));
        when(mMockDatabaseHelper.setMenus(menus))
                .thenReturn(Observable.from(menus));
    }

}
