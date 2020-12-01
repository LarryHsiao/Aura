package com.silverhetch.aurademo

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.silverhetch.aura.view.recyclerview.EmptyListAdapter
import com.silverhetch.aura.view.recyclerview.ViewHolder
import com.larryhsiao.clotho.Source

/**
 * Activity to demo the adapter of empty list
 */
class EmptyListAdapterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = object : RecyclerView.Adapter<ViewHolder>() {
            val data = ArrayList<String>()
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                return ViewHolder(TextView(parent.context))
            }

            override fun getItemCount(): Int {
                return data.size
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                (holder.itemView as TextView).text = data[position]
                // Leave empty.
            }
        }
        val listView = RecyclerView(this)
        listView.layoutManager = LinearLayoutManager(this)
        listView.adapter = EmptyListAdapter(adapter, object : Source<View> {
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
        listView.postDelayed({
            adapter.data.add(":")
            adapter.notifyDataSetChanged()

            listView.postDelayed({
                adapter.data.add("1234")
                adapter.notifyItemInserted(1)
            }, 300)
        }, 1000)
        setContentView(listView)
    }
}