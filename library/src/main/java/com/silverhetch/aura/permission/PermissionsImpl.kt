package com.silverhetch.aura.permission

import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat.checkSelfPermission
import com.silverhetch.aura.permission.wrapper.ActivityPermission
import com.silverhetch.aura.permission.wrapper.FragmentPermission
import com.silverhetch.aura.permission.wrapper.PermissionAPI
import java.util.*

/**
 * Utility class for checking permission
 *
 * @todo #permission request code for granting permission.
 */
class PermissionsImpl : Permissions {
    companion object {
        private const val REQUEST_CODE = 2134
    }

    private val permission: PermissionAPI
    private val permissionCallback: PermissionCallback
    private val permissions: Array<String>

    constructor(activity: Activity, permissionCallback: PermissionCallback, permissions: Array<String>) : this(ActivityPermission(activity), permissionCallback, permissions)
    constructor(fragment: Fragment, permissionCallback: PermissionCallback, permissions: Array<String>) : this(FragmentPermission(fragment), permissionCallback, permissions)

    internal constructor(permission: PermissionAPI, permissionCallback: PermissionCallback, permissions: Array<String>) {
        this.permission = permission
        this.permissionCallback = permissionCallback
        this.permissions = permissions
    }

    override fun isPermissionGranted(): Boolean {
        for (permission in permissions) {
            if (checkSelfPermission(this.permission.context(), permission) != PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    override fun requestPermissions() {
        permission.requestPermissions(permissions, REQUEST_CODE)
    }

    private fun isRationaleRequired(): Boolean {
        return permissionsRequireRationale().isNotEmpty()
    }

    private fun permissionsRequireRationale(): Array<String> {
        val rationalNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (this.permission.shouldShowRequestPermissionRationale(permission)) {
                rationalNeeded.add(permission)
            }
        }
        return rationalNeeded.toTypedArray()
    }

    override fun handleResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE) {
            val permission = ArrayList<String>()
            for (index in permissions.indices) {
                if (grantResults[index] != PERMISSION_GRANTED) {
                    permission.add(permissions[index])
                }
            }
            if (permission.size > 0) {
                if (isRationaleRequired()) {
                    permissionCallback.showPermissionRationale(permission.toTypedArray())
                } else {
                    permissionCallback.onPermissionPermanentlyDecline(permission.toTypedArray())
                }
            } else {
                permissionCallback.onPermissionGranted()
            }
        }
    }
}
