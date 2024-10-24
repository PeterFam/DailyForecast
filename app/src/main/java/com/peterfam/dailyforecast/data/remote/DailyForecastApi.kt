package com.peterfam.dailyforecast.data.remote

import com.peterfam.dailyforecast.data.remote.response.CitiesResponse
import com.peterfam.dailyforecast.data.remote.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface DailyForecastApi {

@GET("/data/2.5/forecast")
suspend fun getWeather(@Query("lat") lat: Double,
                       @Query("lon") lon: Double,
                       @Query("apiKey") apiKey: String): Response<WeatherResponse>

@GET
suspend fun getCities(
    @Url url: String = ""
): Response<CitiesResponse>
}