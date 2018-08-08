package com.silverhetch.aura.permission

import android.app.Activity

interface Permissions {

    /**
     * Check permission for given permissions.
     *
     * @return true if all permission granted, otherwise false and launch promotion provided by Android.
     */
    fun isPermissionGranted(): Boolean

    /**
     * Request the permission for app.
     */
    fun requestPermissions()

    /**
     * Catch the result from [Activity.onRequestPermissionsResult]
     */
    fun handleResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
}