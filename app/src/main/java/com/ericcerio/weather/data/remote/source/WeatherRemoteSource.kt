package com.ericcerio.weather.data.remote.source

import com.ericcerio.weather.domain.model.Place
import com.ericcerio.weather.domain.model.Weather

interface WeatherRemoteSource {

    suspend fun getPlace(lat: Double, long: Double): Place

    suspend fun getWeatherByLatLong(
        placeName: String
    ): Weather
}
