package cl.getapps.sgme.ui.eventos.eventoabierto;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BaseFragment;
import cl.getapps.sgme.util.DialogFactory;

public class EventoAbiertoFragment extends BaseFragment implements EventoAbiertoMvpView {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    @Inject
    EventoAbiertoPresenter mPresenter;

    EventoAbiertoAdapter mAdapter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventoAbiertoFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static EventoAbiertoFragment newInstance(int columnCount) {
        EventoAbiertoFragment fragment = new EventoAbiertoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evento_list, container, false);
        ButterKnife.bind(this, view);

        mPresenter.attachView(this);

        mAdapter = new EventoAbiertoAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog();
        mPresenter.getEventosAbiertosBd();
    }

    @Override
    public void onError() {
        hideProgressDialog();
        DialogFactory.createGenericErrorDialog(getActivity(), "Error al cargar eventos").show();
    }

    @Override
    public void onEventosOk(List<Evento> eventos) {
        hideProgressDialog();
        mAdapter.setEventos(eventos);
    }

}
