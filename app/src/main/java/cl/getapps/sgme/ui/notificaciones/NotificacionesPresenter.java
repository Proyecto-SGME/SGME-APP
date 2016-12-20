package cl.getapps.sgme.ui.notificaciones;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscription;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */

public class NotificacionesPresenter extends BasePresenter<NotificacionesMvpView> {

    DataManager  mDataManager;
    Subscription mSubscription;

    @Inject
    public NotificacionesPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(NotificacionesMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getNotificaciones(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);

    }
}
