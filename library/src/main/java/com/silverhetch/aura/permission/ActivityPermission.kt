package com.silverhetch.aura.permission

import android.app.Activity
import android.content.Context
import android.support.v4.app.ActivityCompat

internal class ActivityPermission(private val activity: Activity) : PermissionAPI {
    override fun context(): Context {
        return activity
    }

    override fun requestPermissions(permissions: Array<String>, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)
    }
}