package cl.getapps.sgme;

import android.app.Application;
import android.content.Context;

import cl.getapps.sgme.injection.component.ApplicationComponent;
import cl.getapps.sgme.injection.component.DaggerApplicationComponent;
import cl.getapps.sgme.injection.module.ApplicationModule;
import timber.log.Timber;

public class SgmeApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //Fabric.with(this, new Crashlytics());
        }
    }

    public static SgmeApplication get(Context context) {
        return (SgmeApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
