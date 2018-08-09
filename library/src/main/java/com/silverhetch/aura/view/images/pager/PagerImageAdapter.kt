package com.silverhetch.aura.view.images.pager

import androidx.viewpager.widget.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.squareup.picasso.Picasso

/**
 * Adapter for pager which has one image
 */
class PagerImageAdapter(private val uris: ArrayList<String>) : PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return uris.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image = ImageView(container.context)
        image.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        val placeHolder = CircularProgressDrawable(container.context)
        placeHolder.setStyle(CircularProgressDrawable.DEFAULT)
        Picasso.get().load(uris[position]).placeholder(placeHolder).into(image)
        container.addView(image)
        return image
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}