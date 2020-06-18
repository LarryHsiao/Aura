package com.silverhetch.aurademo

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.silverhetch.aura.AuraActivity
import com.silverhetch.aura.view.RandomColors
import com.silverhetch.aura.view.recyclerview.slider.Slider
import com.silverhetch.aura.view.recyclerview.ViewHolder

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
