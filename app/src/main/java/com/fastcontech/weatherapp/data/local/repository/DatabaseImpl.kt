package com.fastcontech.weatherapp.data.local.repository

import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.data.local.db.AppDatabase
import kotlinx.coroutines.flow.Flow
import org.koin.dsl.module


class DatabaseImpl(private val appDatabase: AppDatabase) : FavouriteDatabaseHelper {
    override suspend fun getFavouriteCitiesWeather(): List<Favourite> {
        return appDatabase.favouriteDao().getAllFavourite()
    }

    override suspend fun insertFavouriteCityWeather(favourite: Favourite) {
        appDatabase.favouriteDao().insert(favourite)
    }

    override suspend fun deleteFavouriteCityWeather(favourite: Favourite) {
        appDatabase.favouriteDao().delete(favourite)
    }
}
