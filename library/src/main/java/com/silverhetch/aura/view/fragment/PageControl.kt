package com.silverhetch.aura.view.fragment

import androidx.fragment.app.Fragment
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.AuraFragment

/**
 * Fragment page control, this class used for changing the main fragment on screen.
 *
 * This is the alternative approach to Navigation Control which is part of Android Jetpack.
 *
 * Which to use:
 *  - If you consider a fancy IDE feature ready navigation, go with Android Jetpack.
 *  - Or, you just need the working simple old-fashion approach of page navigation, use [PageControl] would be good choice.
 *
 * @see AuraFragment
 * @see AuraActivity
 */
interface PageControl {
    /**
     * Navigate to given fragment with back stack.
     */
    fun nextPage(fragment: Fragment)

    /**
     * Navigate to given fragment without back state.
     */
    fun rootPage(fragment: Fragment)
}