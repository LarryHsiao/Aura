package com.silverhetch.aura.view.activity.brightness

import android.app.Activity

/**
 * Adjust the brightness which only effected in same Activity.
 *
 * The value may be negative number which indicated as using system preferred setting.
 */
class InAppBrightness(private val activity: Activity) : Brightness {
    override fun set(new: Float) {
        var value = new
        if (value > 1) {
            value = 1f
        }
        if (value < 0) {
            value = 0f
        }
        activity.window.attributes = activity.window.attributes.apply { screenBrightness = value }
    }

    override fun value(): Float {
        return activity.window.attributes.screenBrightness
    }
}