package cl.getapps.sgme.ui.ayuda;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscription;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */

public class AyudaPresenter extends BasePresenter<AyudaMvpView> {
    DataManager  mDataManager;
    Subscription mSubscription;

    @Inject
    public AyudaPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(AyudaMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getAyuda(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);

    }
}
