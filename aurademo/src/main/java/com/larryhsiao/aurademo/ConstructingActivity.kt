package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ListPopupWindow.MATCH_PARENT
import androidx.core.view.ViewCompat
import com.larryhsiao.aura.phantom.ConstructingFragment

/**
 * Activity contains a [ConstructingFragment] for demo purpose.
 */
class ConstructingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootId = ViewCompat.generateViewId()
        setContentView(FrameLayout(this).apply {
            id = rootId
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        })

        supportFragmentManager.beginTransaction()
                .replace(rootId, ConstructingFragment())
                .commit()
    }
}