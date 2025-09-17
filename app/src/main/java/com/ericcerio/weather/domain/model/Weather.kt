package com.ericcerio.weather.domain.model

data class Weather(
    val id: Int = 0,
    val city: String,
    val country: String,
    val temperature: Double,
    val sunRise: Int,
    val sunSet: Int,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val currentTime: Int
) {

    fun  getCelsius(): Double {
        return temperature - 273.15
    }
}
