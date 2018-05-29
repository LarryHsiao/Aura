package com.silverhetch.util

import android.support.v7.app.AppCompatActivity

/**
 * Aura style Activity. Inherit this class for all Aura feature.
 */
class AuraActivity : AppCompatActivity() {
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            // NOTE: for now don`t care the priority of fragments.
            if (it is Events && it.isVisible && it.onBackPress()) {
                return
            }
        }
        super.onBackPressed()
    }
}