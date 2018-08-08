package com.silverhetch.aura.view.fab;

import androidx.annotation.DrawableRes;

/**
 * Created by larryhsiao on 2017/6/12.
 */

public interface FabBehavior {
    @DrawableRes
    int icon();
    void onClick();
}
