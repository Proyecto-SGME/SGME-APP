package cl.getapps.sgme.injection.component;

import dagger.Subcomponent;
import cl.getapps.sgme.injection.PerActivity;
import cl.getapps.sgme.injection.module.ActivityModule;
import cl.getapps.sgme.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
