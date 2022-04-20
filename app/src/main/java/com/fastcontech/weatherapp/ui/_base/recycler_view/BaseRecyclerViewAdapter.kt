package com.fastcontech.weatherapp.ui._base.recycler_view

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T : Any>(var clickListener: ItemClickListener<T>? = null) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {
    private var mListData: ArrayList<T> = arrayListOf()
    private var mListDataFiltered: ArrayList<T> = arrayListOf()

    override fun getItemCount(): Int {
        return mListDataFiltered.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(list: List<T>) {

        mListData = list as ArrayList<T>
        mListDataFiltered = mListData
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<T> {
        return mListData
    }

    fun clearAll() {
        mListData.clear()
        notifyDataSetChanged()
    }

    fun updateData(list: List<T>) {
        mListDataFiltered = list as ArrayList<T>
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        mListData.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindingData(mListDataFiltered[position])
    }

    fun restoreItem(item: T, position: Int) {
        mListData.add(position, item)
        notifyItemInserted(position)
    }
}
