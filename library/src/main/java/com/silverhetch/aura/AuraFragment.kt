package com.silverhetch.aura

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.silverhetch.aura.permission.PermissionCallback
import com.silverhetch.aura.permission.Permissions
import com.silverhetch.aura.permission.PermissionsImpl
import com.silverhetch.aura.permission.PhantomPermission
import com.silverhetch.aura.view.activity.ActionBarTitle
import com.silverhetch.aura.view.fab.FabBehavior
import com.silverhetch.aura.view.fab.FabControl
import com.silverhetch.aura.view.fab.PhantomFabControl
import com.silverhetch.aura.view.fragment.PageControl
import com.silverhetch.aura.view.fragment.callback.FragmentResult
import com.silverhetch.aura.view.fragment.callback.ResultPipe

/**
 * Convenient Fragment class to inherit all Aura style interfaces.
 */
abstract class AuraFragment : Fragment(),
    BackControl,
    PermissionCallback,
    PageControl,
    FabControl,
    ResultPipe {
    private companion object {
        private const val REQUEST_CODE_PERMISSION_SETTING_REDIRECT = 4521
    }

    private val fragmentResult = FragmentResult(this)
    private var fabControl: FabControl = PhantomFabControl()
    private var permissionObj: Permissions = PhantomPermission()
    override fun onBackPress(): Boolean {
        childFragmentManager.fragments.forEach {
            if (it is BackControl && it.isVisible && it.onBackPress()) {
                return true
            }
        }
        return false
    }

    override fun onAttach(context: Context) {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.permission_pleaseAllowPermission)
            .setOnCancelListener { requireActivity().finish() }
            .setNegativeButton(R.string.app_cancel) { _, _ ->
                requireActivity().finish()
            }.setPositiveButton(R.string.app_confirm) { _, _ ->
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts(
                        "package",
                        requireActivity().packageName,
                        null
                    )
                )
                startActivityForResult(
                    intent,
                    REQUEST_CODE_PERMISSION_SETTING_REDIRECT
                )
            }.show()
    }

    /**
     * Shows alert dialog to make sure user not want to grant the permissions.
     *
     * Override this method to do project specific behavior.
     */
    override fun showPermissionRationale(permission: Array<String>) {
        AlertDialog.Builder(requireContext())
            .setMessage(R.string.permission_pleaseAllowPermission)
            .setOnCancelListener { requireActivity().finish() }
            .setPositiveButton(R.string.app_confirm) { _, _ ->
                permissionObj.requestPermissions()
            }.setNegativeButton(R.string.app_cancel) { _, _ ->
                requireActivity().finish()
            }.show()
    }

    override fun nextPage(fragment: Fragment) {
        if (activity is PageControl) {
            (activity as PageControl).nextPage(fragment)
        }
    }

    override fun rootPage(fragment: Fragment) {
        if (activity is PageControl) {
            (activity as PageControl).rootPage(fragment)
        }
    }

    override fun attachFab(fabBehavior: FabBehavior) {
        fabControl.attachFab(fabBehavior)
    }

    override fun detachFab() {
        fabControl.detachFab()
    }

    override fun sendResult(requestCode: Int, resultCode: Int, data: Intent) {
        fragmentResult.sendResult(requestCode, resultCode, data)
    }

    /**
     * Convenient method for setting up default ActionBar`s title
     */
    fun setTitle(title: String) {
        ActionBarTitle(
            activity,
            title
        ).fire()
    }
}