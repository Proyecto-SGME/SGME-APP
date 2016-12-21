package cl.getapps.sgme.ui.eventos.eventopendiente;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Evento} and makes a call to the
 * specified {@link EventoPendienteFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventoPendienteAdapter extends RecyclerView.Adapter<EventoPendienteAdapter.ViewHolder> {

    private List<Evento> eventos;
    private EventoPendienteFragment.OnListFragmentInteractionListener mListener;

    public EventoPendienteAdapter(EventoPendienteFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    public void setEventos(List<Evento> eventos){
        this.eventos = eventos;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = eventos.get(position);

        holder.nombreCliente.setText(holder.mItem.nombreCliente);
        holder.maquina.setText(holder.mItem.maquina);
        holder.tipoFalla.setText(holder.mItem.tipoFalla);
        holder.fecha.setText(holder.mItem.fechaEvento);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View      mView;
        public       Evento mItem;
        @BindView(R.id.nombreCliente)
        TextView nombreCliente;
        @BindView(R.id.maquina)
        TextView maquina;
        @BindView(R.id.fechaEvento)
        TextView fecha;
        @BindView(R.id.tipoFalla)
        TextView tipoFalla;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
