package com.silverhetch.util.view.menu;

import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by mikes on 9/24/2017.
 */

public class MenuControlImpl implements MenuControl {
    private final Menu menu;

    public MenuControlImpl(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void disable() {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            item.collapseActionView();
            item.setVisible(false);
        }
    }

    @Override
    public void enable() {
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setVisible(true);
        }
    }
}
