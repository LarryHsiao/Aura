package com.silverhetch.util.permission

/**
 * Callback for permission checking
 */
interface PermissionCallback {
    /**
     * Permission granted.
     */
    fun onPermissionGranted()

    /**
     * Permissino decline
     *
     * @param permission Permissions declined.
     */
    fun onPermissionDecline(permission: Array<String>)

    /**
     * Promotion require.
     */
    fun rationaleRequired(permission: Array<String>)

    /**
     * The permission is permanently decline by checking the "Never ask again".
     */
    fun onPermissionPermanentlyDecline(permission: Array<String>)
}
