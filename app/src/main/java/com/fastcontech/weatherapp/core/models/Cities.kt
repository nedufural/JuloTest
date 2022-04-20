package com.fastcontech.weatherapp.core.models

import kotlinx.serialization.Serializable

@Serializable
data class Cities(
    val cod: String,
    val message: Long,
    val cnt: Long,
    val list: List<ListElement>,
    val city: City
)

@Serializable
data class City(
    val id: Long,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Long,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

@Serializable
data class Coord(
    val lat: Double,
    val lon: Double
)

@Serializable
data class ListElement(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Long,
    val pop: Double,
    val sys: Sys,
    val dt_txt: String
)

@Serializable
data class Clouds(
    val all: Long
)

@Serializable
data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Long,
    val sea_level: Long,
    val grnd_level: Long,
    val humidity: Long,
    val temp_kf: Double
)

@Serializable
data class Sys(
    val pod: String
)

@Serializable
data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double
)
