package cl.getapps.sgme.ui.eventos.eventocerrado;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Evento} and makes a call to the
 * specified {@link EventoCerradoFragment.OnEventoCerradoInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class EventoCerradoAdapter extends RecyclerView.Adapter<EventoCerradoAdapter.ViewHolder> {

    private List<Evento> eventos;
    private final EventoCerradoFragment.OnEventoCerradoInteractionListener mListener;

    public EventoCerradoAdapter(EventoCerradoFragment.OnEventoCerradoInteractionListener listener) {
        eventos = new ArrayList<>();
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

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.

                    mListener.onEventoCerradoInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View      mView;
        public Evento mItem;
        @BindView(R.id.nombreCliente)
        TextView nombreCliente;
        @BindView(R.id.maquina)
        TextView maquina;
        @BindView(R.id.fechaEvento)
        TextView fecha;
        @BindView(R.id.tipoFalla)
        TextView tipoFalla;
        @BindView(R.id.container)
        RelativeLayout container;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
