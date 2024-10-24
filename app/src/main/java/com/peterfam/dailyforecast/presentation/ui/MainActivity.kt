package com.peterfam.dailyforecast.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.peterfam.dailyforecast.presentation.viewmodel.DailyForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    private val viewModel: DailyForecastViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppScreen(
                cities = viewModel.citiesList, // Load cities from the ViewModel or repository
                selectedCity = viewModel.selectedCity,
                onCitySelected = { city -> viewModel. fetchWeather(city) },
                weatherData = viewModel.weatherData,
                isLoading = viewModel.isLoading,
                errorMessage = viewModel.errorMessage,
                onRetry = { city -> city?.let { viewModel. fetchWeather(city) }}
            )
        }
    }
}