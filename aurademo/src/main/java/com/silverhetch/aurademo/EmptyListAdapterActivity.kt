package com.silverhetch.aurademo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silverhetch.aura.view.EmptyListAdapter
import com.silverhetch.aura.view.ViewHolder
import com.silverhetch.clotho.Source

/**
 * Activity to demo the adapter of empty list
 */
class EmptyListAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listView = RecyclerView(this)
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = EmptyListAdapter(object : RecyclerView.Adapter<ViewHolder>() {
            val data = ArrayList<String>()
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                return ViewHolder(View(parent.context))
            }

            override fun getItemCount(): Int {
                return data.size
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                data[0]
                // Leave empty.
            }
        }, object : Source<View> {
            override fun value(): View {
                return TextView(this@EmptyListAdapterActivity).apply {
                    text = "Empty View"
                    setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        resources.getDrawable(R.drawable.ic_cross),
                        null, null
                    )
                    gravity = Gravity.CENTER
                }
            }
        })
        setContentView(listView)
    }
}