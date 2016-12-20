package cl.getapps.sgme;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.test.common.TestDataFactory;
import cl.getapps.sgme.ui.main.MainMvpView;
import cl.getapps.sgme.ui.main.MainPresenter;
import cl.getapps.sgme.util.RxSchedulersOverrideRule;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mMockMainMvpView;
    @Mock DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
        List<Menu> menus = TestDataFactory.makeListRibots(10);
        when(mMockDataManager.getMenus())
                .thenReturn(Observable.just(menus));

        mMainPresenter.loadMenus();
        verify(mMockMainMvpView).showMenus(menus);
        verify(mMockMainMvpView, never()).showMenusVacios();
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
        when(mMockDataManager.getMenus())
                .thenReturn(Observable.just(Collections.<Menu>emptyList()));

        mMainPresenter.loadMenus();
        verify(mMockMainMvpView).showMenusVacios();
        verify(mMockMainMvpView, never()).showMenus(anyListOf(Menu.class));
        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
        when(mMockDataManager.getMenus())
                .thenReturn(Observable.<List<Menu>>error(new RuntimeException()));

        mMainPresenter.loadMenus();
        verify(mMockMainMvpView).showError();
        verify(mMockMainMvpView, never()).showMenusVacios();
        verify(mMockMainMvpView, never()).showMenus(anyListOf(Menu.class));
    }

}