package com.ericcerio.weather.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(data: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    fun getAllSavedWeather(): List<WeatherEntity>
}
