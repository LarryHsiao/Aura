package com.silverhetch.aura.overlay

import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION_CODES.M
import android.provider.Settings
import com.silverhetch.aura.overlay.OverlayPermission.Callback

/**
 * Implementation of overlay permission object.
 *
 * Note this class do NOT handles api versions.
 */
@TargetApi(M)
class OverlayPermissionImpl(private val activity: Activity, private val callback: Callback) : OverlayPermission {
    companion object {
        private const val REQUEST_CODE = 4791
    }

    override fun isGranted(): Boolean {
        return Settings.canDrawOverlays(activity)
    }

    override fun request() {
        if (isGranted()) {
            callback.onSuccess()
            return
        }
        activity.startActivityForResult(Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + activity.applicationContext.packageName)
        ), REQUEST_CODE)
    }

    override fun handleResult(requestCode: Int) {
        if (REQUEST_CODE == requestCode) {
            if (isGranted()) {
                callback.onSuccess()
            } else {
                callback.onFailure()
            }
        }
    }

}