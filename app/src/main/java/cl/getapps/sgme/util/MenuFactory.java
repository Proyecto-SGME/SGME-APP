package cl.getapps.sgme.util;

import java.util.ArrayList;
import java.util.List;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.data.model.NombreMenu;
import cl.getapps.sgme.data.model.VistaMenu;
import cl.getapps.sgme.ui.ayuda.AyudaActivity;
import cl.getapps.sgme.ui.cuenta.CuentaActivity;
import cl.getapps.sgme.ui.eventos.EventosActivity;
import cl.getapps.sgme.ui.hojaderuta.HojaRutaActivity;
import cl.getapps.sgme.ui.notificaciones.NotificacionesActivity;

/**
 * Creado por GRINGRAZ el 18-12-2016.
 */

public final class MenuFactory {

    private static List<Menu> menus;
    private static List<NombreMenu> nombreMenus;
    private static List<VistaMenu> vistaMenus;

    private static void initNombresMenu(){
        nombreMenus = new ArrayList<>();
        nombreMenus.add(NombreMenu.create(
                NombreMenuEnum.HOJADERUTA.getNombre(),
                NombreMenuEnum.HOJADERUTA.getDescripcion()));
        nombreMenus.add(NombreMenu.create(
                NombreMenuEnum.EVENTOS.getNombre(),
                NombreMenuEnum.EVENTOS.getDescripcion()));
        nombreMenus.add(NombreMenu.create(
                NombreMenuEnum.NOTIFICACIONES.getNombre(),
                NombreMenuEnum.NOTIFICACIONES.getDescripcion()));
        nombreMenus.add(NombreMenu.create(
                NombreMenuEnum.CUENTA.getNombre(),
                NombreMenuEnum.CUENTA.getDescripcion()));
        nombreMenus.add(NombreMenu.create(
                NombreMenuEnum.AYUDA.getNombre(),
                NombreMenuEnum.AYUDA.getDescripcion()));
    }

    private static void initVistasMenu(){
        vistaMenus = new ArrayList<>();
        vistaMenus.add(VistaMenu.builder()
                .setActividadMenu(HojaRutaActivity.class.getSimpleName())
                .setHexColor("")
                .setNombreMenu(nombreMenus.get(0))
                .setIcon("")
                .build());
        vistaMenus.add(VistaMenu.builder()
                .setActividadMenu(EventosActivity.class.getSimpleName())
                .setHexColor("")
                .setNombreMenu(nombreMenus.get(1))
                .setIcon("")
                .build());
        vistaMenus.add(VistaMenu.builder()
                .setActividadMenu(NotificacionesActivity.class.getSimpleName())
                .setHexColor("")
                .setNombreMenu(nombreMenus.get(2))
                .setIcon("")
                .build());
        vistaMenus.add(VistaMenu.builder()
                .setActividadMenu(CuentaActivity.class.getSimpleName())
                .setHexColor("")
                .setNombreMenu(nombreMenus.get(3))
                .setIcon("")
                .build());
        vistaMenus.add(VistaMenu.builder()
                .setActividadMenu(AyudaActivity.class.getSimpleName())
                .setHexColor("")
                .setNombreMenu(nombreMenus.get(4))
                .setIcon("")
                .build());
    }

    private static void initMenus(){
        menus = new ArrayList<>();
        menus.add(Menu.create(vistaMenus.get(0)));
        menus.add(Menu.create(vistaMenus.get(1)));
        menus.add(Menu.create(vistaMenus.get(2)));
        menus.add(Menu.create(vistaMenus.get(3)));
        menus.add(Menu.create(vistaMenus.get(4)));
    }

    public static List<Menu> getMenus(){
        initNombresMenu();
        initVistasMenu();
        initMenus();
        return menus;
    }

    private enum NombreMenuEnum{
        HOJADERUTA("Hoja de ruta", "Hoja de Ruta"),
        EVENTOS("Eventos","Eventos"),
        NOTIFICACIONES("Notificaciones", "Notificaciones"),
        CUENTA("Cuenta", "Cuenta"),
        AYUDA("Ayuda", "Ayuda");

        private String nombre;
        private String descripcion;

        NombreMenuEnum(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public String getNombre() {
            return nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }
}
