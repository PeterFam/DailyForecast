package com.peterfam.dailyforecast.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem

@Composable
fun WeatherAppScreen(
    cities: List<CitiesItem>,
    selectedCity: CitiesItem?,
    onCitySelected: (CitiesItem) -> Unit,
    weatherData: List<WeatherDataItem>?,
    isLoading: Boolean,
    errorMessage: String?,
    onRetry: (CitiesItem?) -> Unit
) {
    Column {
        AppBarWithDropdown(
            cities = cities,
            selectedCity = selectedCity,
            onCitySelected = onCitySelected
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            ErrorView(errorMessage, onRetry, selectedCity)
        } else {
            WeatherForecastList(weatherData = weatherData)
        }
    }
}