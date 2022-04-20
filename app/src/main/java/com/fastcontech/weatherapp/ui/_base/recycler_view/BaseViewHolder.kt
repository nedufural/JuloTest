package com.fastcontech.weatherapp.ui._base.recycler_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : Any>(
    itemClickListener: ItemClickListener<T>? = null,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private var clickListener: ItemClickListener<T>? = itemClickListener

    private var mData: T? = null

    abstract fun bindingData(data: T?)

    fun setData(data: T?) {
        mData = data
    }

    init {
        itemView.setOnClickListener {
            clickListener?.onItemClick(mData, bindingAdapterPosition, 0)
        }
    }
}
