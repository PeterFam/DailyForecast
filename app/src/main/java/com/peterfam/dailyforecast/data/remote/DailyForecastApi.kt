package com.peterfam.dailyforecast.data.remote

import com.peterfam.dailyforecast.data.remote.response.CitiesResponse
import com.peterfam.dailyforecast.data.remote.response.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DailyForecastApi {

@GET("data/2.5/forecast?lat={lat}&lon={lon}&appid={apiKey}")
suspend fun getWeather(@Path("lat") lat: Double,
                       @Path("lon") lon: Double,
                       @Path("apiKey") apiKey: String): Response<WeatherResponse>

@GET
suspend fun getCities(
    @Url url: String = "Cities API URL"
): Response<CitiesResponse>
}