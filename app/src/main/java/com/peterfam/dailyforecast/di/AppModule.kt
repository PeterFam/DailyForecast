package com.peterfam.dailyforecast.di

import android.content.Context
import androidx.room.Room
import com.peterfam.dailyforecast.data.local.db.CityDao
import com.peterfam.dailyforecast.data.local.db.DailyForecastDB
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
        return Room.databaseBuilder(context, DailyForecastDB::class.java, Constants.DBName).build()
    }

    @Singleton
    @Provides
    fun provideDao(database: DailyForecastDB): CityDao = database.cityDao()



}