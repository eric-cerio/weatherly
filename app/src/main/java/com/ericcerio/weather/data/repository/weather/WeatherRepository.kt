package com.ericcerio.weather.data.repository.weather

import com.ericcerio.weather.domain.model.Place
import com.ericcerio.weather.domain.model.Weather

interface WeatherRepository {

    suspend fun getPlace(lat: Double, long: Double): Place

    suspend fun getCurrentLocationWeather(
        placeName: String
    ): Weather

    fun getSavedWeather(): List<Weather>

    fun saveCurrentWeather(weather: Weather)
}
