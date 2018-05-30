package com.silverhetch.aura

import android.support.v7.app.AppCompatActivity

/**
 * Aura style Activity. Inherit this class for all Aura feature.
 */
abstract class AuraActivity : AppCompatActivity() {
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is Events && it.isVisible && it.onBackPress()) {
                return
            }
        }
        super.onBackPressed()
    }
}