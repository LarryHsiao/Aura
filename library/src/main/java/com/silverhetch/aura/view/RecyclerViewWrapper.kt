package com.silverhetch.aura.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Wrapper class for ViewHolder of RecyclerView.
 */
open class RecyclerViewWrapper<T : RecyclerView.ViewHolder>(
    private val origin: RecyclerView.Adapter<T>
) : RecyclerView.Adapter<T>() {

    init {
        origin.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeRemoved(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                notifyDataSetChanged()
            }

            override fun onItemRangeInserted(
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeChanged(
                positionStart: Int,
                itemCount: Int
            ) {
                notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeChanged(
                positionStart: Int,
                itemCount: Int,
                payload: Any?
            ) {
                notifyItemRangeChanged(positionStart, itemCount, payload)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        return origin.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return origin.itemCount
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        return origin.onBindViewHolder(holder, position)
    }

    override fun onBindViewHolder(
        holder: T,
        position: Int,
        payloads: MutableList<Any>
    ) {
        origin.onBindViewHolder(holder, position, payloads)
    }

    override fun unregisterAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        origin.unregisterAdapterDataObserver(observer)
    }

    override fun onViewDetachedFromWindow(holder: T) {
        origin.onViewDetachedFromWindow(holder)
    }

    override fun getItemId(position: Int): Long {
        return origin.getItemId(position)
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        origin.setHasStableIds(hasStableIds)
    }

    override fun onFailedToRecycleView(holder: T): Boolean {
        return origin.onFailedToRecycleView(holder)
    }

    override fun getItemViewType(position: Int): Int {
        return origin.getItemViewType(position)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        origin.onAttachedToRecyclerView(recyclerView)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        origin.onDetachedFromRecyclerView(recyclerView)
    }

    override fun onViewRecycled(holder: T) {
        origin.onViewRecycled(holder)
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        origin.registerAdapterDataObserver(observer)
    }

    override fun onViewAttachedToWindow(holder: T) {
        origin.onViewAttachedToWindow(holder)
    }
}