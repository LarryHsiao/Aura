package com.silverhetch.aura.view.activity

import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.R
import com.silverhetch.clotho.Source

/**
 * Source to apply transparent theme and disable animation to Activity.
 */
class TransparentSource(private val activity: AppCompatActivity) : Source<Unit> {
    override fun value() {
        activity.setTheme(R.style.Transparent)
        activity.overridePendingTransition(0, 0)
    }
}