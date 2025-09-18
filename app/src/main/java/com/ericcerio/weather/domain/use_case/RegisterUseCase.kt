package com.ericcerio.weather.domain.use_case

import com.ericcerio.weather.data.repository.auth.AuthRepository
import javax.inject.Inject


class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean {
        return authRepository.register(username, password)
    }
}
