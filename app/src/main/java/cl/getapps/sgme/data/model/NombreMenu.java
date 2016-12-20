package cl.getapps.sgme.data.model;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;


@AutoValue
public abstract class NombreMenu implements Parcelable {
    public abstract String nombre();
    public abstract String decripcion();

    public static NombreMenu create(String nombre, String descripcion) {
        return new AutoValue_NombreMenu(nombre, descripcion);
    }

    public static TypeAdapter<NombreMenu> typeAdapter(Gson gson) {
        return new AutoValue_NombreMenu.GsonTypeAdapter(gson);
    }

}
