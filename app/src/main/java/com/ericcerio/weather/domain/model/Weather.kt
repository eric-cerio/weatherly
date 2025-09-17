package com.ericcerio.weather.domain.model

import com.ericcerio.weather.R
import com.ericcerio.weather.data.local.WeatherEntity
import com.ericcerio.weather.utils.DateUtils

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
    fun getCelsius(): String {
        return String.format("%d°C", (temperature - 273.15).toInt())
    }

    companion object {
        fun empty() = Weather(
            city = "",
            country = "",
            temperature = 0.0,
            sunRise = 0L,
            sunSet = 0L,
            condition = "",
            icon = "",
            windSpeed = 0.0,
            dateTime = 0L,
        )
    }


    // Returns a drawable resource ID for the weather icon
    fun getWeatherIcon(condition: String): Int {
        val hour = DateUtils.getHour(dateTime)
        val isNight = hour >= 18 || hour < 6
        return when {
            condition.contains("rain", ignoreCase = true) ->
                R.drawable.outline_rainy_24
            condition.contains("sun", ignoreCase = true) || condition.contains("clear", ignoreCase = true) ->
                if (isNight) R.drawable.outline_moon_stars_24 else R.drawable.baseline_sunny_24
            else -> if (isNight) R.drawable.outline_moon_stars_24 else R.drawable.baseline_sunny_24
        }
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
