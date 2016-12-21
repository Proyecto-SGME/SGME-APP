package cl.getapps.sgme.ui.eventos;

import java.util.List;

import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.MvpView;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */
public interface EventosMvpView extends MvpView {

    void onEventosOk(List<Evento> eventos);

    void onError();
}
