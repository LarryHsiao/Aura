package com.silverhetch.aura

import android.content.Context
import android.support.v4.app.Fragment
import com.silverhetch.aura.view.fab.FabControl
import com.silverhetch.aura.view.fab.PhantomFabControl

/**
 * Convenient Fragment class to inherit all Aura style interfaces.
 */
abstract class AuraFragment : Fragment(), Events {
    private var fabControl: FabControl = PhantomFabControl()
    override fun onBackPress(): Boolean {
        childFragmentManager.fragments.forEach {
            if (it is Events && it.isVisible && it.onBackPress()) {
                return true
            }
        }
        return false
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity.let {
            if (it is FabControl) {
                fabControl = it
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        fabControl = PhantomFabControl()
    }

    fun fabControl() = fabControl
}