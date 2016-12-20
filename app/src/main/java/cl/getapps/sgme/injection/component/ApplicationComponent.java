package cl.getapps.sgme.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import cl.getapps.sgme.data.remote.SgmeService;
import dagger.Component;
import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.SyncService;
import cl.getapps.sgme.data.local.DatabaseHelper;
import cl.getapps.sgme.data.local.PreferencesHelper;
import cl.getapps.sgme.injection.ApplicationContext;
import cl.getapps.sgme.injection.module.ApplicationModule;
import cl.getapps.sgme.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    SgmeService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
