package com.ericcerio.weather.data.local.source

import com.ericcerio.weather.domain.model.Weather

interface WeatherLocalSource {
    suspend fun getSavedWeather(): List<Weather>
}