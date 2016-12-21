package cl.getapps.sgme.ui.eventos.eventoabierto;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.Evento;
import cl.getapps.sgme.ui.eventos.detalle.DetalleEventoActivity;

public class EventoAbiertoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Evento> eventos;

    public EventoAbiertoAdapter() {
        eventos = new ArrayList<>();
    }

    public void setEventos(List<Evento> eventos){
        this.eventos = eventos;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_evento, parent, false);
        return new ItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final ItemHolder holder1 = (ItemHolder) holder;
        holder1.mItem = eventos.get(position);

        holder1.nombreCliente.setText(holder1.mItem.nombreCliente);
        holder1.maquina.setText(holder1.mItem.maquina);
        holder1.tipoFalla.setText(holder1.mItem.tipoFalla);
        holder1.fecha.setText(holder1.mItem.fechaEvento);

        holder1.nombreCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), DetalleEventoActivity.class).putExtra("evento", holder1.mItem));
            }
        });

        holder1.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder1.itemView.getContext().startActivity(new Intent(holder.itemView.getContext(), DetalleEventoActivity.class).putExtra("evento", holder1.mItem));
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public       Evento mItem;
        @BindView(R.id.nombreCliente)
        TextView nombreCliente;
        @BindView(R.id.container)
        RelativeLayout container;
        @BindView(R.id.maquina)
        TextView maquina;
        @BindView(R.id.fechaEvento)
        TextView fecha;
        @BindView(R.id.tipoFalla)
        TextView tipoFalla;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemView.getContext().startActivity(new Intent(itemView.getContext(), DetalleEventoActivity.class).putExtra("evento", mItem));
        }
    }
}
