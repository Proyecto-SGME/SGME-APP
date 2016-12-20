package cl.getapps.sgme.data.model.ayuda;

import android.os.Parcel;
import android.os.Parcelable;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Ayuda implements Parcelable {

    public String titulo;
    public String mensaje;

    public Ayuda() {
    }

    public Ayuda(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    protected Ayuda(Parcel in) {
        titulo = in.readString();
        mensaje = in.readString();
    }

    public static final Creator<Ayuda> CREATOR = new Creator<Ayuda>() {
        @Override
        public Ayuda createFromParcel(Parcel in) {
            return new Ayuda(in);
        }

        @Override
        public Ayuda[] newArray(int size) {
            return new Ayuda[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(titulo);
        parcel.writeString(mensaje);
    }
}
