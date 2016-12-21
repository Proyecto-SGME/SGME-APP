package cl.getapps.sgme.ui.eventos.eventoabierto;

import java.util.List;

import javax.inject.Inject;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.ui.eventos.EventosMvpView;
import cl.getapps.sgme.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

public class EventoAbiertoPresenter extends BasePresenter<EventoAbiertoMvpView> {
    DataManager mDataManager;
    Subscription mSubscription;

    @Inject
    public EventoAbiertoPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EventoAbiertoMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getEventosAbiertosBd(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getEventosAbiertosBd()
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
