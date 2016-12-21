package cl.getapps.sgme.ui.eventos.eventocerrado;

import java.util.List;

import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.MvpView;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */
public interface EventoCerradoMvpView extends MvpView {
    void onError();

    void onEventosOk(List<Evento> eventos);
}
