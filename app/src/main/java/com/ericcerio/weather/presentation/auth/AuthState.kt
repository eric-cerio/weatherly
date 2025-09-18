package com.ericcerio.weather.presentation.auth

sealed class AuthState {
    object Idle : AuthState()
    object RegisterSuccess : AuthState()
    object RegisterFailed : AuthState()
    object LoginSuccess : AuthState()
    object LoginFailed : AuthState()
}
