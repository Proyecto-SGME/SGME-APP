package cl.getapps.sgme.ui.eventos.detalle;

import java.util.List;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.model.api.DetalleEvento;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

public class DetalleEventoPresenter extends BasePresenter<DetalleEventoMvpView> {
    DataManager mDataManager;
    Subscription mSubscription;

    @Inject
    public DetalleEventoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(DetalleEventoMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getDetalleEvento(int id){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getDetalleEvento(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<DetalleEvento>>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("EVENTOS COMPLETOS");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "ERROR EVENTOS");
                        getMvpView().onError();
                    }

                    @Override
                    public void onNext(List<DetalleEvento> eventos) {
                        getMvpView().onEventosOk(eventos);
                    }
                });
    }
}
