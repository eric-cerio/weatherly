package com.ericcerio.weather.domain.model

import com.ericcerio.weather.data.local.WeatherEntity

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

fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        id = this.id,
        city = this.city,
        country = this.country,
        temperature = this.temperature,
        sunRise = this.sunRise.toLong(),
        sunSet = this.sunSet.toLong(),
        condition = this.condition,
        icon = this.icon,
        windSpeed = this.windSpeed,
        currentTime = this.currentTime
    )
}
