package com.fastcontech.weatherapp.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fastcontech.weatherapp.R
import com.fastcontech.weatherapp.core.models.ListElement
import com.fastcontech.weatherapp.data.network.NetworkConstants
import com.fastcontech.weatherapp.ui._base.recycler_view.BaseRecycleViewAdapter
import com.fastcontech.weatherapp.ui._base.recycler_view.BaseViewHolder
import com.fastcontech.weatherapp.ui._base.recycler_view.ItemClickListener
import org.koin.dsl.module


class WeatherAdapter(
    private val itemClickListener: ItemClickListener<ListElement>,
    private val context: Context
) :
    BaseRecycleViewAdapter<ListElement>(itemClickListener) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ListElement> {
        val convertView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)

        return WeatherViewHolder(
            convertView,
            itemClickListener = itemClickListener,
            context = context
        )
    }

    class WeatherViewHolder(
        convertView: View,
        itemClickListener: ItemClickListener<ListElement>,
        private val context: Context
    ) : BaseViewHolder<ListElement>(itemClickListener, convertView) {

        private val countryName: TextView = convertView.findViewById(R.id.date)
        private val windSpeed: TextView = convertView.findViewById(R.id.wind_speed)
        private val weatherIcon: ImageView = convertView.findViewById(R.id.weather_icon)
        private val humidity: TextView = convertView.findViewById(R.id.humidity)
        private val temp: TextView = convertView.findViewById(R.id.temp)

        override fun bindingData(data: ListElement?) {
            setData(data)
            setEntityDataToView(data)
        }

        private fun setEntityDataToView(data: ListElement?) {
            countryName.text = data?.dt_txt
            temp.text = "${ data?.main?.temp.toString() } ${(0x00B0.toChar())} C"
            humidity.text = "Humidity : ${ data?.main?.humidity.toString() } %"
            windSpeed.text = "Speed : ${ data?.wind?.speed.toString()} m/s"
            Glide.with(context)
                .load(NetworkConstants.getImageUrl(data?.weather?.firstOrNull()?.icon.toString()))
                .centerCrop()
                .skipMemoryCache(true)
                .into(weatherIcon)
        }
    }
}
