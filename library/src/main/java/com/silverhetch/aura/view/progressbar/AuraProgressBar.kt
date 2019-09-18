package com.silverhetch.aura.view.progressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Color.GRAY
import android.graphics.Color.TRANSPARENT
import android.graphics.PorterDuff.Mode.CLEAR
import android.graphics.PorterDuff.Mode.SRC_ATOP
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.silverhetch.aura.R
import com.silverhetch.aura.view.measures.DP

/**
 * A simple progress bar implemented with drawing canvas.
 * Just different from vanilla one in Android Framework.
 */
class AuraProgressBar : View {
    companion object {
        private const val HEIGHT_DP_MAX = 40f
    }

    private val heightPxMax: Float
    private val radius: Float
    private val bgRectF by lazy { RectF(0f, 0f, width.toFloat(), height.toFloat()) }
    private val progressSrc: Rect
    private val progressDst = Rect()
    private val progressMaskBitmap: Bitmap
    private val progressMaskPaint: Paint
    private var progress: Float = 0f
    private var animator = ValueAnimator()
    private val bgPaint: Paint
    private val eraser: Paint by lazy {
        Paint().apply {
            color = TRANSPARENT
            xfermode = PorterDuffXfermode(
                CLEAR
            )
            isAntiAlias = true
        }
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val style = context.obtainStyledAttributes(
            attrs,
            R.styleable.AuraProgressBar,
            0, 0
        )
        heightPxMax = DP(context, HEIGHT_DP_MAX).px()
        radius = DP(context, 20f).px()
        bgPaint = Paint().apply {
            color = style.getColor(R.styleable.AuraProgressBar_bgColor, GRAY)
            isAntiAlias = true
        }
        progressMaskBitmap = Bitmap.createBitmap(1, 1, ARGB_8888).apply {
            eraseColor(style.getColor(R.styleable.AuraProgressBar_progressColor, Color.DKGRAY))
        }
        progressMaskPaint = Paint().apply { xfermode = PorterDuffXfermode(SRC_ATOP) }
        progressSrc = Rect(0, 0, 1, 1)

        updateProgress(style.getFloat(R.styleable.AuraProgressBar_progress, 0f))
        animator.apply {
            duration = 150
            addUpdateListener {
                updateProgress(it.animatedValue as Float)
            }
        }
        style.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        setWillNotDraw(false)
        setLayerType(LAYER_TYPE_HARDWARE, null)
        if (canvas == null) {
            return
        }
        if (height <= heightPxMax) {
            bgRectF.top = 0f
            bgRectF.bottom = height.toFloat()
        } else {
            val spacing = (height - heightPxMax) / 2
            bgRectF.top = spacing
            bgRectF.bottom = height - spacing
        }
        canvas.drawRoundRect(bgRectF, 0f, 0f, eraser)
        canvas.drawRoundRect(bgRectF, radius, radius, bgPaint) // background
        progressDst.right = (width * progress).toInt()
        progressDst.bottom = height
        canvas.drawBitmap(progressMaskBitmap, progressSrc, progressDst, progressMaskPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(
            getDefaultSize(suggestedMinimumWidth, widthMeasureSpec),
            if (layoutParams.height == WRAP_CONTENT) {
                heightPxMax.toInt()
            } else {
                getDefaultSize(heightPxMax.toInt(), heightMeasureSpec)
            }
        )
    }

    /**
     * @param newProgress 0.0f ~ 1.0f
     */
    fun updateProgress(newProgress: Float) {
        progress = if (newProgress < 0f) 0f else if (newProgress > 1f) 1f else newProgress
        invalidate()
    }

    /**
     * Current progress this view is showing
     */
    fun progress(): Float = progress

    fun updateProgressAnimated(newProgress: Float) {
        animator.cancel()
        animator.setFloatValues(progress, newProgress)
        animator.start()
    }
}