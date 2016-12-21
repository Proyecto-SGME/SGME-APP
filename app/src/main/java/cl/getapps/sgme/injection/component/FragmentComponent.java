package cl.getapps.sgme.injection.component;

import cl.getapps.sgme.injection.PerFragment;
import cl.getapps.sgme.injection.module.FragmentModule;
import cl.getapps.sgme.ui.eventos.eventoabierto.EventoAbiertoFragment;
import cl.getapps.sgme.ui.eventos.eventocerrado.EventoCerradoFragment;
import dagger.Subcomponent;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

    void inject(EventoAbiertoFragment eventoAbiertoFragment);

    void inject(EventoCerradoFragment eventoCerradoFragment);
}
