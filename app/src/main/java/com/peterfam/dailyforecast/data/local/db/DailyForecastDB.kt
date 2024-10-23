package com.peterfam.dailyforecast.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.peterfam.dailyforecast.data.local.model.City


@Database(entities = [City::class], version = 1, exportSchema = false)
abstract class DailyForecastDB : RoomDatabase(){

    abstract fun cityDao(): CityDao

}