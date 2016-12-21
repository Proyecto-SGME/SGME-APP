package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Ruta implements Parcelable {


    protected Ruta(Parcel in) {
    }

    public static final Creator<Ruta> CREATOR = new Creator<Ruta>() {
        @Override
        public Ruta createFromParcel(Parcel in) {
            return new Ruta(in);
        }

        @Override
        public Ruta[] newArray(int size) {
            return new Ruta[size];
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
