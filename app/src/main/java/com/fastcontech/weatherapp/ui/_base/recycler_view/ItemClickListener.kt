package com.fastcontech.weatherapp.ui._base.recycler_view

interface ItemClickListener<T> {
    fun onItemClick(data: T?, position: Int, typeClick: Int = 0)
}
