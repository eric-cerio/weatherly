package com.ericcerio.weather.presentation.auth

import androidx.lifecycle.ViewModel
import com.ericcerio.weather.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object RegisterSuccess : AuthState()
    object RegisterFailed : AuthState()
    object LoginSuccess : AuthState()
    object LoginFailed : AuthState()
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(username: String, password: String) {
        _authState.value = if (repository.register(username, password)) {
            AuthState.RegisterSuccess
        } else {
            AuthState.RegisterFailed
        }
    }

    fun login(username: String, password: String) {
        _authState.value = if (repository.login(username, password)) {
            AuthState.LoginSuccess
        } else {
            AuthState.LoginFailed
        }
    }
}

