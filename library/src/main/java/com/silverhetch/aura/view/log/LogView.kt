package com.silverhetch.aura.view.log

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import java.util.Observable
import java.util.Observer

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT

internal class LogView : LinearLayout, Observer {
    private lateinit var textView: TextView

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    @TargetApi(LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context) {
        orientation = LinearLayout.VERTICAL
        textView = TextView(context)
        textView.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        textView.setTextColor(Color.BLACK)
        textView.gravity = Gravity.BOTTOM
        textView.movementMethod = ScrollingMovementMethod()
        addView(textView)
    }

    override fun update(observable: Observable, data: Any) {
        if (data is String) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                textView.append(Html.fromHtml(data.toString() + "<br/>", Html.FROM_HTML_MODE_LEGACY))
            } else {
                textView.append(Html.fromHtml(data.toString() + "<br/>"))
            }
        }
    }
}
