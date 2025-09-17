package com.ericcerio.weather.di

import com.ericcerio.weather.data.local.source.WeatherLocalSource
import com.ericcerio.weather.data.remote.source.WeatherRemoteSource
import com.ericcerio.weather.data.repository.WeatherRepository
import com.ericcerio.weather.data.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(weatherLocalSource: WeatherLocalSource, weatherRemoteSource: WeatherRemoteSource): WeatherRepository {
        return WeatherRepositoryImpl(weatherRemoteSource, weatherLocalSource)
    }
}
