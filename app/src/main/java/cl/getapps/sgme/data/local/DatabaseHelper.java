package cl.getapps.sgme.data.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.data.model.api.Evento;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

@Singleton
public class DatabaseHelper {

    private final BriteDatabase mDb;

    @Inject
    public DatabaseHelper(DbOpenHelper dbOpenHelper) {
        SqlBrite.Builder briteBuilder = new SqlBrite.Builder();
        mDb = briteBuilder.build().wrapDatabaseHelper(dbOpenHelper, Schedulers.immediate());
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public Observable<Menu> setMenus(final Collection<Menu> newMenus) {
        return Observable.create(new Observable.OnSubscribe<Menu>() {
            @Override
            public void call(Subscriber<? super Menu> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.MenuTable.TABLE_NAME, null);
                    for (Menu menu : newMenus) {
                        long result = mDb.insert(Db.MenuTable.TABLE_NAME,
                                Db.MenuTable.toContentValues(menu.profile()),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(menu);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Menu>> getMenus() {
        return mDb.createQuery(Db.MenuTable.TABLE_NAME,
                "SELECT * FROM " + Db.MenuTable.TABLE_NAME)
                .mapToList(new Func1<Cursor, Menu>() {
                    @Override
                    public Menu call(Cursor cursor) {
                        return Menu.create(Db.MenuTable.parseCursor(cursor));
                    }
                });
    }

    public Observable<Evento> insertarEventos(final List<Evento> eventos) {
        return Observable.create(new Observable.OnSubscribe<Evento>() {
            @Override
            public void call(Subscriber<? super Evento> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                BriteDatabase.Transaction transaction = mDb.newTransaction();
                try {
                    mDb.delete(Db.EventoTable.TABLE_NAME, null);
                    for (Evento evento : eventos) {
                        long result = mDb.insert(Db.EventoTable.TABLE_NAME,
                                Db.EventoTable.toContentValues(evento),
                                SQLiteDatabase.CONFLICT_REPLACE);
                        if (result >= 0) subscriber.onNext(evento);
                    }
                    transaction.markSuccessful();
                    subscriber.onCompleted();
                } finally {
                    transaction.end();
                }
            }
        });
    }

    public Observable<List<Evento>> getEventosAbiertosBd() {
        return mDb.createQuery(Db.EventoTable.TABLE_NAME,
                "SELECT * FROM " + Db.EventoTable.TABLE_NAME + " WHERE " + Db.EventoTable.COLUMN_ESTADO + "='ABIERTO'")
                .mapToList(new Func1<Cursor, Evento>() {
                    @Override
                    public Evento call(Cursor cursor) {
                        return Db.EventoTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<List<Evento>> getEventosCerradosBd() {
        return mDb.createQuery(Db.EventoTable.TABLE_NAME,
                "SELECT * FROM " + Db.EventoTable.TABLE_NAME + " WHERE " + Db.EventoTable.COLUMN_ESTADO + "='CERRADO'")
                .mapToList(new Func1<Cursor, Evento>() {
                    @Override
                    public Evento call(Cursor cursor) {
                        return Db.EventoTable.parseCursor(cursor);
                    }
                });
    }

    public Observable<List<Evento>> getEventosPendientesBd() {
        return mDb.createQuery(Db.EventoTable.TABLE_NAME,
                "SELECT * FROM " + Db.EventoTable.TABLE_NAME + " WHERE " + Db.EventoTable.COLUMN_ESTADO + "='PENDIENTE'")
                .mapToList(new Func1<Cursor, Evento>() {
                    @Override
                    public Evento call(Cursor cursor) {
                        return Db.EventoTable.parseCursor(cursor);
                    }
                });
    }
}
