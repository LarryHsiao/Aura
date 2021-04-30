package com.larryhsiao.aura.view.textview

import android.graphics.BlurMaskFilter
import android.widget.TextView
import com.larryhsiao.clotho.Action

/**
 * Action to blur given [TextView].
 */
class BlurTextAction(
    private val textView: TextView
) : Action {
    override fun fire() {
        textView.paint.maskFilter = BlurMaskFilter(
            textView.textSize / 3,
            BlurMaskFilter.Blur.NORMAL
        )
    }
}