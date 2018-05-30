package com.silverhetch.aura.permission

/**
 * Callback for permission checking
 */
interface PermissionCallback {
    /**
     * Permission granted.
     */
    fun onPermissionGranted()

    /**
     * Promotion require which user had decline the previous promotion.
     */
    fun showPermissionRationale(permission: Array<String>)

    /**
     * The permission is permanently decline by checking the "Never ask again".
     */
    fun onPermissionPermanentlyDecline(permission: Array<String>)
}
