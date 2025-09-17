package com.ericcerio.weather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ericcerio.weather.domain.model.Weather

@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val id: Int,
    val city: String,
    val country: String,
    val temperature: Double,
    val sunRise: Long,
    val sunSet: Long,
    val condition: String,
    val icon: String,
    val windSpeed: Double,
    val dateTime: Long,
)

fun WeatherEntity.toDomain(): Weather {
    return Weather(
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
