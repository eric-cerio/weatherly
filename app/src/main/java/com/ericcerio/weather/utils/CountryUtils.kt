package com.ericcerio.weather.utils

import java.util.Locale

object CountryUtils {
    fun getCountryNameFromCode(code: String): String {
        return try {
            Locale("", code).displayCountry.takeIf { it.isNotBlank() } ?: code
        } catch (e: Exception) {
            code
        }
    }
}

