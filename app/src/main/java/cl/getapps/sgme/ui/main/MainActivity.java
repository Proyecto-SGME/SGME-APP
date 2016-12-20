package cl.getapps.sgme.ui.main;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.SyncService;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.ui.ayuda.AyudaActivity;
import cl.getapps.sgme.ui.base.BaseActivity;
import cl.getapps.sgme.ui.cuenta.CuentaActivity;
import cl.getapps.sgme.ui.eventos.EventosActivity;
import cl.getapps.sgme.ui.hojaderuta.HojaRutaActivity;
import cl.getapps.sgme.ui.notificaciones.NotificacionesActivity;
import cl.getapps.sgme.util.DialogFactory;

public class MainActivity extends BaseActivity implements MainMvpView, MainMenusAdapter.OnMenuClickListener {


    @BindView(R.id.titulo_seccion)
    TextView titulo_seccion;
    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "uk.co.androidboilerplate.ui.main.MainActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject MainPresenter mMainPresenter;
    @Inject
    MainMenusAdapter mMenusAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        showProgressDialog();

        mMenusAdapter.setOnMenuCLickListener(this);

        mRecyclerView.setAdapter(mMenusAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadMenus();

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }

        titulo_seccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearNotificacion();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showMenus(List<Menu> menus) {
        mMenusAdapter.setMenus(menus);
        mMenusAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public void showMenusVacios() {
        mMenusAdapter.setMenus(Collections.<Menu>emptyList());
        mMenusAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMenuClick(Menu menu) {
        switch (menu.profile().actividadMenu()){
            case "HojaRutaActivity":
                startActivity(new Intent(this, HojaRutaActivity.class));
                break;
            case "EventosActivity":
                startActivity(new Intent(this, EventosActivity.class));
                break;
            case "NotificacionesActivity":
                startActivity(new Intent(this, NotificacionesActivity.class));
                break;
            case "CuentaActivity":
                startActivity(new Intent(this, CuentaActivity.class));
                break;
            case "AyudaActivity":
                startActivity(new Intent(this, AyudaActivity.class));
                break;
        }
    }

    public void crearNotificacion(){

//set intents and pending intents to call service on click of "dismiss" action button of notification
        Intent dismissIntent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        dismissIntent.setAction("Sincronizar");
        PendingIntent piDismiss = PendingIntent.getActivity(this, 0, dismissIntent, 0);

//set intents and pending intents to call service on click of "snooze" action button of notification
        /*Intent snoozeIntent = new Intent(this, MyService.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        PendingIntent piSnooze = PendingIntent.getService(this, 0, snoozeIntent, 0);*/

        //build notification
        android.support.v4.app.NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_app)
                        .setContentTitle("Sincronización pendiente")
                        .setContentText("Sincronice la aplicación para recuperar nuevos eventos")
                        .setDefaults(Notification.DEFAULT_ALL) // must requires VIBRATE permission
                        .setPriority(NotificationCompat.PRIORITY_HIGH) //must give priority to High, Max which will considered as heads-up notification
                        /*.addAction(R.drawable.snooze,
                                getString(R.string.snooze), piSnooze)*/;
        builder.setContentIntent(piDismiss);
        builder.setAutoCancel(true);

// Gets an instance of the NotificationManager service
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//to post your notification to the notification bar with a id. If a notification with same id already exists, it will get replaced with updated information.
        notificationManager.notify(10000, builder.build());
    }

}
