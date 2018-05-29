package com.silverhetch.util.permission

import android.app.Activity
import android.support.v4.app.ActivityCompat

import java.util.ArrayList

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.support.v4.content.ContextCompat.checkSelfPermission

/**
 * Utility class for checking permission
 */
class Permissions(private val activity: Activity, private val callback: Callback, private val permissions: Array<String>) {
    companion object {
        private const val REQUEST_CODE = 2134
    }

    /**
     * Check permission for given permissions.
     *
     * @return true if all permission granted, otherwise false and launch promotion provided by Android.
     */
    fun isPermissionGranted(): Boolean {
        for (permission in permissions) {
            if (checkSelfPermission(activity, permission) != PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    /**
     * Request the permission for app.
     */
    fun requestPermissions() {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
    }

    private fun checkRationaleRequired(): Boolean {
        permissionsRequireRationale().let { permissions ->
            return if (permissions.isNotEmpty()) {
                callback.rationaleRequired(permissions)
                true
            } else {
                false
            }
        }
    }

    private fun permissionsRequireRationale(): Array<String> {
        val rationalNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                rationalNeeded.add(permission)
            }
        }
        return rationalNeeded.toTypedArray()
    }

    /**
     * Catch the result from [Activity.onRequestPermissionsResult]
     */
    fun handleResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            val noPermission = ArrayList<String>()
            for (index in permissions.indices) {
                if (grantResults[index] != PERMISSION_GRANTED) {
                    noPermission.add(permissions[index])
                }
            }
            if (noPermission.size > 0) {
                if (checkRationaleRequired()) {
                    callback.onPermissionDecline(noPermission.toTypedArray())
                } else {
                    callback.onPermissionPermanentlyDecline(noPermission.toTypedArray())
                }
            } else {
                callback.onPermissionGranted()
            }
        }
    }
}
