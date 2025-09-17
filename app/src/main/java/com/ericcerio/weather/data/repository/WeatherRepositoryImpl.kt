package com.ericcerio.weather.data.repository

import com.ericcerio.weather.data.local.source.WeatherLocalSource
import com.ericcerio.weather.data.remote.source.WeatherRemoteSource
import com.ericcerio.weather.domain.model.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteSource,
    private val localDataSource: WeatherLocalSource
): WeatherRepository {
    override suspend fun getCurrentLocationWeather(
        lat: Double,
        long: Double
    ): Weather {
        return remoteDataSource.getWeatherByLatLong(lat, long)
    }

    override fun getSavedWeather(): List<Weather> {
        return localDataSource.getSavedWeather()
    }

    override fun saveCurrentWeather(weather: Weather) {
        return localDataSource.saveWeather(weather)
    }

}