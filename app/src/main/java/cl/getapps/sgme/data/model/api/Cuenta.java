package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Cuenta implements Parcelable {
    protected Cuenta(Parcel in) {
    }

    public static final Creator<Cuenta> CREATOR = new Creator<Cuenta>() {
        @Override
        public Cuenta createFromParcel(Parcel in) {
            return new Cuenta(in);
        }

        @Override
        public Cuenta[] newArray(int size) {
            return new Cuenta[size];
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
