package com.silverhetch.aura

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import com.silverhetch.aura.permission.PermissionCallback
import com.silverhetch.aura.permission.Permissions
import com.silverhetch.aura.permission.PermissionsImpl
import com.silverhetch.aura.permission.PhantomPermission
import com.silverhetch.aura.view.fab.FabControl
import com.silverhetch.aura.view.fab.PhantomFabControl

/**
 * Convenient Fragment class to inherit all Aura style interfaces.
 */
abstract class AuraFragment : Fragment(), Events, PermissionCallback {
    private companion object {
        private const val REQUEST_CODE_PERMISSION_SETTING_REDIRECT = 4521
    }

    private var fabControl: FabControl = PhantomFabControl()
    private var permissionObj: Permissions = PhantomPermission()
    override fun onBackPress(): Boolean {
        childFragmentManager.fragments.forEach {
            if (it is Events && it.isVisible && it.onBackPress()) {
                return true
            }
        }
        return false
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity.let {
            if (it is FabControl) {
                fabControl = it
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        fabControl = PhantomFabControl()
    }

    fun fabControl() = fabControl

    /**
     * Convenient method for requesting permissions implemented with [PermissionsImpl].
     *
     * @see [AuraFragment.onPermissionGranted]
     */
    fun requestPermissionsByObj(permissions: Array<String>) {
        permissionObj = PermissionsImpl(this, this, permissions)
        permissionObj.requestPermissions()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PERMISSION_SETTING_REDIRECT) {
            if (!permissionObj.isPermissionGranted()) {
                permissionObj.requestPermissions()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionObj.handleResult(requestCode, permissions, grantResults)
    }

    /**
     * Override this method to catch permission granted event.
     */
    override fun onPermissionGranted() {
    }

    /**
     * Exit this Activity by default if user decline the promotion.
     */
    override fun onPermissionPermanentlyDecline(permission: Array<String>) {
        AlertDialog.Builder(context!!)
                .setMessage(R.string.permission_pleaseAllowPermission)
                .setOnCancelListener { activity!!.finish() }
                .setNegativeButton(R.string.app_cancel) { _, _ ->
                    activity!!.finish()
                }.setPositiveButton(R.string.app_confirm) { _, _ ->
                    val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity!!.packageName, null)
                    )
                    startActivityForResult(intent, REQUEST_CODE_PERMISSION_SETTING_REDIRECT)
                }.show()
    }

    /**
     * Shows alert dialog to make sure user not want to grant the permissions.
     *
     * Override this method to do project specific behavior.
     */
    override fun showPermissionRationale(permission: Array<String>) {
        AlertDialog.Builder(context!!)
                .setMessage(R.string.permission_pleaseAllowPermission)
                .setOnCancelListener { activity!!.finish() }
                .setPositiveButton(R.string.app_confirm) { _, _ ->
                    permissionObj.requestPermissions()
                }.setNegativeButton(R.string.app_cancel) { _, _ ->
                    activity!!.finish()
                }.show()
    }
}