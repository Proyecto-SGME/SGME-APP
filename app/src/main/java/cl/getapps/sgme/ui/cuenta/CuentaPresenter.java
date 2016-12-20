package cl.getapps.sgme.ui.cuenta;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscription;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */

public class CuentaPresenter extends BasePresenter<CuentaMvpView> {

    DataManager  mDataManager;
    Subscription mSubscription;

    @Inject
    public CuentaPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(CuentaMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getCuenta(int empresaId, int clienteRut){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
    }
}
