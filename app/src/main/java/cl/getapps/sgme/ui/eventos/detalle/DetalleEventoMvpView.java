package cl.getapps.sgme.ui.eventos.detalle;

import java.util.List;

import cl.getapps.sgme.data.model.api.DetalleEvento;
import cl.getapps.sgme.ui.base.MvpView;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */
public interface DetalleEventoMvpView extends MvpView {
    void onError();

    void onEventosOk(List<DetalleEvento> eventos);
}
