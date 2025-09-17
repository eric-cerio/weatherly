package com.ericcerio.weather.data.local.source

import com.ericcerio.weather.data.local.WeatherDao
import com.ericcerio.weather.data.local.toDomain
import com.ericcerio.weather.domain.model.Weather
import javax.inject.Inject

class WeatherLocalSourceImpl @Inject constructor(
    private val weatherDao: WeatherDao
): WeatherLocalSource {

    override suspend fun getSavedWeather(): List<Weather> {
        return weatherDao.getAllSavedWeather().map { it.toDomain() }
    }
}