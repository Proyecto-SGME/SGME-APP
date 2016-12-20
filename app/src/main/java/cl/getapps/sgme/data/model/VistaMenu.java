package cl.getapps.sgme.data.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.Date;

@AutoValue
public abstract class VistaMenu implements Parcelable {
    public abstract NombreMenu nombreMenu();
    public abstract String actividadMenu();
    public abstract String hexColor();
    public abstract String icon();

    public static Builder builder() {
        return new AutoValue_VistaMenu.Builder();
    }

    public static TypeAdapter<VistaMenu> typeAdapter(Gson gson) {
        return new AutoValue_VistaMenu.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setNombreMenu(NombreMenu nombreMenu);
        public abstract Builder setActividadMenu(String actividadMenu);
        public abstract Builder setHexColor(String hexColor);
        public abstract Builder setIcon(String icon);
        public abstract VistaMenu build();
    }
}
