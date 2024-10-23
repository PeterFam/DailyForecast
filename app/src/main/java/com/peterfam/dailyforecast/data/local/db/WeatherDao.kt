package com.peterfam.dailyforecast.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.peterfam.dailyforecast.data.local.model.WeatherEntity

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather WHERE cityId = :cityID")
    suspend fun getWeatherOfACity(cityID: Int): WeatherEntity
}