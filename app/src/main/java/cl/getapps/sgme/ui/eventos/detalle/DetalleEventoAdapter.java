package cl.getapps.sgme.ui.eventos.detalle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.api.DetalleEvento;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

public class DetalleEventoAdapter extends RecyclerView.Adapter<DetalleEventoAdapter.ViewHolder> {

    List<DetalleEvento> detalleEventos;

    public DetalleEventoAdapter() {
        detalleEventos = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detalle_evento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = detalleEventos.get(position);

        holder.detalle.setText(holder.mItem.descripcion);
        holder.fecha.setText(holder.mItem.fecha);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return detalleEventos.size();
    }

    public void setDetallesEventos(List<DetalleEvento> eventos) {
        this.detalleEventos = eventos;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View      mView;
        public DetalleEvento mItem;
        @BindView(R.id.detalle)
        TextView detalle;
        @BindView(R.id.fechaEvento)
        TextView fecha;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
