package com.silverhetch.aura.permission.wrapper

import android.content.Context
import android.support.v4.app.Fragment

/**
 * Wrapper for Fragment
 */
internal class FragmentPermission(private val fragment: Fragment) : PermissionAPI {
    override fun context(): Context {
        return fragment.context!!
    }

    override fun requestPermissions(permissions: Array<String>, requestCode: Int) {
        fragment.requestPermissions(permissions, requestCode)
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return fragment.shouldShowRequestPermissionRationale(permission)
    }
}