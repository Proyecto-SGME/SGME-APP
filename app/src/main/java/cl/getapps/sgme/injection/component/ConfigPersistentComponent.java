package cl.getapps.sgme.injection.component;

import cl.getapps.sgme.injection.module.FragmentModule;
import dagger.Component;
import cl.getapps.sgme.injection.ConfigPersistent;
import cl.getapps.sgme.injection.module.ActivityModule;
import cl.getapps.sgme.ui.base.BaseActivity;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}