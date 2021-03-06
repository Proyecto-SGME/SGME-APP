package cl.getapps.sgme.ui.eventos;

import java.util.List;

import javax.inject.Inject;
import javax.xml.validation.Schema;

import cl.getapps.sgme.data.DataManager;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BasePresenter;
import cl.getapps.sgme.util.RxUtil;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Creado por GRINGRAZ el 20-12-2016.
 */

public class EventosPresenter extends BasePresenter<EventosMvpView> {

    DataManager  mDataManager;
    Subscription mSubscription;

    @Inject
    public EventosPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(EventosMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getEventos(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getEventos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Evento>>() {
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
                    public void onNext(List<Evento> eventos) {
                        getMvpView().onEventosOk(eventos);
                    }
                });
    }

    public void insertarEventos(List<Evento> eventos) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.insertarEventos(eventos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Evento>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Evento evento) {

                    }
                });
    }
}
