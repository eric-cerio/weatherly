package com.ericcerio.weather.data.remote.dto

import com.ericcerio.weather.domain.model.Weather
import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("base")
    val base: String?,
    @SerializedName("clouds")
    val clouds: CloudsDTO?,
    @SerializedName("cod")
    val cod: Int?,
    @SerializedName("coord")
    val coord: CoordDTO?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: MainDTO?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rain")
    val rain: RainDTO?,
    @SerializedName("sys")
    val sys: SysDTO?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: List<WeatherDetailsDTO?>?,
    @SerializedName("wind")
    val wind: WindDTO?,
)

data class CloudsDTO(
    @SerializedName("all")
    val all: Int?,
)

data class CoordDTO(
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
)

data class MainDTO(
    @SerializedName("feels_like")
    val feelsLike: Double?,
    @SerializedName("grnd_level")
    val grndLevel: Int?,
    @SerializedName("humidity")
    val humidity: Int?,
    @SerializedName("pressure")
    val pressure: Int?,
    @SerializedName("sea_level")
    val seaLevel: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("temp_max")
    val tempMax: Double?,
    @SerializedName("temp_min")
    val tempMin: Double?,
)

data class RainDTO(
    @SerializedName("1h")
    val h: Double?,
)

data class SysDTO(
    @SerializedName("country")
    val country: String?,
    @SerializedName("sunrise")
    val sunrise: Int?,
    @SerializedName("sunset")
    val sunset: Int?,
)

data class WeatherDetailsDTO(
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: String?,
)

data class WindDTO(
    @SerializedName("deg")
    val deg: Int?,
    @SerializedName("gust")
    val gust: Double?,
    @SerializedName("speed")
    val speed: Double?,
)

fun WeatherDTO.toDomain(): Weather {
    return Weather(
        id = this.weather?.get(0)?.id ?: 0,
        city = this.name ?: "",
        country = this.sys?.country ?: "",
        temperature = this.main?.temp ?: 0.0,
        sunRise = (this.sys?.sunrise ?: 0),
        sunSet = (this.sys?.sunset ?: 0),
        condition = this.weather?.get(0)?.main ?: "",
        icon = this.weather?.get(0)?.icon ?: "",
        windSpeed = this.wind?.speed ?: 0.0,
        currentTime = this.dt ?: 0,
    )
}

// app key = e1f2bc4da5ac3e6170583aa8b2f9ba9d
