package com.silverhetch.util.view.navigation;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;


import com.silverhetch.util.R;

import static android.support.v4.view.GravityCompat.START;
import static android.support.v4.widget.DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
import static android.support.v4.widget.DrawerLayout.LOCK_MODE_UNLOCKED;

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
