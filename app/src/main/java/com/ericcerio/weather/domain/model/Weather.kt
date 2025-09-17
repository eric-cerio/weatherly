package com.ericcerio.weather.domain.model

import com.ericcerio.weather.data.local.WeatherEntity

data class Weather(
    val id: Int = 0,
    val city: String,
    val country: String,
    val temperature: Double,
    val sunRise: Long,
    val sunSet: Long,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val dateTime: Long,
) {
    fun getCelsius(): Double {
        return temperature - 273.15
    }
}

fun Weather.toEntity(): WeatherEntity {
    return WeatherEntity(
        id = this.id,
        city = this.city,
        country = this.country,
        temperature = this.temperature,
        sunRise = this.sunRise,
        sunSet = this.sunSet,
        condition = this.condition,
        icon = this.icon,
        windSpeed = this.windSpeed,
        dateTime = this.dateTime,
    )
}
