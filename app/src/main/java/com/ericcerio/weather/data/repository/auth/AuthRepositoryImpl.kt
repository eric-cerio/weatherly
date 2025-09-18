package com.ericcerio.weather.data.repository.auth

import com.ericcerio.weather.presentation.auth.User
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(): AuthRepository {

    private val users = mutableListOf(User("testuser", "password123"))

    override fun register(username: String, password: String): Boolean {
        if (users.any { it.username == username }) return false
        users.add(User(username, password))
        return true
    }

    override fun login(username: String, password: String): Boolean {
        return users.any { it.username == username && it.password == password }
    }
}
