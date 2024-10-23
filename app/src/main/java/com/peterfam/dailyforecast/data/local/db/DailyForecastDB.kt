package com.peterfam.dailyforecast.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.peterfam.dailyforecast.data.local.model.CityEntity
import com.peterfam.dailyforecast.data.local.model.WeatherEntity
import com.peterfam.dailyforecast.utils.Converters


@Database(entities = [CityEntity::class, WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DailyForecastDB : RoomDatabase(){

    abstract fun cityDao(): CityDao

    abstract fun weatherDao(): WeatherDao

}