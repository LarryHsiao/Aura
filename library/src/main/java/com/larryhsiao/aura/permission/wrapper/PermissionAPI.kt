package com.larryhsiao.aura.permission.wrapper

import android.content.Context

/**
 * Wrapper to wrap Activity/Fragment`s permission requesting methods.
 */
internal interface PermissionAPI {
    fun context(): Context
    fun requestPermissions(permissions: Array<String>, requestCode: Int)
    fun shouldShowRequestPermissionRationale(permission: String): Boolean
}