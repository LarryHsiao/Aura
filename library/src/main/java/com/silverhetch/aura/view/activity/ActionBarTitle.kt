package com.silverhetch.aura.view.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.larryhsiao.clotho.Action

/**
 * Action to change ActionBar's title.
 */
class ActionBarTitle(
    private val activity: Activity?,
    private val title: String
) : Action {
    override fun fire() {
        if (activity is AppCompatActivity) {
            activity.supportActionBar?.title = title
        }
    }
}