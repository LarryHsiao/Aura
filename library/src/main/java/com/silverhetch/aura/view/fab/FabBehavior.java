package com.silverhetch.aura.view.fab;

import androidx.annotation.DrawableRes;

/**
 * Object to represent a behavior of a floating action button,
 */
public interface FabBehavior {
    /**
     * The drawable resource id, it is better to square.
     */
    @DrawableRes
    int icon();

    /**
     * The clicked event.
     */
    void onClick();
}
