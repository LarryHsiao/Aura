package com.larryhsiao.aurademo

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import com.larryhsiao.aura.view.animation.RippleBackground
import com.larryhsiao.aura.view.animation.RippleBackgroundBorderless
import com.larryhsiao.aura.view.measures.SP

/**
 * Demo ripple effect which fetch the resource id from [RippleBackground].
 */
class RippleBackgroundActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this).let { root ->
            root.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            root.addView(ImageView(this).let { imageView ->
                imageView.layoutParams = FrameLayout.LayoutParams(
                        SP(this, 50f).px().toInt(),
                        SP(this, 50f).px().toInt()
                ).apply { gravity = Gravity.CENTER }
                imageView.isClickable = true
                imageView.setImageResource(android.R.drawable.alert_dark_frame)
                imageView.setBackgroundResource(
                        RippleBackgroundBorderless(this).value()
                )
                imageView
            })
            root
        })
    }
}