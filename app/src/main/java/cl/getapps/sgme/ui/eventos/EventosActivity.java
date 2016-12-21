package cl.getapps.sgme.ui.eventos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BaseActivity;
import cl.getapps.sgme.ui.eventos.detalle.DetalleEventoActivity;
import cl.getapps.sgme.ui.eventos.eventoabierto.EventoAbiertoFragment;
import cl.getapps.sgme.ui.eventos.eventocerrado.EventoCerradoFragment;
import cl.getapps.sgme.ui.eventos.eventopendiente.EventoPendienteFragment;
import cl.getapps.sgme.util.DialogFactory;

public class EventosActivity extends BaseActivity implements
        EventoCerradoFragment.OnEventoCerradoInteractionListener, EventoPendienteFragment.OnEventoCerradoInteractionListener, EventosMvpView {


    @BindView(R.id.toolbar)
    Toolbar        toolbar;
    @BindView(R.id.titulo_seccion)
    TextView       titulo_seccion;
    @BindView(R.id.tabsEventos)
    TabLayout      tabsEventos;
    @BindView(R.id.pagerEventos)
    ViewPager      pagerEventos;
    @BindView(R.id.content_eventos)
    RelativeLayout content_eventos;

    private ViewPagerAdapter pagerAdapter;

    @Inject
    EventosPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.attachView(this);

        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pagerEventos.setAdapter(pagerAdapter);

        tabsEventos.setupWithViewPager(pagerEventos);

        setupTabsEventos();

        showProgressDialog();

        mPresenter.getEventos();
    }

    private void setupWindowAnimations() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.activity_fade);
        getWindow().setEnterTransition(fade);
    }

    private void setupTabsEventos(){
        tabsEventos.getTabAt(0).setText("ABIERTOS");
        tabsEventos.getTabAt(1).setText("CERRADOS");
        tabsEventos.getTabAt(2).setText("PENDIENTES");
    }

    @Override
    public void onEventosOk(List<Evento> eventos) {
        hideProgressDialog();
        mPresenter.insertarEventos(eventos);
    }

    @Override
    public void onError() {
        hideProgressDialog();
        DialogFactory.createGenericErrorDialog(this, "Sin sincronización").show();
    }

    @Override
    public void onEventoCerradoInteraction(Evento evento) {
        startActivity(new Intent(this, DetalleEventoActivity.class).putExtra("evento", evento));
    }

    @Override
    public void onEventoPendienteInteraction(Evento evento) {
        startActivity(new Intent(this, DetalleEventoActivity.class).putExtra("evento", evento));
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new EventoAbiertoFragment();
                case 1:
                    return new EventoCerradoFragment();
                case 2:
                    return new EventoPendienteFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
