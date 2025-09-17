package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.data.repository.WeatherRepository
import com.ericcerio.weather.utils.Resource
import kotlinx.coroutines.flow.flow
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
            val weather = repository.getCurrentLocationWeather(lat, long)
            repository.saveCurrentWeather(weather)
            emit(Resource.Success(weather))
            emit(Resource.Loading(false))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}
