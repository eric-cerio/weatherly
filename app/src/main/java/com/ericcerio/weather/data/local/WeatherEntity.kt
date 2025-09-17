package com.ericcerio.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ericcerio.weather.domain.model.Weather


@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey
    val id: Int = 0,
    val city: String,
    val country: String,
    val temperature: Double,
    val sunRise: Long,
    val sunSet: Long,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val currentTime: Int,
)

fun WeatherEntity.toDomain(): Weather {
    return Weather(
        id = this.id,
        city = this.city,
        country = this.country,
        temperature = this.temperature,
        sunRise = this.sunRise.toInt(),
        sunSet = this.sunSet.toInt(),
        condition = this.condition,
        icon = this.icon,
        windSpeed = this.windSpeed,
        currentTime = this.currentTime
    )
}