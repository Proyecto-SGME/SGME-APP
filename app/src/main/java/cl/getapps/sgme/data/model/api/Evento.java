package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Evento implements Parcelable {
    protected Evento(Parcel in) {
    }

    public static final Creator<Evento> CREATOR = new Creator<Evento>() {
        @Override
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        @Override
        public Evento[] newArray(int size) {
            return new Evento[size];
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
