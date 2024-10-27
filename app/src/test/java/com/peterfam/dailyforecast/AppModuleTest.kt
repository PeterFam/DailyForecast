package com.peterfam.dailyforecast

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import javax.inject.Inject
import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.peterfam.dailyforecast.data.local.db.CityDao
import com.peterfam.dailyforecast.data.local.db.DailyForecastDB
import com.peterfam.dailyforecast.data.local.db.WeatherDao
import com.peterfam.dailyforecast.data.remote.DailyForecastApi
import com.peterfam.dailyforecast.di.AppModule
import com.peterfam.dailyforecast.domain.DailyForecastRepositoryImpl
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

//@HiltAndroidTest
//class AppModuleTest {
//
//    @get:Rule
//    var hiltRule = HiltAndroidRule(this)
//
//    @Inject
//    lateinit var context: Context
//
//    @Inject
//    lateinit var retrofit: Retrofit.Builder
//
//    @Inject
//    lateinit var dailyForecastApi: DailyForecastApi
//
//    @Inject
//    lateinit var dailyForecastDB: DailyForecastDB
//
//    @Inject
//    lateinit var weatherDao: WeatherDao
//
//    @Inject
//    lateinit var cityDao: CityDao
//
//    @Inject
//    lateinit var okHttpClient: OkHttpClient
//
//    @Before
//    fun init() {
//        hiltRule.inject() // Inject dependencies
//    }
//
//    @Test
//    fun `provideOkHttpClient returns OkHttpClient instance`() {
//        assertThat(okHttpClient).isNotNull()
//    }
//
//    @Test
//    fun `provideRetrofit returns Retrofit instance`() {
//        val retrofitInstance = retrofit.baseUrl(BuildConfig.BASE_URL).build()
//        assertThat(retrofitInstance).isNotNull()
//    }
//
//    @Test
//    fun `provideDailyForecastApi returns DailyForecastApi instance`() {
//        assertThat(dailyForecastApi).isNotNull()
//    }
//
//    @Test
//    fun `provideRoomDatabase returns DailyForecastDB instance`() {
//        assertThat(dailyForecastDB).isNotNull()
//    }
//
//    @Test
//    fun `provideCityDao returns CityDao instance`() {
//        assertThat(cityDao).isNotNull()
//    }
//
//    @Test
//    fun `provideWeatherDao returns WeatherDao instance`() {
//        assertThat(weatherDao).isNotNull()
//    }
//
//    @Test
//    fun `provideDailyForecastRepository returns DailyForecastRepositoryImpl instance`() {
//        val repository = AppModule.provideDailyForecastRepository(
//            context,
//            weatherDao,
//            cityDao,
//            dailyForecastApi
//        )
//        assertThat(repository).isInstanceOf(DailyForecastRepositoryImpl::class.java)
//    }
//}