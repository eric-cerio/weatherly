package com.ericcerio.weather.domain.model

data class Place(
    val country: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val state: String
)
