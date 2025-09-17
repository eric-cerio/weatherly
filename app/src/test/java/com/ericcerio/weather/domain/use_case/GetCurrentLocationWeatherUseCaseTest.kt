package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.data.repository.WeatherRepository
import com.ericcerio.weather.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetCurrentLocationWeatherUseCaseTest {
    private lateinit var useCase: GetCurrentLocationWeatherUseCase
    private val repository = mockk<WeatherRepository>()

    @Before
    fun setup() {
        useCase = GetCurrentLocationWeatherUseCase(repository)
    }

    @Test
    fun `invoke emits success when repository returns data`() = runTest {
        val weather = Weather(id = 1, city = "Cebu", country = "PH", temperature = 300.0, condition = "Clear", dateTime = 0, sunRise = 0, sunSet = 0, icon = "", windSpeed = 0.0)
        coEvery { repository.getCurrentLocationWeather(any(), any()) } returns weather
        coEvery { repository.saveCurrentWeather(any()) } returns Unit
        val result = useCase(10.0, 123.0)
        var successEmitted = false
        result.collect {
            if (it is Resource.Success) {
                assert(it.data == weather)
                successEmitted = true
            }
        }
        assert(successEmitted)
    }

    @Test
    fun `invoke emits error when repository throws`() = runTest {
        coEvery { repository.getCurrentLocationWeather(any(), any()) } throws Exception("error")
        val result = useCase(10.0, 123.0)
        var errorEmitted = false
        result.collect {
            if (it is Resource.Error) {
                assert(it.message == "error")
                errorEmitted = true
            }
        }
        assert(errorEmitted)
    }
}
