package com.ericcerio.weather.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ericcerio.weather.R
import com.ericcerio.weather.utils.dimensions.Size

@Composable
fun LoginScreen(
    authState: AuthState,
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    doLogin: (String, String) -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState) {
        if (authState is AuthState.LoginSuccess) {
            onLoginSuccess()
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
        Text(stringResource(R.string.login), style = MaterialTheme.typography.headlineMedium)
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
        Button(onClick = { doLogin(username, password) }) {
            Text(stringResource(R.string.login))
        }
        Spacer(modifier = Modifier.height(Size.SMALL))
        TextButton(onClick = onNavigateToRegister) {
            Text(stringResource(R.string.don_t_have_an_account_register))
        }
        if (authState is AuthState.LoginFailed) {
            Text(stringResource(R.string.login_failed_try_again), color = MaterialTheme.colorScheme.error)
        }
    }
}
