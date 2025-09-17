package com.ericcerio.weather.presentation.weather.current

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.domain.use_case.GetCurrentLocationWeatherUseCase
import com.ericcerio.weather.utils.LocationHelper
import com.ericcerio.weather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentLocationWeatherUseCase: GetCurrentLocationWeatherUseCase,
    private val locationHelper: LocationHelper
) : ViewModel() {

    private val _weatherState = mutableStateOf(CurrentWeatherState())
    val weatherState = _weatherState

    fun getCurrentLocationWeather(lat: Double, long: Double) {
        getCurrentLocationWeatherUseCase(lat, long).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _weatherState.value = weatherState.value.copy(
                        isLoading = result.data as Boolean
                    )
                }
                is Resource.Success -> {
                    _weatherState.value = weatherState.value.copy(
                        weatherData = result.data as Weather,
                        isLoading = false,
                    )
                }
                is Resource.Error -> {
                    _weatherState.value = weatherState.value.copy(
                        isLoading = false,
                        error = result.message as String,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun fetchWeatherForDeviceLocation(onLocationError: (String) -> Unit = {}) {
        locationHelper.getLocation { location ->
            if (location != null) {
                getCurrentLocationWeather(location.latitude, location.longitude)
            } else {
                onLocationError("Location unavailable")
            }
        }
    }
}
