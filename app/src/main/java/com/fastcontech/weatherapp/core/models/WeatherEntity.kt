package com.fastcontech.weatherapp.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "_weather")
@Serializable
data class WeatherEntity(
    @PrimaryKey
    @SerializedName("timezone") var timezone: String,
    @SerializedName("daily") var daily: List<Daily>
) : java.io.Serializable

@Serializable
data class Temp(

    @SerializedName("day") var day: Float,
    @SerializedName("min") var min: Float,
    @SerializedName("max") var max: Float,
    @SerializedName("night") var night: Float,
    @SerializedName("eve") var eve: Float,
    @SerializedName("morn") var morn: Float

)

@Serializable
data class FeelsLike(

    @SerializedName("day") var day: Double,
    @SerializedName("night") var night: Double,
    @SerializedName("eve") var eve: Double,
    @SerializedName("morn") var morn: Double

)

@Serializable
data class Daily(
    @SerializedName("dt") var dt: Int,
    @SerializedName("sunrise") var sunrise: Int,
    @SerializedName("sunset") var sunset: Int,
    @SerializedName("moonrise") var moonrise: Int,
    @SerializedName("moonset") var moonset: Int,
    @SerializedName("temp") var temp: Temp,
    @SerializedName("pressure") var pressure: Int,
    @SerializedName("humidity") var humidity: Int,
    @SerializedName("wind_speed") var wind_speed: Double,
    @SerializedName("weather") var weather: List<Weather>,
    @SerializedName("clouds") var clouds: Int,
    @SerializedName("pop") var pop: Double,
    @SerializedName("uvi") var uvi: Double

)
