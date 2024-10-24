package com.peterfam.dailyforecast.domain

import android.content.Context
import android.util.Log
import androidx.lifecycle.asLiveData
import com.peterfam.dailyforecast.data.local.db.CityDao
import com.peterfam.dailyforecast.data.local.db.WeatherDao
import com.peterfam.dailyforecast.data.local.model.CityEntity
import com.peterfam.dailyforecast.data.local.model.WeatherEntity
import com.peterfam.dailyforecast.data.remote.DailyForecastApi
import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem
import com.peterfam.dailyforecast.utils.NetworkConnectionManager
import com.peterfam.dailyforecast.utils.toCitiesItem
import com.peterfam.dailyforecast.utils.toCityEntity
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class DailyForecastRepositoryImpl @Inject constructor(
    private val context: Context,
    private val weatherDao: WeatherDao,
    private val cityDao: CityDao,
    private val api: DailyForecastApi
): DailyForecastRepository{
    override suspend fun getCities(): List<CitiesItem> {
        if(NetworkConnectionManager().isInternetAvailable(context)){
            // Fetch from API
            val cities =  api.getCities().body()?.cities ?: emptyList()
            // Map API response to CityEntity and cache it
            cityDao.insertCity(cities.map { it.toCityEntity() })

            return cities
        }else{
            // If API fails, fetch from local database
            val cachedCities = cityDao.getCities().last()
            return if (cachedCities.isNotEmpty()) {
                cachedCities.map { it.toCitiesItem() }
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getWeatherData(citiesItem: CitiesItem): List<WeatherDataItem> {
       return if(NetworkConnectionManager().isInternetAvailable(context)){
            val response = api.getWeather(lat = citiesItem.lat ?: 0.0, lon = citiesItem.lon ?: 0.0, apiKey = "a08180a9c262ac7851a06cf4e8644f4f")
            val weatherResponseList = response.body()?.weatherDataItems
            weatherDao.insetWeather(WeatherEntity(cityId = citiesItem.id ?: 0, weatherList = weatherResponseList ?: emptyList()))
            weatherResponseList ?: emptyList()
        }else {
            weatherDao.getWeatherOfACity(citiesItem.id ?: 0).weatherList
        }
    }

}