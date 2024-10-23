package com.peterfam.dailyforecast.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem

class Converters {
    @TypeConverter
    fun weatherListToJsonString(value: List<WeatherDataItem>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToWeatherList(value: String) = Gson().fromJson(value, Array<WeatherDataItem>::class.java).toList()
}