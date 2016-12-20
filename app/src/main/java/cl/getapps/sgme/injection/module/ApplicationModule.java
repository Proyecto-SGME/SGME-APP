package cl.getapps.sgme.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import cl.getapps.sgme.data.remote.SgmeService;
import dagger.Module;
import dagger.Provides;
import cl.getapps.sgme.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    SgmeService provideRibotsService() {
        return SgmeService.Creator.newRibotsService();
    }

}
