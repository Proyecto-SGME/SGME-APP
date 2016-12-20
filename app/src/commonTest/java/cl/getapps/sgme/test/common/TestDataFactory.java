package cl.getapps.sgme.test.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.data.model.NombreMenu;
import cl.getapps.sgme.data.model.VistaMenu;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static Menu makeRibot(String uniqueSuffix) {
        return Menu.create(makeProfile(uniqueSuffix));
    }

    public static List<Menu> makeListRibots(int number) {
        List<Menu> menus = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            menus.add(makeRibot(String.valueOf(i)));
        }
        return menus;
    }

    public static VistaMenu makeProfile(String uniqueSuffix) {
        return VistaMenu.builder()
                .setNombreMenu(makeName(uniqueSuffix))
                .setActividadMenu("actividadMenu" + uniqueSuffix + "@ribot.co.uk")
                .setHexColor("#0066FF")
                .build();
    }

    public static NombreMenu makeName(String uniqueSuffix) {
        return NombreMenu.create("NombreMenu-" + uniqueSuffix, "Surname-" + uniqueSuffix);
    }

}