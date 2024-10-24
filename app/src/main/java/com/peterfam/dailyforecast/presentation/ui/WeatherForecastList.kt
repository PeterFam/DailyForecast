package com.peterfam.dailyforecast.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.peterfam.dailyforecast.BuildConfig
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem

@Composable
fun WeatherForecastList(weatherData: List<WeatherDataItem>?) {
    LazyColumn {
        weatherData?.forEach { item ->
            item {
                WeatherForecastItem(weatherItem = item)
            }
        }
    }
}

@Composable
fun WeatherForecastItem(weatherItem: WeatherDataItem) {
    val weather = weatherItem.weather?.firstOrNull()
    val iconUrl = "${BuildConfig.ICONS_BASE_URL}${weather?.icon}@2x.png"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = weatherItem.dtTxt ?: "Unknown Date")
        Text(text = weather?.description ?: "Unknown Weather")

        AsyncImage(
            model = iconUrl,
            contentDescription = "Weather Icon",
            modifier = Modifier.size(48.dp)
        )

        Text(text = "${weatherItem.main?.temp}°C")
    }
}

