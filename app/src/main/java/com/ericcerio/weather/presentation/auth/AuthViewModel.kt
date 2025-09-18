package com.ericcerio.weather.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ericcerio.weather.domain.use_case.LoginUseCase
import com.ericcerio.weather.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    fun register(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = if (registerUseCase.invoke(username, password)) {
                AuthState.RegisterSuccess
            } else {
                AuthState.RegisterFailed
            }
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _authState.value = if (loginUseCase.invoke(username, password)) {
                AuthState.LoginSuccess
            } else {
                AuthState.LoginFailed
            }
        }
    }
}

