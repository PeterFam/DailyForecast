package com.peterfam.dailyforecast.di

import android.content.Context
import androidx.room.Room
import com.peterfam.dailyforecast.data.local.db.CityDao
import com.peterfam.dailyforecast.data.local.db.DailyForecastDB
import com.peterfam.dailyforecast.data.local.db.WeatherDao
import com.peterfam.dailyforecast.data.remote.DailyForecastApi
import com.peterfam.dailyforecast.domain.DailyForecastRepository
import com.peterfam.dailyforecast.domain.DailyForecastRepositoryImpl
import com.peterfam.dailyforecast.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(1, java.util.concurrent.TimeUnit.MINUTES)
            .writeTimeout(1, java.util.concurrent.TimeUnit.MINUTES)
            .connectTimeout(1, java.util.concurrent.TimeUnit.MINUTES)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)

    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): DailyForecastDB{
        return Room.databaseBuilder(
            context,
            DailyForecastDB::class.java,
            Constants.DBName)
            .build()
    }

    @Singleton
    @Provides
    fun provideDailyForecastApi(retrofit: Retrofit.Builder): DailyForecastApi = retrofit.baseUrl("").build().create()

    @Singleton
    @Provides
    fun provideCityDao(database: DailyForecastDB): CityDao = database.cityDao()

    @Singleton
    @Provides
    fun provideWeatherDao(database: DailyForecastDB): WeatherDao = database.weatherDao()

    @Singleton
    @Provides
    fun provideDailyForecastRepository(
        @ApplicationContext context: Context,
        weatherDao: WeatherDao,
        cityDao: CityDao,
        api: DailyForecastApi): DailyForecastRepository
    = DailyForecastRepositoryImpl(context, weatherDao, cityDao, api)


}