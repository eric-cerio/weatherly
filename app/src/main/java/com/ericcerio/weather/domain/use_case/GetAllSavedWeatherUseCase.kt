package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.data.repository.WeatherRepository
import com.ericcerio.weather.domain.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllSavedWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {
    suspend operator fun invoke(): List<Weather> = withContext(Dispatchers.IO) {
        repository.getSavedWeather()
    }
}
