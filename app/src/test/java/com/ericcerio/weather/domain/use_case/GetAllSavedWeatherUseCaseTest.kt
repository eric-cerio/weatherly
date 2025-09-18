package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.data.repository.weather.WeatherRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAllSavedWeatherUseCaseTest {
    private val repository = mockk<WeatherRepository>()
    private val useCase = GetAllSavedWeatherUseCase(repository)

    @Test
    fun `invoke returns data when repository returns data`() = runTest {
        val weatherList = listOf(Weather(id = 1, city = "Cebu", country = "PH", temperature = 300.0, condition = "Clear", dateTime = 0, sunRise = 0, sunSet = 0, icon = "", windSpeed = 0.0))
        coEvery { repository.getSavedWeather() } returns weatherList
        val result = useCase()
        assert(result == weatherList)
    }

    @Test
    fun `invoke throws when repository throws`() = runTest {
        coEvery { repository.getSavedWeather() } throws Exception("error")
        try {
            useCase()
            assert(false) // Should not reach here
        } catch (e: Exception) {
            assert(e.message == "error")
        }
    }
}

