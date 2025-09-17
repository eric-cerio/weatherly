package com.ericcerio.weather.di

import android.content.Context
import androidx.room.Room
import com.ericcerio.weather.data.local.WeatherDao
import com.ericcerio.weather.data.local.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWeatherDatabase(@ApplicationContext app: Context): WeatherDatabase =
        Room.databaseBuilder(app, WeatherDatabase::class.java, "weather_db").build()

    @Provides
    @Singleton
    fun provideWeatherDao(db: WeatherDatabase): WeatherDao = db.weatherDao()
}
