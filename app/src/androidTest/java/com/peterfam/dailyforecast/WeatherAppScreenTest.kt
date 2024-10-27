package com.peterfam.dailyforecast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.Main
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem
import com.peterfam.dailyforecast.data.remote.response.WeatherItem
import com.peterfam.dailyforecast.presentation.ui.AppBarWithDropdown
import com.peterfam.dailyforecast.presentation.ui.WeatherAppScreen
import com.peterfam.dailyforecast.presentation.ui.WeatherForecastList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherAppScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockCities = listOf(
        CitiesItem(id = 1, cityNameEn = "New York", cityNameAr = "نيويورك", lon = -74.0, lat = 40.7),
        CitiesItem(id = 2, cityNameEn = "London", cityNameAr = "لندن", lon = -0.1, lat = 51.5)
    )

    private val mockWeatherData = listOf(
        WeatherDataItem(
            dtTxt = "2024-10-23 12:00:00",
            weather = listOf(WeatherItem(description = "Clear", icon = "01d")),
            main = Main(temp = 23.0)
        ),
        WeatherDataItem(
            dtTxt = "2024-10-24 12:00:00",
            weather = listOf(WeatherItem(description = "Rain", icon = "09d")),
            main = Main(temp = 18.0)
        )
    )

    @Test
    fun appBarWithDropdown_displaysSelectedCityName() {
        composeTestRule.setContent {
            AppBarWithDropdown(
                cities = mockCities,
                selectedCity = mockCities[0],
                onCitySelected = {}
            )
        }

        composeTestRule.onNodeWithText("New York").assertExists()
        composeTestRule.onNodeWithContentDescription("Select City").performClick()
        composeTestRule.onNodeWithText("London").assertExists()
    }

    @Test
    fun weatherAppScreen_showsLoadingIndicator() {
        composeTestRule.setContent {
            WeatherAppScreen(
                cities = mockCities,
                selectedCity = mockCities[0],
                onCitySelected = {},
                weatherData = null,
                isLoading = true,
                errorMessage = null,
                onRetry = {}
            )
        }

        composeTestRule.onNodeWithTag("CircularProgressIndicator").assertExists()
    }

    @Test
    fun weatherAppScreen_showsErrorMessageWithRetry() {
        composeTestRule.setContent {
            WeatherAppScreen(
                cities = mockCities,
                selectedCity = mockCities[0],
                onCitySelected = {},
                weatherData = null,
                isLoading = false,
                errorMessage = "Failed to load weather data.",
                onRetry = {}
            )
        }

        composeTestRule.onNodeWithText("Failed to load weather data.").assertExists()
        composeTestRule.onNodeWithText("Retry").assertExists()
    }

    @Test
    fun weatherForecastList_displaysWeatherItems() {
        composeTestRule.setContent {
            WeatherForecastList(weatherData = mockWeatherData)
        }

        composeTestRule.onNodeWithText("2024-10-23 12:00:00").assertExists()
        composeTestRule.onNodeWithText("Clear").assertExists()
        composeTestRule.onNodeWithText("23.0°C").assertExists()

        composeTestRule.onNodeWithText("2024-10-24 12:00:00").assertExists()
        composeTestRule.onNodeWithText("Rain").assertExists()
        composeTestRule.onNodeWithText("18.0°C").assertExists()
    }
}