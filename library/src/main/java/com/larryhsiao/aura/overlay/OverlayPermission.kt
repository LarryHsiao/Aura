package com.larryhsiao.aura.overlay

/**
 * Overlay Permission object. Which handles permission.
 *
 * Note: Declare permission in Manifest is required. (android.permission.SYSTEM_ALERT_WINDOW)
 */
interface OverlayPermission {
    /**
     * Callback method.
     */
    interface Callback {
        /**
         * Invoked when overlay permission granted.
         */
        fun onSuccess()

        /**
         * Invoked when overlay permission is not success(Just disabled).
         */
        fun onFailure()
    }

    /**
     * Determine if overlay permission is granted
     */
    fun isGranted(): Boolean

    /**
     * Request the permission
     */
    fun request()

    /**
     * Handles result from permission activity.
     */
    fun handleResult(requestCode: Int)
}