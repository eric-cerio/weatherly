package com.ericcerio.weather.data.repository.weather

import com.ericcerio.weather.data.local.source.WeatherLocalSource
import com.ericcerio.weather.data.remote.source.WeatherRemoteSource
import com.ericcerio.weather.domain.model.Place
import com.ericcerio.weather.domain.model.Weather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: WeatherRemoteSource,
    private val localDataSource: WeatherLocalSource,
) : WeatherRepository {

    override suspend fun getPlace(lat: Double, long: Double): Place {
        return remoteDataSource.getPlace(lat, long)
    }

    override suspend fun getCurrentLocationWeather(
        placeName: String
    ): Weather {
        return remoteDataSource.getWeatherByLatLong(placeName)
    }


    override fun getSavedWeather(): List<Weather> {
        return localDataSource.getSavedWeather()
    }

    override fun saveCurrentWeather(weather: Weather) {
        return localDataSource.saveWeather(weather)
    }
}
