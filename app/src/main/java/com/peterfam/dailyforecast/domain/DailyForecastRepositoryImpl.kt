package com.peterfam.dailyforecast.domain

import android.content.Context
import com.peterfam.dailyforecast.BuildConfig
import com.peterfam.dailyforecast.data.local.db.CityDao
import com.peterfam.dailyforecast.data.local.db.WeatherDao
import com.peterfam.dailyforecast.data.local.model.WeatherEntity
import com.peterfam.dailyforecast.data.remote.DailyForecastApi
import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem
import com.peterfam.dailyforecast.utils.NetworkConnectionManager
import com.peterfam.dailyforecast.utils.toCitiesItem
import com.peterfam.dailyforecast.utils.toCityEntity
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
            val cachedCities = cityDao.getCities()
            return if (cachedCities.isNotEmpty()) {
                cachedCities.map { it.toCitiesItem() }
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getWeatherData(citiesItem: CitiesItem): List<WeatherDataItem> {
       return if(NetworkConnectionManager().isInternetAvailable(context)){
            val response = api.getWeather(lat = citiesItem.lat ?: 0.0, lon = citiesItem.lon ?: 0.0, apiKey = BuildConfig.API_KEY)
            val weatherResponseList = response.body()?.weatherDataItems
            weatherDao.insetWeather(WeatherEntity(cityId = citiesItem.id ?: 0, weatherList = weatherResponseList ?: emptyList()))
            weatherResponseList ?: emptyList()
        }else {
            weatherDao.getWeatherOfACity(citiesItem.id ?: 0).weatherList
        }
    }

}