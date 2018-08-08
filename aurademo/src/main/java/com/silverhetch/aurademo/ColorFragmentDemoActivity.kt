package com.silverhetch.aurademo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.silverhetch.aura.phantom.ColorFragment

/**
 * Created by mikes on 4/2/2018.
 */
class ColorFragmentDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewpager = ViewPager(this)
        viewpager.id = R.id.colorFramgent_viewPager
        viewpager.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        setContentView(viewpager)

        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return ColorFragment()
            }

            override fun getCount(): Int {
                return 10
            }
        }
    }
}