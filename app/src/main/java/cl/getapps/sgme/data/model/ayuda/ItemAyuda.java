package cl.getapps.sgme.data.model.ayuda;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Creado por ChristopherR el 20-12-16.
 */

public class ItemAyuda extends ExpandableGroup<Ayuda> {
    public ItemAyuda(String title, List<Ayuda> items) {
        super(title, items);
    }
}
