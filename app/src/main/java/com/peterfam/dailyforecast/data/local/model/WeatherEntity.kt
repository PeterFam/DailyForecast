package com.peterfam.dailyforecast.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false)
    val cityId: Int, //work as foreign key,

    val weatherList: List<WeatherDataItem>
)
