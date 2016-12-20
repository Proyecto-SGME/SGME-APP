package cl.getapps.sgme.data.model;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Menu implements Comparable<Menu>, Parcelable {

    public abstract VistaMenu profile();

    public static Menu create(VistaMenu vistaMenu) {
        return new AutoValue_Menu(vistaMenu);
    }

    public static TypeAdapter<Menu> typeAdapter(Gson gson) {
        return new AutoValue_Menu.GsonTypeAdapter(gson);
    }

    @Override
    public int compareTo(@NonNull Menu another) {
        return profile().nombreMenu().nombre().compareToIgnoreCase(another.profile().nombreMenu().nombre());
    }
}

