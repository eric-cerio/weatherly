package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.data.repository.weather.WeatherRepository
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class GetCurrentLocationWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository,
) {
    operator fun invoke(
        lat: Double,
        long: Double,
    ) = flow {
        try {
            emit(Resource.Loading(true))
            var weather = Weather.empty()
            withContext(Dispatchers.IO) {
                val place = repository.getPlace(lat, long)
                weather = repository.getCurrentLocationWeather(place.name)
                repository.saveCurrentWeather(weather)
            }
            emit(Resource.Success(weather))
            emit(Resource.Loading(false))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
