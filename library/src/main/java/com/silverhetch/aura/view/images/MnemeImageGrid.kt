package com.silverhetch.aura.view.images

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams
import android.widget.GridLayout
import android.widget.ImageView
import com.silverhetch.aura.R
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.aura.view.measures.DP
import com.silverhetch.clotho.Source

/**
 * Simple architecture grid view for viewing ImageView and an adding button at last.
 * Little amounts of image is best, with large amount of image is not recommend and would have performance issue.
 *
 * Just implement a new one if this is not capable full fill requirement.
 */
class MnemeImageGrid : GridLayout {
    private val padding by lazy {
        DP(context, 8f).px().toInt()
    }
    private var addIcon: Drawable? = null

    private var callback: (index: Int, isAddButton: Boolean) -> Unit = { _, _ -> }
    private var addable = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    @TargetApi(21)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context) {
        columnCount = 3
        if (isInEditMode) {
            val list = ArrayList<Source<Drawable>>()
            for (i in 0..2) {
                list.add(object : Source<Drawable> {
                    override fun value(): Drawable {
                        return BitmapDrawable(resources, Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888).also {
                            it.eraseColor(RandomColors().value())
                        })
                    }
                })
            }
            initImages(list)
        }
    }

    /**
     * Load up the images.
     */
    fun initImages(sources: List<Source<Drawable>>) {
        post {
            removeAllViews()
            sources.forEach { source ->
                addItem(source.value())
            }

            if (addable) {
                addButtonItem()
            }
        }
    }

    /**
     * Enable/Disable add button
     */
    fun addable(new: Boolean) {
        if (addable != new) {
            addable = new

            if (addable) {
                addButtonItem()
            } else {
                removeViewAt(childCount - 1)
            }
        }
    }

    /**
     * Listener the item click event.
     */
    fun setCallback(newCallback: (index: Int, isAddingButton: Boolean) -> Unit) {
        callback = newCallback
    }

    private fun addButtonItem() {
        addItem(addIcon ?: resources.getDrawable(R.drawable.ic_image_plus))
    }

    /**
     * Customize the add button drawable
     */
    fun setupAddIcon(newIcon: Drawable) {
        addIcon = newIcon
    }

    private fun addItem(drawable: Drawable) {
        val itemSize = (measuredWidth.toFloat() / columnCount).toInt()
        addView(
            ImageView(context).also {
                it.layoutParams = LayoutParams(
                    LayoutParams(itemSize, itemSize)
                )
                it.setPadding(padding, padding, padding, padding)
                it.setImageDrawable(drawable)
                it.setOnClickListener { view ->
                    val index = indexOfChild(view)
                    callback(index, addable && index == childCount - 1)
                }
            }
        )
    }
}