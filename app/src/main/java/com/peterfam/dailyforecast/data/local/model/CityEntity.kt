package com.peterfam.dailyforecast.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "city")
data class CityEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val cityNameAr: String,

    val cityNameEn: String,

    val lon: Double,

    val lat: Double
)
