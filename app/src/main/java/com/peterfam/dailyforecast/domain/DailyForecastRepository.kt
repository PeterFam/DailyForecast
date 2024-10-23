package com.peterfam.dailyforecast.domain

import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem

interface DailyForecastRepository {

    suspend fun getCities() : List<CitiesItem>?

    suspend fun getWeatherData(citiesItem: CitiesItem) : List<WeatherDataItem>?

}