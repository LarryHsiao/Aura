package com.silverhetch.util.permission

import android.app.Activity
import android.support.v4.app.ActivityCompat

import java.util.ArrayList

import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.support.v4.content.ContextCompat.checkSelfPermission

/**
 * Utility class for checking permission
 */
class PermissionChecking(private val activity: Activity, private vararg val permissions: String) {
    interface Callback {
        fun success()
        fun failed(noPermission: Array<String>)
    }

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
    fun requestPermisssion() {
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE)
    }

    /**
     * Catch the result from [Activity.onRequestPermissionsResult]
     *
     * @param callback     callback for success and failed, either method invoked if requestCode is
     * not the same as [REQUEST_CODE]
     */
    fun handleResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray, callback: Callback) {
        if (requestCode == REQUEST_CODE) {
            val noPermission = ArrayList<String>()
            for (index in permissions.indices) {
                if (grantResults[index] != PERMISSION_GRANTED) {
                    noPermission.add(permissions[index])
                }
            }
            if (noPermission.size > 0) {
                callback.failed(noPermission.toTypedArray())
            } else {
                callback.success()
            }
        }
    }
}
