package cl.getapps.sgme.ui.ayuda;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.data.model.ayuda.Ayuda;
import cl.getapps.sgme.data.model.ayuda.ItemAyuda;
import cl.getapps.sgme.ui.base.BaseActivity;

public class AyudaActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar        toolbar;
    @BindView(R.id.titulo_seccion)
    TextView       titulo_seccion;
    @BindView(R.id.recycler_view)
    RecyclerView   recycler_view;
    @BindView(R.id.content_ayuda)
    RelativeLayout content_ayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<ItemAyuda> ayudaList = getAyuda();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //instantiate your adapter with the list of genres
        AyudaAdapter adapter = new AyudaAdapter(ayudaList);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(adapter);
    }

    private List<ItemAyuda> getAyuda() {
        List<Ayuda> ayudaList = new ArrayList<>();
        List<ItemAyuda> itemAyudaList = new ArrayList<>();
        ayudaList.add(new Ayuda("Cerrar sesión", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque quam tortor, condimentum sed dui eu, faucibus tempus metus. Nullam suscipit tortor at metus imperdiet sagittis. Cras ac leo in enim gravida aliquet quis non massa. Praesent lobortis metus eget ante molestie, suscipit blandit massa malesuada. Pellentesque et libero sem. Fusce turpis orci, aliquam non pulvinar a, ornare non turpis. Nulla facilisi. Vestibulum commodo ante nec lectus venenatis, et vehicula lorem posuere. Curabitur ullamcorper purus ac urna dapibus, in rutrum dui tempus. Cras ultricies leo vitae risus posuere, id mattis augue ultricies. Nam consequat arcu ipsum. Donec non massa ut ante euismod mattis in sit amet nulla."));

        itemAyudaList.add(new ItemAyuda("Cerrar sesión", ayudaList));
        itemAyudaList.add(new ItemAyuda("Sincronizar aplicación", ayudaList));
        itemAyudaList.add(new ItemAyuda("Ver eventos", ayudaList));
        itemAyudaList.add(new ItemAyuda("Ver rutas", ayudaList));
        itemAyudaList.add(new ItemAyuda("Ver notificaciones", ayudaList));

        return itemAyudaList;
    }


}
