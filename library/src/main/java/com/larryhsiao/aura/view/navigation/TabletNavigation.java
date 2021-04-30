package com.larryhsiao.aura.view.navigation;

import android.content.res.Configuration;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;

/**
 * Created by Larry Hsiao on 2017/6/25.
 */

public class TabletNavigation implements Navigation {
    private final ActionBar actionBar;

    public TabletNavigation(ActionBar actionBar) {
        this.actionBar = actionBar;
    }

    @Override
    public void singlePageMode() {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void normalMode() {
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void closeDrawers() {
    }

    @Override
    public void syncState() {
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
