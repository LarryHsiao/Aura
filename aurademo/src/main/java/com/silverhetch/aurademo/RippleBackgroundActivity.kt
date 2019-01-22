package com.silverhetch.aurademo

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import android.widget.ImageView
import com.silverhetch.aura.view.animation.RippleBackground
import com.silverhetch.aura.view.animation.RippleBackgroundBorderless
import com.silverhetch.aura.view.measures.ScaleIndependentPixels

/**
 * Demo ripple effect which fetch the resource id from [RippleBackground].
 */
class RippleBackgroundActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this).apply {
            layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(ImageView(this@RippleBackgroundActivity).apply {
                layoutParams = FrameLayout.LayoutParams(
                        ScaleIndependentPixels(context, 50).px(),
                        ScaleIndependentPixels(context, 50).px()
                ).apply { gravity = Gravity.CENTER }
                isClickable = true
                setImageResource(android.R.drawable.alert_dark_frame)
                setBackgroundResource(
                        RippleBackgroundBorderless(this@RippleBackgroundActivity).fetch()
                )
            })
        })
    }
}