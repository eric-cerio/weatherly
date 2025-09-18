package com.ericcerio.weather.data.remote

import com.ericcerio.weather.BuildConfig
import com.ericcerio.weather.data.remote.dto.PlaceItemDTO
import com.ericcerio.weather.data.remote.dto.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("data/2.5/weather")
    suspend fun getWeatherByLatLong(
        @Query("q") placeName: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): WeatherDTO

    @GET("geo/1.0/reverse")
    suspend fun getCityNameByLatLong(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("limit") limit: Int = 1,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
    ): List<PlaceItemDTO>
}
