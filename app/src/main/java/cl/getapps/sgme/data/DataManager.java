package cl.getapps.sgme.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.util.MenuFactory;
import rx.Observable;
import cl.getapps.sgme.data.local.DatabaseHelper;
import cl.getapps.sgme.data.local.PreferencesHelper;
import cl.getapps.sgme.data.remote.SgmeService;

@Singleton
public class DataManager {

    private final SgmeService mSgmeService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(SgmeService sgmeService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mSgmeService = sgmeService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<Menu> syncMenu() {
        return mDatabaseHelper.setMenus(MenuFactory.getMenus());
    }

    public Observable<List<Menu>> getMenus() {
        return mDatabaseHelper.getMenus().distinct();
    }

}
