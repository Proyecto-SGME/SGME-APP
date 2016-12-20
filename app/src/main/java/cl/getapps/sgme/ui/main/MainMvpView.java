package cl.getapps.sgme.ui.main;

import java.util.List;

import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showMenus(List<Menu> menus);

    void showMenusVacios();

    void showError();

}
