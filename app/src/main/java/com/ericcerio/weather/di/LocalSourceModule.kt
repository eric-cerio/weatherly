package com.ericcerio.weather.di

import com.ericcerio.weather.data.local.WeatherDao
import com.ericcerio.weather.data.local.source.WeatherLocalSource
import com.ericcerio.weather.data.local.source.WeatherLocalSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalSourceModule {

    @Provides
    @Singleton
    fun provideWeatherLocalSource(weatherDao: WeatherDao): WeatherLocalSource {
        return WeatherLocalSourceImpl(weatherDao)
    }
}
