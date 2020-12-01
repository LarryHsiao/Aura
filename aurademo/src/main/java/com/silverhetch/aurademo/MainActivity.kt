package com.silverhetch.aurademo

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.Intent.ACTION_GET_CONTENT
import android.content.Intent.EXTRA_MIME_TYPES
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.silverhetch.aurademo.*
import com.silverhetch.aura.intent.ChooserIntent
import com.silverhetch.aura.intent.PickerIntent
import com.silverhetch.aura.media.BitmapStream
import com.silverhetch.aura.permission.PermissionCallback
import com.silverhetch.aura.permission.PermissionsImpl
import com.silverhetch.aura.sercurity.IsDeviceSecure
import com.silverhetch.aura.uri.UriMimeType
import com.silverhetch.aura.view.TintDrawable
import com.silverhetch.aura.view.activity.SystemUIColor
import com.silverhetch.aura.view.activity.statusbar.StatusBarColor
import com.silverhetch.aura.view.bitmap.DrawableBitmap
import com.silverhetch.aura.view.images.ImageActivity
import com.silverhetch.aura.view.images.pager.PagerImageActivity
import com.silverhetch.aurademo.fabcontrol.FabControlDemoActivity
import com.silverhetch.aurademo.media.MediaPlayerDemoActivity
import com.silverhetch.aurademo.resultpipe.FragmentResultDemoActivity
import com.larryhsiao.clotho.file.ToFile
import java.io.File

/**
 * Entry point of demo app.
 */
class MainActivity : AppCompatActivity(), PermissionCallback {
    companion object {
        private const val REQUEST_CODE_SHOW_MIME_TYPE = 1000
    }

    private val permissions = PermissionsImpl(
        this,
        this,
        arrayOf(WRITE_EXTERNAL_STORAGE)
    )

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
                "Media Player",
                "Fullscreen Demo",
                "Overlay Demo",
                "Ripple Demo",
                "Brightness Demo",
                "Image demo",
                "Constructing Fragment",
                "Full screen dialog",
                "Aura Progress bar",
                "WebView ",
                "Status bar color light",
                "Status bar color dark",
                "Input Dialog Demo Activity",
                "PageControl demo Activity",
                "Mneme ImageGrid demo",
                "Multi purpose Chooser",
                "FragmentResult",
                "Tint Dot Demo",
                "Audio Recording",
                "UriMimeType",
                "System UI color light",
                "System UI color dark",
                "BioAuth",
                "PcikerMultipleMimeType",
                "Clickable span",
                "Empty List Adapter",
                "Alert Dialog",
                "Slider",
                "BlurTextView",
                "Is device secure?",
                "DeepLink client"
            ))
        listview.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, _ ->
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
                        "https://dummyimage.com/600x400/000/fff"
                    )))
                }
                6 -> {
                    startActivity(Intent(view.context, LogDemoActivity::class.java))
                }
                7 -> {
                    startActivity(Intent(view.context, BlurImageDemoActivity::class.java))
                }
                8 -> {
                    startActivity(Intent(view.context, MediaPlayerDemoActivity::class.java).apply {
                        data = Uri.parse("https://larryhsiao.com:13000/sample.mkv")
                    })
                }
                9 -> {
                    startActivity(Intent(view.context, FullScreenActivity::class.java))
                }
                10 -> {
                    startActivity(Intent(view.context, OverlayPermissionDemoActivity::class.java))
                }
                11 -> {
                    startActivity(Intent(view.context, RippleBackgroundActivity::class.java))
                }
                12 -> {
                    startActivity(Intent(view.context, BrightnessDemoActivity::class.java))
                }
                13 -> {
                    startActivity(ImageActivity.newIntent(view.context, "https://dummyimage.com/600x400/000/fff"))
                }
                14 -> {
                    startActivity(Intent(view.context, ConstructingActivity::class.java))
                }
                15 -> {
                    SampleFullScreenDialogFragment().show(supportFragmentManager, null)
                }
                16 -> {
                    startActivity(Intent(view.context, AuraProgressBarActivity::class.java))
                }
                17 -> {
                    startActivity(Intent(view.context, WebViewDemoActivity::class.java))
                }
                18 -> {
                    StatusBarColor(window, Color.parseColor("#03A9F4"), 0.6f).fire()
                }
                19 -> {
                    StatusBarColor(window, Color.parseColor("#000000")).fire()
                }
                20 -> {
                    startActivity(Intent(view.context, InputDialogDemoActivity::class.java))
                }
                21 -> {
                    startActivity(Intent(view.context, PageControlDemoActivity::class.java))
                }
                22 -> {
                    startActivity(Intent(view.context, MnemeImageGridDemoActivity::class.java))
                }
                23 -> {
                    startActivity(ChooserIntent(
                        view.context,
                        "Title here",
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE),
                        Intent(ACTION_GET_CONTENT).also {
                            it.type = "images/*"
                        }
                    ).value())
                }
                24 -> {
                    startActivity(
                        Intent(
                            view.context,
                            FragmentResultDemoActivity::class.java
                        )
                    )
                }
                25 -> {
                    startActivity(
                        ImageActivity.newIntent(
                            view.context,
                            File(
                                filesDir,
                                "tempImage"
                            ).also { dest ->
                                ToFile(
                                    BitmapStream(
                                        DrawableBitmap(TintDrawable(
                                            resources.getDrawable(R.drawable.ic_cross),
                                            Color.CYAN
                                        ).value()).value()
                                    ).value(),
                                    dest
                                ) {}.fire()
                            }.toURI().toString()
                        )
                    )
                }
                26 -> {
                    startActivity(
                        Intent(
                            view.context,
                            AudioRecordingActivity::class.java
                        )
                    )
                }
                27 -> {
                    startActivityForResult(
                        Intent(Intent.ACTION_GET_CONTENT).also {
                            it.type = "image/*"
                            it.putExtra(EXTRA_MIME_TYPES, arrayOf("image/*", "video/*", "audio/*"))
                        },
                        REQUEST_CODE_SHOW_MIME_TYPE
                    )
                }
                28 -> {
                    SystemUIColor(window, Color.WHITE).fire()
                }
                29 -> {
                    SystemUIColor(window, Color.BLACK).fire()
                }
                30 -> {
                    startActivity(
                        Intent(view.context, BioAuthDemoActivity::class.java)

                    )
                }
                31 -> {
                    startActivity(
                        PickerIntent(
                            "image/*",
                            "audio/*",
                            "video/*"
                        ).value()
                    )
                }
                32 -> {
                    startActivity(
                        Intent(this, ClickableSpanActivity::class.java)
                    )
                }
                33 -> {
                    startActivity(
                        Intent(this, EmptyListAdapterActivity::class.java)
                    )
                }
                34 -> {
                    startActivity(
                        Intent(this, AlertDemoActivity::class.java)
                    )
                }
                35 -> {
                    startActivity(
                        Intent(this, SliderDemoActivity::class.java)
                    )
                }
                36-> startActivity(
                    Intent(this, BlurTextViewDemoActivity::class.java)
                )
                37 -> Toast.makeText(
                    this,
                    "Device secured: " + IsDeviceSecure(this).value(),
                    Toast.LENGTH_LONG
                ).show()
                38 -> startActivity(
                    Intent(this, DeepLinkClientDemoActivity::class.java)
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
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

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SHOW_MIME_TYPE) {
            val mimeType = UriMimeType(this, data?.data?.toString()
                ?: "").value()
            Toast.makeText(
                this,
                "MimeType: $mimeType",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
