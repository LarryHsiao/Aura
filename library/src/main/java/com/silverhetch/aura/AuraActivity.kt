package com.silverhetch.aura

import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import com.silverhetch.aura.view.fab.FabBehavior
import com.silverhetch.aura.view.fab.FabControl
import com.silverhetch.aura.view.fab.FabControlImpl
import com.silverhetch.aura.view.fab.PhantomFabControl

/**
 * Aura style Activity. Inherit this class for all Aura feature.
 */
abstract class AuraActivity : AppCompatActivity(), FabControl {
    private var fabControl: FabControl = PhantomFabControl()
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is Events && it.isVisible && it.onBackPress()) {
                return
            }
        }
        super.onBackPressed()
    }

    override fun attachFab(fabBehavior: FabBehavior?) {
        fabControl.attachFab(fabBehavior)
    }

    override fun detachFab(fabBehavior: FabBehavior?) {
        fabControl.detachFab(fabBehavior)
    }

    fun setupFabControl(fab: FloatingActionButton) {
        fabControl = FabControlImpl(fab)
    }
}