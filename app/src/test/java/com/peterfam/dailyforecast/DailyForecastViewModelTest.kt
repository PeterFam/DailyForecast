package com.peterfam.dailyforecast

import com.peterfam.dailyforecast.data.remote.response.CitiesItem
import com.peterfam.dailyforecast.data.remote.response.WeatherDataItem
import com.peterfam.dailyforecast.domain.DailyForecastRepository
import com.peterfam.dailyforecast.presentation.viewmodel.DailyForecastViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DailyForecastViewModelTest {
    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: DailyForecastViewModel
    private val repository: DailyForecastRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set test dispatcher for coroutines
        viewModel = DailyForecastViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset dispatcher after test
    }

    @Test
    fun `getCities should update citiesList`() = runTest {
        // Mock the repository response
        val mockCities = listOf(CitiesItem(id = 1, cityNameEn = "Cairo"))
        coEvery { repository.getCities() } returns mockCities

        // Call the function
        viewModel.getCities()

        // Assertions
        assertEquals(mockCities, viewModel.citiesList)
        assertFalse(viewModel.isLoading)
    }

    @Test
    fun `fetchWeather should update weatherData on success`() = runTest {
        val mockWeatherData = listOf(WeatherDataItem(dtTxt = "2024-10-22 12:00"))
        coEvery { repository.getWeatherData(any()) } returns mockWeatherData

        viewModel.fetchWeather(CitiesItem(id = 1, cityNameEn = "Cairo"))

        assertEquals(mockWeatherData, viewModel.weatherData)
        assertNull(viewModel.errorMessage)
    }

    @Test
    fun `fetchWeather should set errorMessage on failure`() = runTest {
        coEvery { repository.getWeatherData(any()) } throws Exception("Network Error")

        viewModel.fetchWeather(CitiesItem(id = 1, cityNameEn = "Cairo"))

        assertEquals("Failed to load weather data.", viewModel.errorMessage)
        assertNull(viewModel.weatherData)
    }
}