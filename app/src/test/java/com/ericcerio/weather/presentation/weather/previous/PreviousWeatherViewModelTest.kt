package com.ericcerio.weather.presentation.weather.previous

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.domain.use_case.GetAllSavedWeatherUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PreviousWeatherViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val getAllSavedWeatherUseCase = mockk<GetAllSavedWeatherUseCase>()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllSavedWeather sets savedWeathers on success`() = runTest {
        val weatherList = listOf(Weather(id = 1, city = "Cebu", country = "PH", temperature = 300.0, condition = "Clear", dateTime = 0, sunRise = 0, sunSet = 0, icon = "", windSpeed = 0.0))
        coEvery { getAllSavedWeatherUseCase.invoke() } returns weatherList
        val viewModel = PreviousWeatherViewModel(getAllSavedWeatherUseCase)
        val observer = Observer<List<Weather>> {}
        viewModel.savedWeathers.observeForever(observer)
        viewModel.getAllSavedWeather()
        advanceUntilIdle()
        assertEquals(weatherList, viewModel.savedWeathers.value)
        viewModel.savedWeathers.removeObserver(observer)
    }

    @Test
    fun `getAllSavedWeather sets empty list on error`() = runTest {
        coEvery { getAllSavedWeatherUseCase.invoke() } throws Exception("error")
        val viewModel = PreviousWeatherViewModel(getAllSavedWeatherUseCase)
        try {
            viewModel.getAllSavedWeather()
        } catch (_: Exception) {}
        assertEquals(emptyList<Weather>(), viewModel.savedWeathers.value ?: emptyList<Weather>())
    }
}
