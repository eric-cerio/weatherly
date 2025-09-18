package com.ericcerio.weather.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ericcerio.weather.R
import com.ericcerio.weather.utils.dimensions.Size


@Composable
fun RegisterScreen(
    authState: AuthState,
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    doRegister: (String, String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        if (authState is AuthState.RegisterSuccess) {
            onRegisterSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = "App Icon",
            modifier = Modifier.size(96.dp)
        )
        Spacer(modifier = Modifier.height(Size.LARGE))
        Text(stringResource(R.string.register), style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(Size.LARGE))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(stringResource(R.string.username)) }
        )
        Spacer(modifier = Modifier.height(Size.SMALL))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(Size.LARGE))
        Button(onClick = { doRegister(username, password) }) {
            Text(stringResource(R.string.register))
        }
        Spacer(modifier = Modifier.height(Size.SMALL))
        TextButton(onClick = onNavigateToLogin) {
            Text(stringResource(R.string.already_have_an_account_login))
        }
        if (authState is AuthState.RegisterFailed) {
            Text(stringResource(R.string.registration_failed_username_taken), color = MaterialTheme.colorScheme.error)
        }
    }
}

