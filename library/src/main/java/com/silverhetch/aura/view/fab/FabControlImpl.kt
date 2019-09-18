package com.silverhetch.aura.view.fab

import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.silverhetch.aura.R

/**
 * Implementation of [FabControl]
 *
 *
 * Note: This class have integrate into [AuraActivity] and [AuraFragment]. Use both abstract classes to quick start.
 */
class FabControlImpl(private val fabView: FloatingActionButton) : FabControl {
    private var fabBehavior: FabBehavior? = null

    init {
        this.fabBehavior = null
    }

    override fun attachFab(fabBehavior: FabBehavior) {
        this.fabBehavior = fabBehavior
        if (fabView.scaleY == 1.0f) {
            showFabAfterDetachAnimation(fabBehavior)
        } else {
            showFab(fabBehavior)
        }
    }

    override fun detachFab() {
        this.fabBehavior = null
        fabView.hide()
        fabView.setOnClickListener(null)
        fabView.setImageDrawable(null)
    }

    private fun showFabAfterDetachAnimation(fabBehavior: FabBehavior) {
        fabView.postDelayed({ showFab(fabBehavior) }, fabView.context.resources.getInteger(R.integer.animation_delay_for_previous).toLong())
    }

    private fun showFab(fabBehavior: FabBehavior) {
        fabView.show()
        fabView.setImageResource(fabBehavior.icon())
        fabView.setOnClickListener {
            try {
                fabBehavior.onClick()
            } catch (e: Exception) {
                Toast.makeText(fabView.context, R.string.appError_unknown, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
