package cl.getapps.sgme.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
}
