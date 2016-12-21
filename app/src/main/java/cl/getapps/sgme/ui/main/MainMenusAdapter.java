package cl.getapps.sgme.ui.main;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.ui.ayuda.AyudaActivity;
import cl.getapps.sgme.ui.cuenta.CuentaActivity;
import cl.getapps.sgme.ui.eventos.EventosActivity;
import cl.getapps.sgme.ui.hojaderuta.HojaRutaActivity;
import cl.getapps.sgme.ui.notificaciones.NotificacionesActivity;

public class MainMenusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Menu> mMenus;
    private OnMenuClickListener mListener;

    private int lastPosition = -1;

    @Inject
    public MainMenusAdapter() {
        mMenus = new ArrayList<>();
    }

    public void setOnMenuCLickListener(OnMenuClickListener mListener){
        this.mListener = mListener;
    }

    public void setMenus(List<Menu> menus) {
        mMenus = menus;
    }

    @Override
    public MenuViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu, parent, false);
        return new MenuViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final Menu menu = mMenus.get(position);
        final MenuViewholder menuholder = (MenuViewholder) holder;
        menuholder.nameTextView.setText(String.format("%s", menu.profile()
                .nombreMenu().nombre().toUpperCase()));

        switch (menu.profile().actividadMenu()){
            case "HojaRutaActivity":
                menuholder.hexColorView.setImageResource(R.drawable.ic_hoja_ruta);
                break;
            case "EventosActivity":
                menuholder.hexColorView.setImageResource(R.drawable.ic_eventos);
                break;
            case "NotificacionesActivity":
                menuholder.hexColorView.setImageResource(R.drawable.ic_notificaciones);
                break;
            case "CuentaActivity":
                menuholder.hexColorView.setImageResource(R.drawable.ic_cuenta);
                break;
            case "AyudaActivity":
                menuholder.hexColorView.setImageResource(R.drawable.ic_help);
                break;
        }

        menuholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onMenuClick(menu, menuholder.hexColorView);
            }
        });
        setAnimation(menuholder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return mMenus.size();
    }

    class MenuViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.img_icon) ImageView hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;

        public MenuViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void clearAnimation() {
            itemView.clearAnimation();
        }

        @Override
        public void onClick(View v) {

        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(final RecyclerView.ViewHolder holder) {
        if (holder instanceof MenuViewholder) {
            ((MenuViewholder)holder).clearAnimation();
        }
    }

    public interface OnMenuClickListener{
        void onMenuClick(Menu menu, ImageView hexColorView);
    }
}
