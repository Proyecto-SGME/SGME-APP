package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Creado por GRINGRAZ el 21-12-2016.
 */

public class DetalleEvento implements Parcelable {
    @SerializedName("ID")
    @Expose
    public int id;
    @SerializedName("N_EVE")
    @Expose
    public int nEve;
    @SerializedName("DESCRIPCI\u00d3N")
    @Expose
    public String descripcion;
    @SerializedName("FECHA")
    @Expose
    public String fecha;
    public final static Parcelable.Creator<DetalleEvento> CREATOR = new Creator<DetalleEvento>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DetalleEvento createFromParcel(Parcel in) {
            DetalleEvento instance = new DetalleEvento();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.nEve = ((int) in.readValue((int.class.getClassLoader())));
            instance.descripcion = ((String) in.readValue((String.class.getClassLoader())));
            instance.fecha = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public DetalleEvento[] newArray(int size) {
            return (new DetalleEvento[size]);
        }

    }
            ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nEve);
        dest.writeValue(descripcion);
        dest.writeValue(fecha);
    }

    public int describeContents() {
        return 0;
    }
}
