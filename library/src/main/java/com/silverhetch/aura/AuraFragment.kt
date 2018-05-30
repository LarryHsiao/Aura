package com.silverhetch.aura

import android.support.v4.app.Fragment

/**
 * Convenient Fragment class to inherit all Aura style interfaces.
 */
abstract class AuraFragment : Fragment(), Events {
    override fun onBackPress(): Boolean {
        childFragmentManager.fragments.forEach {
            if (it is Events && it.isVisible && it.onBackPress()) {
                return true
            }
        }
        return false
    }
}