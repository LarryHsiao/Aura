package com.silverhetch.aura.view.images

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Bitmap.Config.ARGB_8888
import android.graphics.Bitmap.createBitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.GridLayout
import android.widget.ImageView
import com.silverhetch.aura.R
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.aura.view.measures.DP

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
    private var itemSize = 0
    private val imageMap = LinkedHashMap<String, MnemeItem>()
    private var callback: (item: MnemeItem, isAddButton: Boolean) -> Unit = { _, _ -> }
    private var addable = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    @TargetApi(21)
    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context) {
        columnCount = 3
        if (isInEditMode) {
            val list = ArrayList<MnemeItem>()
            for (i in 0..2) {
                list.add(BitmapItem(
                    context,
                    createBitmap(1, 1, ARGB_8888).also {
                        it.eraseColor(RandomColors().value())
                    })
                )
            }
            initImages(list, true)
        }
    }

    /**
     * Load up the images.
     */
    fun initImages(newDrawables: List<MnemeItem>, editable: Boolean) {
        post {
            itemSize = (measuredWidth.toFloat() / columnCount).toInt()
            imageMap.clear()
            removeAllViews()
            newDrawables.forEach { source ->
                imageMap[source.id()] = source
                itemView(source.icon())
            }
            addable(editable)
        }
    }

    /**
     * Add a Image to view.
     */
    fun addImage(source: MnemeItem) {
        post {
            if (imageMap.containsKey(source.id()).not()) {
                imageMap[source.id()] = source
                itemView(source.icon())
            }
        }
    }

    /**
     * Sources on view
     */
    fun sources(): Map<String, MnemeItem> {
        return LinkedHashMap(imageMap)
    }

    /**
     * Enable/Disable add button
     */
    fun addable(new: Boolean) {
        if (addable != new) {
            addable = new

            if (addable) {
                addButtonView()
            } else {
                removeViewAt(childCount - 1)
            }

            for (i in 0 until if (addable) childCount - 1 else childCount) {
                getChildAt(i).findViewById<View>(
                    R.id.itemImageWithCross_delete
                ).visibility = if (addable) View.VISIBLE else View.GONE
            }
        }
    }

    /**
     * Listener the item click event.
     */
    fun setCallback(newCallback: (item: MnemeItem, isAddingButton: Boolean) -> Unit) {
        callback = newCallback
    }

    private fun addButtonView() {
        addView(LayoutInflater.from(context).inflate(
            R.layout.item_image_with_cross,
            this,
            false
        ).also { itemView ->
            itemView.layoutParams = LayoutParams(itemSize, itemSize)
            itemView.setPadding(padding, padding, padding, padding)
            itemView.findViewById<ImageView>(
                R.id.itemImageWithCross_imageView
            ).setImageResource(R.drawable.ic_plus)
            itemView.setOnClickListener { view ->
                callback(BitmapItem(
                    context,
                    createBitmap(1, 1, ARGB_8888)
                ), true)
            }
            itemView.findViewById<View>(
                R.id.itemImageWithCross_delete
            ).visibility = View.GONE
        }, childCount)
    }

    /**
     * Customize the add button drawable
     */
    fun setupAddIcon(newIcon: Drawable) {
        addIcon = newIcon
    }

    private fun itemView(drawable: Drawable) {
        addView(LayoutInflater.from(context).inflate(
            R.layout.item_image_with_cross,
            this,
            false
        ).also { itemView ->
            itemView.layoutParams = LayoutParams(itemSize, itemSize)
            itemView.setPadding(padding, padding, padding, padding)
            itemView.findViewById<ImageView>(R.id.itemImageWithCross_imageView).setImageDrawable(drawable)
            itemView.setOnClickListener { view ->
                val index = indexOfChild(view)
                callback(getItem(index), addable && index == childCount - 1)
            }
            itemView.findViewById<View>(R.id.itemImageWithCross_delete).also {
                it.visibility = if (addable) VISIBLE else GONE
                it.setOnClickListener {
                    val index = indexOfChild(itemView)
                    removeViewAt(index)
                    imageMap.remove(ArrayList(imageMap.keys)[index])
                }
            }
        }, if (addable) {
            childCount - 1
        } else {
            childCount
        })
    }

    private fun getItem(index: Int): MnemeItem {
        return imageMap[ArrayList(imageMap.keys)[index]]
            ?: BitmapItem(
                context,
                createBitmap(1, 1, ARGB_8888)
            )
    }
}