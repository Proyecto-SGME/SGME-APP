package cl.getapps.sgme.ui.ayuda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.ayuda.Ayuda;
import cl.getapps.sgme.data.model.ayuda.ItemAyuda;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class AyudaAdapter extends ExpandableRecyclerViewAdapter<AyudaAdapter.TituloViewHolder, AyudaAdapter.TextoViewHolder> {


    public AyudaAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public TituloViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_ayuda, parent, false);
        return new TituloViewHolder(view);
    }

    @Override
    public TextoViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item_ayuda, parent, false);
        return new TextoViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(TextoViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Ayuda ayuda = ((ItemAyuda) group).getItems().get(childIndex);
        holder.onBind(ayuda);
        //holder.texto_ayuda.setText(ayuda.mensaje);
    }

    @Override
    public void onBindGroupViewHolder(TituloViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitulo(group);
    }

    public class TituloViewHolder extends GroupViewHolder {

        @BindView(R.id.titulo_ayuda)
        TextView titulo_ayuda;

        @BindView(R.id.icon_expand)
        ImageView icon_expand;


        public TituloViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setTitulo(ExpandableGroup group) {
            titulo_ayuda.setText(group.getTitle());
        }

        @Override
        public void expand() {
            icon_expand.animate().rotation(-180).setDuration(300).start();
            //icon_expand.setImageResource(R.drawable.ic_expand_less);
        }

        @Override
        public void collapse() {
            icon_expand.animate().rotation(0).setDuration(300).start();
            //icon_expand.setImageResource(R.drawable.ic_expand_more);
        }
    }

    public class TextoViewHolder extends ChildViewHolder {

        @BindView(R.id.texto_ayuda)
        TextView texto_ayuda;

        public TextoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(Ayuda ayuda) {
            texto_ayuda.setText(ayuda.mensaje);
        }
    }
}
