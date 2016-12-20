package cl.getapps.sgme.data.local;

import android.content.ContentValues;
import android.database.Cursor;

import cl.getapps.sgme.data.model.NombreMenu;
import cl.getapps.sgme.data.model.VistaMenu;

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
}
