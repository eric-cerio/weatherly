package com.ericcerio.weather.presentation.weather.current

import com.ericcerio.weather.domain.model.Weather

data class CurrentWeatherState(
    val isLoading: Boolean = false,
    val error: String = "",
    val weatherData: Weather = Weather.empty()
)

