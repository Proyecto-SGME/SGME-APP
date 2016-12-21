package cl.getapps.sgme.ui.eventos.eventocerrado;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.base.BaseFragment;
import cl.getapps.sgme.util.DialogFactory;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnEventoCerradoInteractionListener}
 * interface.
 */
public class EventoCerradoFragment extends BaseFragment implements EventoCerradoMvpView {

    // TODO: Customize parameters
    private int mColumnCount = 1;

    private OnEventoCerradoInteractionListener mListener;

    @Inject
    EventoCerradoPresenter mPresenter;

    EventoCerradoAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventoCerradoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evento_cerrado_list, container, false);

        mPresenter.attachView(this);

        mAdapter = new EventoCerradoAdapter(mListener);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(mAdapter);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showProgressDialog();
        mPresenter.getEventosCerradosBd();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventoCerradoInteractionListener) {
            mListener = (OnEventoCerradoInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEventoCerradoInteractionListener {
        // TODO: Update argument type and name
        void onEventoCerradoInteraction(Evento evento);
    }
}
