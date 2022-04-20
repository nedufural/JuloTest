package com.fastcontech.weatherapp.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_favourite")
data class Favourite(
    @PrimaryKey
    val cityName: String,
    val countryName: String
)
