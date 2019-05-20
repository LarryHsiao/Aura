package com.silverhetch.aura

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.permission.PermissionCallback
import com.silverhetch.aura.permission.Permissions
import com.silverhetch.aura.permission.PermissionsImpl
import com.silverhetch.aura.permission.PhantomPermission
import com.silverhetch.aura.view.fab.FabBehavior
import com.silverhetch.aura.view.fab.FabControl
import com.silverhetch.aura.view.fab.FabControlImpl
import com.silverhetch.aura.view.fab.PhantomFabControl

/**
 * Aura style Activity. Inherit this class for all Aura feature.
 */
abstract class AuraActivity : AppCompatActivity(), FabControl, PermissionCallback {
    private companion object {
        private const val REQUEST_CODE_PERMISSION_SETTING_REDIRECT = 4521
    }

    private var permissionObj: Permissions = PhantomPermission()
    private var fabControl: FabControl = PhantomFabControl()

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackControl && it.isVisible && it.onBackPress()) {
                return
            }
        }
        super.onBackPressed()
    }

    override fun attachFab(fabBehavior: FabBehavior?) {
        fabControl.attachFab(fabBehavior)
    }

    override fun detachFab(fabBehavior: FabBehavior?) {
        fabControl.detachFab(fabBehavior)
    }

    /**
     * Setup [FloatingActionButton] on Activity
     */
    fun setupFabControl(fab: FloatingActionButton) {
        fabControl = FabControlImpl(fab)
    }

    /**
     * Convenient method for requesting permissions implemented with [PermissionsImpl].
     *
     * @see [AuraActivity.onPermissionGranted]
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
        AlertDialog.Builder(this)
                .setMessage(R.string.permission_pleaseAllowPermission)
                .setOnCancelListener { finish() }
                .setNegativeButton(R.string.app_cancel) { _, _ ->
                    finish()
                }.setPositiveButton(R.string.app_confirm) { _, _ ->
                    val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", packageName, null)
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
        AlertDialog.Builder(this)
                .setMessage(R.string.permission_pleaseAllowPermission)
                .setOnCancelListener { finish() }
                .setPositiveButton(R.string.app_confirm) { _, _ ->
                    permissionObj.requestPermissions()
                }.setNegativeButton(R.string.app_cancel) { _, _ ->
                    finish()
                }.show()
    }
}