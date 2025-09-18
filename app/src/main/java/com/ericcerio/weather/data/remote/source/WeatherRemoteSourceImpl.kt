package com.ericcerio.weather.data.remote.source

import com.ericcerio.weather.data.remote.WeatherAPI
import com.ericcerio.weather.data.remote.dto.toDomain
import com.ericcerio.weather.domain.model.Place
import com.ericcerio.weather.domain.model.Weather
import javax.inject.Inject

class WeatherRemoteSourceImpl @Inject constructor(
    private val weatherAPI: WeatherAPI,
) : WeatherRemoteSource {

    override suspend fun getPlace(lat: Double, long: Double): Place {
        return weatherAPI.getCityNameByLatLong(lat, long).first().toDomain()
    }
    override suspend fun getWeatherByLatLong(
        placeName: String
    ): Weather {
        return weatherAPI.getWeatherByLatLong(placeName).toDomain()
    }
}
