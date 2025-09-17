package com.ericcerio.weather.data.repository

import com.ericcerio.weather.domain.model.Weather

interface WeatherRepository {
    suspend fun getCurrentLocationWeather(
        lat: Double,
        long: Double,
    ): Weather

    fun getSavedWeather(): List<Weather>

    fun saveCurrentWeather(weather: Weather)
}
