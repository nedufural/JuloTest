package com.fastcontech.weatherapp.data.network

object NetworkConstants {
    const val MESSAGE: String = "Network message : "
    const val TIME_OUT: Long = 30_000L
    private const val API_KEY = "d717c63aa00d20c55114ef5e73083ebb"
    fun concatWeatherUrl(lat: String, lon: String): String {
        return "https://api.openweathermap.org/data/2.5/onecall?lat=$lat&lon=$lon&cnt=10&units=metric&exclude=current,hourly,minutely,alerts&appid=$API_KEY"
    }
    fun getImageUrl(icon: String): String {
        return "https://openweathermap.org/img/w/$icon.png"
    }

    fun getSearchUrl(city: String): String {
        return "https://api.openweathermap.org/data/2.5/forecast?q=$city&cnt=3&units=metric&exclude=current,hourly,minutely,alerts&appid=$API_KEY"
    }
}
