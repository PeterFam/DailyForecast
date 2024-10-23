package com.peterfam.dailyforecast.utils

import com.peterfam.dailyforecast.data.local.model.CityEntity
import com.peterfam.dailyforecast.data.remote.response.CitiesItem

fun CitiesItem.toCityEntity(): CityEntity = CityEntity(
    id = this.id ?: 0,
    cityNameAr = this.cityNameAr.orEmpty(),
    cityNameEn = this.cityNameEn.orEmpty(),
    lon = this.lon ?: 0.0,
    lat = this.lat ?: 0.0
)

fun CityEntity.toCitiesItem(): CitiesItem = CitiesItem(
    id = this.id,
    cityNameAr = this.cityNameAr,
    cityNameEn = this.cityNameEn,
    lon = this.lon,
    lat = this.lat
)