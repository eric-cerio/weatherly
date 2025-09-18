package com.ericcerio.weather.data.remote.dto

import com.ericcerio.weather.domain.model.Place
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceItemDTO(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("state")
    val state: String?
)

fun PlaceItemDTO.toDomain(): Place {
    return Place(
        country = country.orEmpty(),
        lat = lat ?: 0.0,
        lon = lon ?: 0.0,
        name = name.orEmpty(),
        state = state.orEmpty()
    )
}



