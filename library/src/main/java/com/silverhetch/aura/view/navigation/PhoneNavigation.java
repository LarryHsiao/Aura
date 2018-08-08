package com.silverhetch.aura.view.navigation;

import android.app.Activity;
import android.content.res.Configuration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;


import com.silverhetch.aura.R;

import static androidx.core.view.GravityCompat.START;
import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
import static androidx.drawerlayout.widget.DrawerLayout.LOCK_MODE_UNLOCKED;

/**
 * Created by Larry Hsiao on 2017/6/25.
 */

public class PhoneNavigation implements Navigation {
    private final ActionBarDrawerToggle drawerToggle;
    private final DrawerLayout drawerLayout;

    public PhoneNavigation(Activity activity, DrawerLayout drawerLayout, ActionBar actionBar) {
        this.drawerLayout = drawerLayout;
        this.drawerToggle = new ActionBarDrawerToggle(activity, drawerLayout,
                R.string.app_drawerOpen,
                R.string.app_drawerClose
        );
        drawerLayout.addDrawerListener(drawerToggle);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    @Override
    public void singlePageMode() {
        drawerLayout.setDrawerLockMode(LOCK_MODE_LOCKED_CLOSED);
        drawerToggle.setDrawerIndicatorEnabled(false);
    }

    @Override
    public void normalMode() {
        drawerLayout.setDrawerLockMode(LOCK_MODE_UNLOCKED);
        drawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    public void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void syncState() {
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return drawerToggle.onOptionsItemSelected(item);
    }

    @Override
    public boolean onBackPressed() {
        if (drawerLayout.isDrawerOpen(START)) {
            drawerLayout.closeDrawer(START);
            return true;
        }
        return false;
    }


}
