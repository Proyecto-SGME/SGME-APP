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

}
