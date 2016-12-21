package cl.getapps.sgme.ui.eventos.detalle;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.DetalleEvento;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BaseActivity;
import cl.getapps.sgme.util.DialogFactory;

public class DetalleEventoActivity extends BaseActivity implements DetalleEventoMvpView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    DetalleEventoPresenter mPresenter;

    DetalleEventoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        ButterKnife.bind(this);
        activityComponent().inject(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter.attachView(this);

        mAdapter = new DetalleEventoAdapter();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(mAdapter);

        Evento evento = getIntent().getParcelableExtra("evento");

        mPresenter.getDetalleEvento(evento.id);

        showProgressDialog();
    }

    @Override
    public void onError() {
        hideProgressDialog();
        DialogFactory.createGenericErrorDialog(this, "Error al cargar detalle").show();
    }

    @Override
    public void onEventosOk(List<DetalleEvento> eventos) {
        hideProgressDialog();
        mAdapter.setDetallesEventos(eventos);

    }
}
