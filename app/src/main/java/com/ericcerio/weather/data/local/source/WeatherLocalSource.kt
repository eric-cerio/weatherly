package com.ericcerio.weather.data.local.source

import com.ericcerio.weather.domain.model.Weather

interface WeatherLocalSource {

    fun saveWeather(weather: Weather)

    fun getSavedWeather(): List<Weather>
}