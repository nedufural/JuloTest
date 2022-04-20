package com.fastcontech.weatherapp.data.local

import androidx.room.TypeConverter
import com.fastcontech.weatherapp.core.models.Daily
import com.fastcontech.weatherapp.core.models.FeelsLike
import com.fastcontech.weatherapp.core.models.Temp
import com.fastcontech.weatherapp.core.models.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class WeatherAppTypeConverter {

    @TypeConverter
    fun weatherToString(app: String): List<Weather?>? {
        val listType: Type = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson<List<Weather>>(app, listType)
    }

    @TypeConverter
    fun stringToWeather(app: List<Weather>): String? {
        return Gson().toJson(app)
    }

    @TypeConverter
    fun dailyToString(app: String): List<Daily> {
        val listType: Type = object : TypeToken<List<Daily>>() {}.type
        return Gson().fromJson<List<Daily>>(app, listType)
    }

    @TypeConverter
    fun stringToDaily(app: List<Daily>): String? {
        return Gson().toJson(app)
    }

    @TypeConverter
    fun tempToString(app: Temp): String = Gson().toJson(app)

    @TypeConverter
    fun stringToTemp(string: String): Temp = Gson().fromJson(string, Temp::class.java)

    @TypeConverter
    fun mainToString(app: FeelsLike): String = Gson().toJson(app)

    @TypeConverter
    fun stringToMain(string: String): FeelsLike = Gson().fromJson(string, FeelsLike::class.java)
}
