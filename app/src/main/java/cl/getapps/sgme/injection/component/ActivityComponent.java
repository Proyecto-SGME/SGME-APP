package cl.getapps.sgme.injection.component;

import cl.getapps.sgme.ui.ayuda.AyudaActivity;
import cl.getapps.sgme.ui.eventos.EventosActivity;
import cl.getapps.sgme.ui.eventos.detalle.DetalleEventoActivity;
import cl.getapps.sgme.ui.hojaderuta.HojaRutaActivity;
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

    void inject(EventosActivity eventosActivity);

    void inject(HojaRutaActivity hojaRutaActivity);

    void inject(AyudaActivity ayudaActivity);

    void inject(DetalleEventoActivity detalleEventoActivity);
}
