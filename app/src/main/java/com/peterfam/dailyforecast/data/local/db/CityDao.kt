package com.peterfam.dailyforecast.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterfam.dailyforecast.data.local.model.City
import kotlinx.coroutines.flow.Flow


@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)

    @Query("SELECT * FROM city")
    fun getCities(): Flow<List<City>>
}