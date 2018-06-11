package com.silverhetch.aura.permission

import android.content.Context

internal interface PermissionAPI {
    fun context(): Context
    fun requestPermissions(permissions: Array<String>, requestCode: Int)
    fun shouldShowRequestPermissionRationale(permission: String): Boolean
}