package com.ericcerio.weather.data.remote.source

import com.ericcerio.weather.domain.model.Weather

interface WeatherRemoteSource {
    suspend fun getWeatherByLatLong(latitude: Double, longitude: Double): Weather
}