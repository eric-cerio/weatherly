package com.ericcerio.weather.data.repository.auth

import com.ericcerio.weather.presentation.auth.User

interface AuthRepository {
    // Dummy user for login
    fun register(username: String, password: String): Boolean

    fun login(username: String, password: String): Boolean
}
