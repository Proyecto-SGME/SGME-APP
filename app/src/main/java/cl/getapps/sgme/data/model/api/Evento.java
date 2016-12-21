package cl.getapps.sgme.data.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class Evento implements Parcelable
{
    @SerializedName("ID")
    @Expose
    public int id;
    @SerializedName("NOMBRE_CLIENTE")
    @Expose
    public String nombreCliente;
    @SerializedName("MAQUINA")
    @Expose
    public String maquina;
    @SerializedName("TIPO_FALLA")
    @Expose
    public String tipoFalla;
    @SerializedName("FECHA_EVENTO")
    @Expose
    public String fechaEvento;
    @SerializedName("ESTADO")
    @Expose
    public String estado;
    public final static Parcelable.Creator<Evento> CREATOR = new Creator<Evento>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Evento createFromParcel(Parcel in) {
            Evento instance = new Evento();
            instance.id = ((int) in.readValue((int.class.getClassLoader())));
            instance.nombreCliente = ((String) in.readValue((String.class.getClassLoader())));
            instance.maquina = ((String) in.readValue((String.class.getClassLoader())));
            instance.tipoFalla = ((String) in.readValue((String.class.getClassLoader())));
            instance.fechaEvento = ((String) in.readValue((String.class.getClassLoader())));
            instance.estado = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public Evento[] newArray(int size) {
            return (new Evento[size]);
        }

    }
            ;

    /**
     * No args constructor for use in serialization
     *
     */
    public Evento() {
    }

    /**
     *
     * @param estado
     * @param maquina
     * @param nombreCliente
     * @param tipoFalla
     * @param fechaEvento
     * @param id
     */
    public Evento(int id, String nombreCliente, String maquina, String tipoFalla, String fechaEvento, String estado) {
        super();
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.maquina = maquina;
        this.tipoFalla = tipoFalla;
        this.fechaEvento = fechaEvento;
        this.estado = estado;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nombreCliente);
        dest.writeValue(maquina);
        dest.writeValue(tipoFalla);
        dest.writeValue(fechaEvento);
        dest.writeValue(estado);
    }

    public int describeContents() {
        return 0;
    }

}
