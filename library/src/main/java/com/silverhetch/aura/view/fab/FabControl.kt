package com.silverhetch.aura.view.fab

/**
 * Object that controls a floating action button on screen.
 */
interface FabControl {
    /**
     * Attach a new behavior with new [FabBehavior]
     */
    fun attachFab(fabBehavior: FabBehavior)

    /**
     * Detach from fab.
     */
    fun detachFab()
}
