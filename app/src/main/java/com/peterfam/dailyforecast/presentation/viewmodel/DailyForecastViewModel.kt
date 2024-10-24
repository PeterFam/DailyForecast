package com.peterfam.dailyforecast.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem
import com.peterfam.dailyforecast.domain.DailyForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyForecastViewModel @Inject constructor(private val repository: DailyForecastRepository): ViewModel() {

    var weatherData by mutableStateOf<List<WeatherDataItem>?>(null)
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var citiesList by mutableStateOf<List<CitiesItem>>(emptyList())
    var selectedCity by mutableStateOf<CitiesItem?>(null)
    init {
        getCities()
    }

    private fun getCities(){
        viewModelScope.launch {
            try {
                isLoading = true
                citiesList = repository.getCities() ?: emptyList()
            } catch (e: Exception){
                e.printStackTrace()
            }finally {
                isLoading = false
            }
        }
    }

    fun fetchWeather(city: CitiesItem) {
        selectedCity = city
        viewModelScope.launch {
            try {
                isLoading = true
                weatherData = repository.getWeatherData(city)
                errorMessage = null
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage = "Failed to load weather data."
            } finally {
                isLoading = false
            }
        }
    }
}