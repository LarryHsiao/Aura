package com.larryhsiao.aura.view

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

/**
 * Simple view for loading complex layout. Which will show given view as PlaceHolder until the layout is inflated.
 *
 * ```
 * class PlaceHolderViewDemoActivity : AppCompatActivity() {
 *  override fun onCreate(savedInstanceState: Bundle?) {
 *      super.onCreate(savedInstanceState)
 *      setContentView(PlaceHolderView(this).apply {
 *          layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
 *          loadUp(R.layout.activity_place_holder) {
 *              // View loaded
 *          }
 *      })
 *  }
 * }
 * ```
 */
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

    /**
     * Set up this place holder content
     */
    fun setup(newPlaceHolder: View) {
        loaded = false
        removeAllViews()
        addView(newPlaceHolder)
    }

    /**
     * Loads the heavy loading layout.
     */
    fun loadUp(@LayoutRes id: Int, callback: (view: View) -> Unit) {
        AsyncLayoutInflater(context).inflate(id, this) { view: View, _: Int, _: ViewGroup? ->
            removeAllViews()
            addView(view)
            loaded = true
            callback(view)
        }
    }

    /**
     * Is this view loaded?
     */
    fun isLoaded() = loaded

    /**
     * The view loaded.
     */
    fun loadedView() = if (childCount > 0) {
        getChildAt(0)
    } else {
        View(context)
    }
}