package com.silverhetch.aurademo

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.*
import com.silverhetch.util.permission.Permissions
import com.silverhetch.util.permission.PermissionCallback


class MainActivity : AppCompatActivity(), PermissionCallback {
    private val permissions = Permissions(this, this, arrayOf(
            WRITE_EXTERNAL_STORAGE
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listview = ListView(this)
        listview.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        setContentView(listview)

        listview.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arrayOf(
                        "Color fragment",
                        "Request permissions"
                ))
        listview.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            when (position) {
                0 -> {
                    startActivity(Intent(this, ColorFragmentDemoActivity::class.java))
                }
                1 -> {
                    permissions.requestPermissions()
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        this.permissions.handleResult(requestCode, permissions, grantResults)
    }

    override fun onPermissionGranted() {
        Toast.makeText(this, "Granted", Toast.LENGTH_LONG).show()
    }

    override fun onPermissionDecline(permission: Array<String>) {
        Toast.makeText(this, "Decline", Toast.LENGTH_LONG).show()
    }

    override fun rationaleRequired(permission: Array<String>) {
        AlertDialog.Builder(this)
                .setMessage("Permission required!")
                .setPositiveButton("OK") { dialog, which ->
                    permissions.requestPermissions()
                }.setNegativeButton("Cancel", { dialog, which ->

                }).create().show()
    }

    override fun onPermissionPermanentlyDecline(permission: Array<String>) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}
