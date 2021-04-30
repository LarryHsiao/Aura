package com.larryhsiao.aura.view.navigation;

import android.content.res.Configuration;
import android.view.MenuItem;

/**
 * Created by Larry Hsiao on 2017/6/25.
 */

public interface Navigation extends NavigationSlid {
    boolean onBackPressed();

    void closeDrawers();

    void syncState();

    void onConfigurationChanged(Configuration newConfig);

    boolean onOptionsItemSelected(MenuItem item);
}
