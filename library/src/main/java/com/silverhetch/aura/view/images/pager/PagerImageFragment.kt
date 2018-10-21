package com.silverhetch.aura.view.images.pager

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.silverhetch.aura.AuraFragment

/**
 * Fragment that shows image with given uri.
 */
class PagerImageFragment : AuraFragment() {
    companion object {
        private const val URI_IMAGE = "URI_IMAGE"
        @JvmStatic
        fun newInstance(input: ArrayList<String>): PagerImageFragment {
            val fragment = PagerImageFragment()
            val bundle = Bundle()
            bundle.putStringArrayList(URI_IMAGE, input)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val uris= arguments!!.getStringArrayList(URI_IMAGE)
        val pager = ViewPager(inflater.context)
        pager.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        pager.adapter = PagerImageAdapter(uris)
        return pager
    }
}