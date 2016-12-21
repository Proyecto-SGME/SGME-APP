package cl.getapps.sgme.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import cl.getapps.sgme.data.model.NombreMenu;
import cl.getapps.sgme.data.model.VistaMenu;
import cl.getapps.sgme.data.model.api.Evento;

public class Db {

    public Db() { }

    public abstract static class MenuTable {
        public static final String TABLE_NAME = "menus";

        public static final String COLUMN_ACTIVDAD_MENU = "actividadMenu";
        public static final String COLUMN_NOMBRE = "first_name";
        public static final String COLUMN_DESCRIPCION = "last_name";
        public static final String COLUMN_HEX_COLOR = "hex_color";
        public static final String COLUMN_ICON = "icon";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ACTIVDAD_MENU + " TEXT PRIMARY KEY, " +
                        COLUMN_NOMBRE + " TEXT, " +
                        COLUMN_DESCRIPCION + " TEXT, " +
                        COLUMN_HEX_COLOR + " TEXT, " +
                        COLUMN_ICON + " TEXT" +
                "); ";

        public static ContentValues toContentValues(VistaMenu vistaMenu) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ACTIVDAD_MENU, vistaMenu.actividadMenu());
            values.put(COLUMN_NOMBRE, vistaMenu.nombreMenu().nombre());
            values.put(COLUMN_DESCRIPCION, vistaMenu.nombreMenu().decripcion());
            values.put(COLUMN_HEX_COLOR, vistaMenu.hexColor());
            values.put(COLUMN_ICON, vistaMenu.icon());
            return values;
        }

        public static VistaMenu parseCursor(Cursor cursor) {
            NombreMenu nombreMenu = NombreMenu.create(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION)));

            return VistaMenu.builder()
                    .setNombreMenu(nombreMenu)
                    .setActividadMenu(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ACTIVDAD_MENU)))
                    .setHexColor(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_HEX_COLOR)))
                    .setIcon(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ICON)))
                    .build();
        }
    }

    public abstract static class EventoTable {
        public static final String TABLE_NAME = "eventos";

        public static final String COLUMN_ID_EVENTO = "id_evento";
        public static final String COLUMN_NOMBRE_CLIENTE = "nombre_cliente";
        public static final String COLUMN_MAQUINA = "maquina";
        public static final String COLUMN_TIPO_FALLA = "tipo_falla";
        public static final String COLUMN_FECHA_EVENTO = "evento";
        public static final String COLUMN_ESTADO = "estado";

        public static final String CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID_EVENTO + " INT PRIMARY KEY, " +
                        COLUMN_NOMBRE_CLIENTE + " TEXT, " +
                        COLUMN_MAQUINA + " TEXT, " +
                        COLUMN_TIPO_FALLA + " TEXT, " +
                        COLUMN_FECHA_EVENTO + " TEXT, " +
                        COLUMN_ESTADO + " TEXT" +
                        "); ";

        public static ContentValues toContentValues(Evento evento) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID_EVENTO, evento.id);
            values.put(COLUMN_NOMBRE_CLIENTE, evento.nombreCliente);
            values.put(COLUMN_MAQUINA, evento.maquina);
            values.put(COLUMN_TIPO_FALLA, evento.tipoFalla);
            values.put(COLUMN_FECHA_EVENTO, evento.fechaEvento);
            values.put(COLUMN_ESTADO, evento.estado);
            return values;
        }

        public static Evento parseCursor(Cursor cursor) {
            Evento evento = new Evento();
            evento.id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID_EVENTO));
            evento.nombreCliente = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE_CLIENTE));
            evento.maquina = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MAQUINA));
            evento.tipoFalla = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIPO_FALLA));
            evento.fechaEvento = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FECHA_EVENTO));
            evento.estado = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ESTADO));
            return evento;
        }
    }
}
