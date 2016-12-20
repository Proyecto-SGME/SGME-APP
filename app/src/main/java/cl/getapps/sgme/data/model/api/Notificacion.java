package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Notificacion implements Parcelable {
    protected Notificacion(Parcel in) {
    }

    public static final Creator<Notificacion> CREATOR = new Creator<Notificacion>() {
        @Override
        public Notificacion createFromParcel(Parcel in) {
            return new Notificacion(in);
        }

        @Override
        public Notificacion[] newArray(int size) {
            return new Notificacion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
