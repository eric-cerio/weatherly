package com.ericcerio.weather.di

import com.ericcerio.weather.data.remote.WeatherAPI
import com.ericcerio.weather.data.remote.source.WeatherRemoteSource
import com.ericcerio.weather.data.remote.source.WeatherRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {

    @Provides
    @Singleton
    fun provideWeatherRemoteSource(weatherAPI: WeatherAPI): WeatherRemoteSource  {
        return WeatherRemoteSourceImpl(weatherAPI)
    }

}