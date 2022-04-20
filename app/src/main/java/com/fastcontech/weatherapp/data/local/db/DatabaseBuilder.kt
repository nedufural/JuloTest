package com.fastcontech.weatherapp.data.local.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {

    private lateinit var INSTANCE: AppDatabase

    fun getInstance(context: Context): AppDatabase {
        synchronized(AppDatabase::class) {
            INSTANCE = buildRoomDB(context)
        }
        return INSTANCE
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, DBConstants.DB_NAME
        ).build()
}
