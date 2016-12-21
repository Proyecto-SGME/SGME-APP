package cl.getapps.sgme.ui.hojaderuta;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cl.getapps.sgme.R;
import cl.getapps.sgme.ui.base.BaseActivity;
import cl.getapps.sgme.ui.hojaderuta.detallehojaruta.RutaActivity;
/*import cl.getapps.sgme.ui.hojaderuta.detallehojaruta.RutaActivity;*/

public class HojaRutaActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar        toolbar;
    @BindView(R.id.titulo_seccion)
    TextView       titulo_seccion;
    @BindView(R.id.item_falso)
    TextView       item_falso;
    @BindView(R.id.content_hoja_ruta)
    RelativeLayout content_hoja_ruta;
    @BindView(R.id.card_view)
    CardView card;

    @BindView(R.id.card_view1)
    CardView card1;

    @BindView(R.id.container)
            RelativeLayout container;

    @BindView(R.id.container1)
            RelativeLayout container1;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoja_ruta);
        activityComponent().inject(this);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RutaActivity.class).putExtra("1", 1));
            }
        });

        container1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, RutaActivity.class).putExtra("2", 2));
            }
        });
    }
}