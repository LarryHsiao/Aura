package com.silverhetch.aura.view.fab;

/**
 * Object that controls a floating action button on screen.
 */
public interface FabControl {
    /**
     * Attach a new behavior with new [FabBehavior]
     */
    void attachFab(FabBehavior fabBehavior);

    /**
     * Detach from fab.
     */
    void detachFab(FabBehavior fabBehavior);
}
