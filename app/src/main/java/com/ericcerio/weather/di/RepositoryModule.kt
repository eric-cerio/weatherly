package com.ericcerio.weather.di

import com.ericcerio.weather.data.local.source.WeatherLocalSource
import com.ericcerio.weather.data.remote.source.WeatherRemoteSource
import com.ericcerio.weather.data.repository.auth.AuthRepository
import com.ericcerio.weather.data.repository.auth.AuthRepositoryImpl
import com.ericcerio.weather.data.repository.weather.WeatherRepository
import com.ericcerio.weather.data.repository.weather.WeatherRepositoryImpl
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

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        // Placeholder for AuthRepository provision
        return AuthRepositoryImpl()
    }
}
