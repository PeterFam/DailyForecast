package com.peterfam.dailyforecast.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peterfam.dailyforecast.data.remote.response.CitiesItem


@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val cityNameAr: String,

    val cityNameEn: String,

    val lon: Double,

    val lat: Double
)
