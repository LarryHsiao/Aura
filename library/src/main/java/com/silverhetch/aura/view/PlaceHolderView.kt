package com.silverhetch.aura.view

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.asynclayoutinflater.view.AsyncLayoutInflater

class PlaceHolderView : FrameLayout {
    private var loaded = false

    constructor(context: Context) : super(context) {
        setup(ProgressBar(context).apply {
            this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                this.gravity = Gravity.CENTER
            }
        })
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setup(ProgressBar(context, attrs).apply {
            this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                this.gravity = Gravity.CENTER
            }
        })
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(ProgressBar(context, attrs, defStyleAttr).apply {
            this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                this.gravity = Gravity.CENTER
            }
        })
    }

    @TargetApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        setup(ProgressBar(context, attrs, defStyleAttr, defStyleRes).apply {
            this.layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                this.gravity = Gravity.CENTER
            }
        })
    }

    fun setup(newPlaceHolder: View) {
        loaded = false
        removeAllViews()
        addView(newPlaceHolder)
    }

    fun loadUp(@LayoutRes id: Int) {
        AsyncLayoutInflater(context).inflate(id, this) { view: View, _: Int, _: ViewGroup? ->
            removeAllViews()
            addView(view)
            loaded = true
        }
    }

    fun isLoaded(): Boolean = loaded
}