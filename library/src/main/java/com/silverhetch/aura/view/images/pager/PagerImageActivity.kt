package com.silverhetch.aura.view.images.pager

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View.*
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.R

/**
 * Simple activity wrapper of [PagerImageFragment]
 */
class PagerImageActivity : AuraActivity() {
    companion object {
        private const val URI_IMAGE = "URI_IMAGE"

        /**
         * Factory method for the [Intent]
         */
        @JvmStatic
        fun newIntent(context: Context, list: ArrayList<String>): Intent {
            val intent = Intent(context, PagerImageActivity::class.java)
            intent.putExtra(URI_IMAGE, list)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frameLayout = FrameLayout(this)
        frameLayout.id = R.id.temp001
        frameLayout.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        setContentView(frameLayout)

        var visibility = SYSTEM_UI_FLAG_HIDE_NAVIGATION
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            visibility = visibility or SYSTEM_UI_FLAG_LAYOUT_STABLE
            visibility = visibility or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            visibility = visibility or SYSTEM_UI_FLAG_FULLSCREEN
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            visibility = visibility or SYSTEM_UI_FLAG_HIDE_NAVIGATION
            visibility = visibility or SYSTEM_UI_FLAG_FULLSCREEN
            visibility = visibility or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            visibility = visibility or SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        }
        window.decorView.systemUiVisibility = visibility

        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.temp001, PagerImageFragment.newInstance(
                intent.getStringArrayListExtra(URI_IMAGE) ?: ArrayList()
            ))
            commit()
        }
    }
}