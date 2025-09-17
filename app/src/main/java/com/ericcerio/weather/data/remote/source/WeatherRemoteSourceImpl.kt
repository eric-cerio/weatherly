package com.ericcerio.weather.data.remote.source

import com.ericcerio.weather.data.remote.WeatherAPI
import com.ericcerio.weather.data.remote.dto.toDomain
import com.ericcerio.weather.domain.model.Weather
import javax.inject.Inject

class WeatherRemoteSourceImpl @Inject constructor(
    private val weatherAPI: WeatherAPI,
) : WeatherRemoteSource {
    override suspend fun getWeatherByLatLong(
        latitude: Double,
        longitude: Double,
    ): Weather {
        return weatherAPI.getWeatherByLatLong(latitude, longitude).toDomain()
    }
}
