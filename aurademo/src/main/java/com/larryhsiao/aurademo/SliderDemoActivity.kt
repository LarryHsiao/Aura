package com.larryhsiao.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.larryhsiao.aura.AuraActivity
import com.larryhsiao.aura.view.RandomColors
import com.larryhsiao.aura.view.recyclerview.slider.Slider
import com.larryhsiao.aura.view.recyclerview.ViewHolder

/**
 * Activity for demo Aura slider.
 */
class SliderDemoActivity : AuraActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(RecyclerView(this).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            adapter = object : RecyclerView.Adapter<ViewHolder>() {
                override fun onCreateViewHolder(
                    parent: ViewGroup,
                    viewType: Int
                ): ViewHolder {
                    return ViewHolder(ImageView(parent.context).apply {
                        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    })
                }

                override fun getItemCount(): Int {
                    return 2
                }

                override fun onBindViewHolder(
                    holder: ViewHolder,
                    position: Int
                ) {
                    (holder.itemView as ImageView).setBackgroundColor(
                        RandomColors().value()
                    )
                }
            }
            Slider(this).fire()
        })
    }
}
