package com.ericcerio.weather.presentation.weather.current

import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.domain.use_case.GetCurrentLocationWeatherUseCase
import com.ericcerio.weather.utils.LocationHelper
import com.ericcerio.weather.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CurrentWeatherViewModelTest {
    private lateinit var viewModel: CurrentWeatherViewModel
    private val getCurrentLocationWeatherUseCase = mockk<GetCurrentLocationWeatherUseCase>()
    private val locationHelper = mockk<LocationHelper>(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CurrentWeatherViewModel(getCurrentLocationWeatherUseCase, locationHelper)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCurrentLocationWeather emits success and updates state`() = runTest {
        val weather = Weather(id = 1, city = "Cebu", country = "PH", temperature = 300.0, condition = "Clear", dateTime = 0, sunRise = 0, sunSet = 0, icon = "", windSpeed = 0.0)
        coEvery { getCurrentLocationWeatherUseCase(any(), any()) } returns flowOf(Resource.Success(weather))
        viewModel.getCurrentLocationWeather(10.0, 123.0)
        advanceUntilIdle()
        assertEquals(weather, viewModel.weatherState.value.weatherData)
        assertEquals(false, viewModel.weatherState.value.isLoading)
    }

    @Test
    fun `getCurrentLocationWeather emits error and updates state`() = runTest {
        coEvery { getCurrentLocationWeatherUseCase(any(), any()) } returns flowOf(Resource.Error("error"))
        viewModel.getCurrentLocationWeather(10.0, 123.0)
        advanceUntilIdle()
        assertEquals("error", viewModel.weatherState.value.error)
        assertEquals(false, viewModel.weatherState.value.isLoading)
    }
}
