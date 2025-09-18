package com.ericcerio.weather.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ericcerio.weather.presentation.auth.AuthViewModel
import com.ericcerio.weather.presentation.auth.LoginScreen
import com.ericcerio.weather.presentation.auth.RegisterScreen
import com.ericcerio.weather.presentation.ui.theme.WeatherAppTheme
import com.ericcerio.weather.presentation.weather.current.CurrentWeatherScreen
import com.ericcerio.weather.presentation.weather.previous.PreviousWeatherScreen
import com.ericcerio.weather.utils.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    val authState = authViewModel.authState.collectAsState().value

                    Column(modifier = Modifier.fillMaxSize()) {
                        Box(modifier = Modifier.weight(1f)) {
                            NavHost(
                                navController = navController,
                                startDestination = if (authState is com.ericcerio.weather.presentation.auth.AuthState.LoginSuccess) Screen.CurrentWeatherScreen.rout else Screen.LoginScreen.rout
                            ) {
                                composable(Screen.LoginScreen.rout) {
                                    LoginScreen(
                                        viewModel = authViewModel,
                                        onLoginSuccess = {
                                            navController.navigate(Screen.CurrentWeatherScreen.rout) {
                                                popUpTo(Screen.LoginScreen.rout) { inclusive = true }
                                            }
                                        },
                                        onNavigateToRegister = {
                                            navController.navigate(Screen.RegisterScreen.rout)
                                        }
                                    )
                                }
                                composable(Screen.RegisterScreen.rout) {
                                    RegisterScreen(
                                        viewModel = authViewModel,
                                        onRegisterSuccess = {
                                            navController.navigate(Screen.LoginScreen.rout) {
                                                popUpTo(Screen.RegisterScreen.rout) { inclusive = true }
                                            }
                                        },
                                        onNavigateToLogin = {
                                            navController.popBackStack()
                                        }
                                    )
                                }
                                composable(Screen.CurrentWeatherScreen.rout) {
                                    CurrentWeatherScreen()
                                }
                                composable(Screen.PreviousWeatherScreen.rout) {
                                    PreviousWeatherScreen()
                                }
                            }
                        }
                        if (authState is com.ericcerio.weather.presentation.auth.AuthState.LoginSuccess) {
                            BottomNavBar(navController = navController)
                        }
                    }
                }
            }
        }
    }
}
