package com.fastcontech.weatherapp.data.local.repository

import com.fastcontech.weatherapp.core.models.Favourite
import kotlinx.coroutines.flow.Flow

interface FavouriteDatabaseHelper {
    suspend fun getFavouriteCitiesWeather(): List<Favourite>
    suspend fun insertFavouriteCityWeather(favourite: Favourite)
    suspend fun deleteFavouriteCityWeather(favourite: Favourite)
}
