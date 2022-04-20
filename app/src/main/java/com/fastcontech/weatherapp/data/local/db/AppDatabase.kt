package com.fastcontech.weatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fastcontech.weatherapp.core.models.Favourite
import com.fastcontech.weatherapp.core.models.WeatherEntity
import com.fastcontech.weatherapp.data.local.WeatherAppTypeConverter
import com.fastcontech.weatherapp.data.local.dao.FavouriteDao

@Database(entities = [Favourite::class], version = 1, exportSchema = true)
@TypeConverters(WeatherAppTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteDao(): FavouriteDao
}
