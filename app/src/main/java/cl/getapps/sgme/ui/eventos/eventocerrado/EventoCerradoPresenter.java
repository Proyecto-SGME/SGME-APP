package cl.getapps.sgme.ui.eventos.eventocerrado;

import java.util.List;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.model.api.Evento;
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

public class EventoCerradoPresenter extends BasePresenter<EventoCerradoMvpView> {
    DataManager mDataManager;
    Subscription mSubscription;

    public EventoCerradoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public boolean isViewAttached() {
        return super.isViewAttached();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getEventosCerradosBd(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getEventosCerradosBd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Evento>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "ERROR EVENTOS BD");
                        getMvpView().onError();
                    }

                    @Override
                    public void onNext(List<Evento> eventos) {
                        getMvpView().onEventosOk(eventos);
                    }
                });
    }
}
