package cl.getapps.sgme;

import android.database.Cursor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import cl.getapps.sgme.data.model.Menu;
import rx.observers.TestSubscriber;
import cl.getapps.sgme.data.local.DatabaseHelper;
import cl.getapps.sgme.data.local.Db;
import cl.getapps.sgme.data.local.DbOpenHelper;
import cl.getapps.sgme.test.common.TestDataFactory;
import cl.getapps.sgme.util.DefaultConfig;
import cl.getapps.sgme.util.RxSchedulersOverrideRule;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private final DatabaseHelper mDatabaseHelper =
            new DatabaseHelper(new DbOpenHelper(RuntimeEnvironment.application));

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Test
    public void setMenus() {
        Menu menu1 = TestDataFactory.makeRibot("r1");
        Menu menu2 = TestDataFactory.makeRibot("r2");
        List<Menu> menus = Arrays.asList(menu1, menu2);

        TestSubscriber<Menu> result = new TestSubscriber<>();
        mDatabaseHelper.setMenus(menus).subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(menus);

        Cursor cursor = mDatabaseHelper.getBriteDb()
                .query("SELECT * FROM " + Db.MenuTable.TABLE_NAME);
        assertEquals(2, cursor.getCount());
        for (Menu menu : menus) {
            cursor.moveToNext();
            assertEquals(menu.profile(), Db.MenuTable.parseCursor(cursor));
        }
    }

    @Test
    public void getMenus() {
        Menu menu1 = TestDataFactory.makeRibot("r1");
        Menu menu2 = TestDataFactory.makeRibot("r2");
        List<Menu> menus = Arrays.asList(menu1, menu2);

        mDatabaseHelper.setMenus(menus).subscribe();

        TestSubscriber<List<Menu>> result = new TestSubscriber<>();
        mDatabaseHelper.getMenus().subscribe(result);
        result.assertNoErrors();
        result.assertValue(menus);
    }

}