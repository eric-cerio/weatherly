package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.data.repository.WeatherRepository
import javax.inject.Inject

class GetAllSavedWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke() = repository.getSavedWeather()
}