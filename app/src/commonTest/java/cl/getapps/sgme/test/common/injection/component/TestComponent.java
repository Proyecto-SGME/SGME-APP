package cl.getapps.sgme.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import cl.getapps.sgme.injection.component.ApplicationComponent;
import cl.getapps.sgme.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
