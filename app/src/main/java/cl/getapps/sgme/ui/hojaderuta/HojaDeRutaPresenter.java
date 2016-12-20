package cl.getapps.sgme.ui.hojaderuta;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscription;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */

public class HojaDeRutaPresenter extends BasePresenter<HojaDeRutaMvpView> {

    DataManager  mDataManager;
    Subscription mSubscription;

    @Inject
    public HojaDeRutaPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(HojaDeRutaMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getHojaRuta(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);

    }
}
