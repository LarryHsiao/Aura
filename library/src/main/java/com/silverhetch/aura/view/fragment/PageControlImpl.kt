package com.silverhetch.aura.view.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * implementation of [PageControl].
 */
class PageControlImpl(private val manager: FragmentManager, @IdRes private val containerId: Int) : PageControl {
    override fun nextPage(fragment: Fragment) {
        manager.beginTransaction()
            .replace(containerId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun rootPage(fragment: Fragment) {
        while (manager.backStackEntryCount > 0) {
            manager.popBackStackImmediate()
        }
        manager.beginTransaction()
            .replace(containerId, fragment)
            .commit()
    }
}