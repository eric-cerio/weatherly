package com.ericcerio.weather.presentation.weather.previous

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.domain.use_case.GetAllSavedWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.invoke

@HiltViewModel
class PreviousWeatherViewModel @Inject constructor(
    val getAllSavedWeatherUseCase: GetAllSavedWeatherUseCase
) : ViewModel() {

    init {
        getAllSavedWeather()
    }

    private val _savedWeathers = MutableLiveData<List<Weather>>()
    val savedWeathers: LiveData<List<Weather>> = _savedWeathers

    fun getAllSavedWeather() {
        viewModelScope.launch {
            try {
                val result = getAllSavedWeatherUseCase.invoke()
                _savedWeathers.value = result
            } catch (e: Exception) {
                _savedWeathers.value = emptyList()
            }
        }
    }
}
