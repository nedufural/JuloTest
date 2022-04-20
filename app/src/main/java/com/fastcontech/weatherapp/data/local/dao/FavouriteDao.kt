package com.fastcontech.weatherapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fastcontech.weatherapp.core.models.Favourite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favourite: Favourite?)

    @Update
    fun update(favourite: Favourite?)

    @Delete
    fun delete(favourite: Favourite?)

    @Query("DELETE FROM _favourite")
    fun deleteAllFavourite()

    @Query("SELECT * FROM _favourite")
    fun getAllFavourite(): List<Favourite>

    @Query("SELECT * FROM _favourite WHERE cityName LIKE  :cityName ORDER BY cityName ASC")
    fun findFavouriteByName(cityName: String): Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMultipleFavourite(weather: List<Favourite>)
}
