package com.fastcontech.weatherapp.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.ui._base.recycler_view.BaseRecycleViewAdapter
import com.fastcontech.weatherapp.ui._base.recycler_view.BaseViewHolder
import com.fastcontech.weatherapp.ui._base.recycler_view.ItemClickListener
import org.koin.dsl.module

class FavouriteAdapter(private val itemClickListener: ItemClickListener<Favourite>) :
    BaseRecycleViewAdapter<Favourite>(itemClickListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Favourite> {
        val convertView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favourites, parent, false)

        return FavouriteViewHolder(
            convertView,
            itemClickListener = itemClickListener
        )
    }

    class FavouriteViewHolder(
        convertView: View,
        itemClickListener: ItemClickListener<Favourite>
    ) :
        BaseViewHolder<Favourite>(itemClickListener, convertView) {

        private val countryName: TextView = convertView.findViewById(R.id.country)
        private val cityName: TextView = convertView.findViewById(R.id.temp)

        override fun bindingData(data: Favourite?) {
            setData(data)
            setEntityDataToView(data)
        }

        private fun setEntityDataToView(data: Favourite?) {
            countryName.text = data?.countryName
            cityName.text = data?.cityName
        }
    }
}
