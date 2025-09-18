package com.ericcerio.weather.utils

sealed class Screen(val rout: String) {
    object CurrentWeatherScreen : Screen("currentWeatherScreen")
    object PreviousWeatherScreen : Screen("previousWeatherScreen")
    object LoginScreen : Screen("loginScreen")
    object RegisterScreen : Screen("registerScreen")
}
