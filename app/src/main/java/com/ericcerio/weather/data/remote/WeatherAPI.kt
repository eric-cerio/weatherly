package com.ericcerio.weather.data.remote

import com.ericcerio.weather.BuildConfig
import com.ericcerio.weather.data.remote.dto.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")
    suspend fun getWeatherByLatLong(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): WeatherDTO
}
