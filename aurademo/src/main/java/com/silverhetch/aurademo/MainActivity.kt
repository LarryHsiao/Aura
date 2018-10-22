package com.silverhetch.aurademo

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aura.permission.PermissionCallback
import com.silverhetch.aura.permission.PermissionsImpl
import com.silverhetch.aura.view.images.pager.PagerImageActivity
import com.silverhetch.aurademo.fabcontrol.FabControlDemoActivity


class MainActivity : AppCompatActivity(), PermissionCallback {
    private val permissions = PermissionsImpl(this, this, arrayOf(
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
                        "Request permissions",
                        "FabControl Demo",
                        "AuraActivity: Permission request",
                        "AuraFragment: Permission request",
                        "Remote image pager",
                        "Log View demo",
                        "Blur Effect",
                        "Media Player"
                ))
        listview.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    startActivity(Intent(this, ColorFragmentDemoActivity::class.java))
                }
                1 -> {
                    permissions.requestPermissions()
                }
                2 -> {
                    startActivity(Intent(this, FabControlDemoActivity::class.java))
                }
                3 -> {
                    startActivity(Intent(this, PermissionRequestAuraActivity::class.java))
                }
                4 -> {
                    startActivity(Intent(this, AuraFragmentPermissionActivity::class.java))
                }
                5 -> {
                    startActivity(PagerImageActivity.newIntent(this, arrayListOf(
                            "https://dummyimage.com/600x400/000/fff",
                            "https://dummyimage.com/600x400/fff/000",
                            "https://dummyimage.com/600x400/000/fff",
                            "https://dummyimage.com/600x400/fff/000",
                            "https://dummyimage.com/600x400/000/fff",
                            "https://dummyimage.com/600x400/fff/000",
                            "https://dummyimage.com/600x400/000/fff"
                    )))
                }
                6 -> {
                    startActivity(Intent(this@MainActivity, LogDemoActivity::class.java))
                }
                7 -> {
                    startActivity(Intent(this@MainActivity, BlurImageDemoActivity::class.java))
                }
                8 -> {
                    startActivity(Intent(this@MainActivity, MediaPlayerDemoActivity::class.java))
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

    override fun showPermissionRationale(permission: Array<String>) {
        AlertDialog.Builder(this)
                .setMessage("Permission required!")
                .setPositiveButton("OK") { _, _ ->
                    permissions.requestPermissions()
                }.setNegativeButton("Cancel") { _, _ ->
                }.create().show()
    }

    override fun onPermissionPermanentlyDecline(permission: Array<String>) {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)
    }
}
