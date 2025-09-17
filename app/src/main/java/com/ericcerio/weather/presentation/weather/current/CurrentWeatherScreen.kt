package com.ericcerio.weather.presentation.weather.current

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ericcerio.weather.R
import com.ericcerio.weather.presentation.ui.theme.WeatherAppTheme
import com.ericcerio.weather.presentation.weather.components.SunriseSunsetInfo
import com.ericcerio.weather.utils.DateUtils
import com.ericcerio.weather.utils.dimensions.Paddings

@Composable
fun CurrentWeatherScreen(
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.weatherState
    CurrentWeatherScreen(state)
}

@Composable
fun CurrentWeatherScreen(
    state: CurrentWeatherState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(Paddings.LARGE),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }
            state.error.isNotEmpty() -> {
                Text(
                    text = state.error,
                    color = Color.Red,
                    style = MaterialTheme.typography.titleLarge
                )
            }
            state.weatherData.id != 0 -> {
                val weather = state.weatherData
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = weather.city,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.height(Paddings.MICRO))
                    Text(
                        text = DateUtils.formatToReadableDate(weather.dateTime),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Gray
                    )
                }


                Image(
                    painter = painterResource(id = weather.getWeatherIcon(weather.condition)),
                    contentDescription = weather.condition,
                    modifier = Modifier.size(128.dp)
                )

                Text(
                    text = weather.getCelsius(),
                    style = MaterialTheme.typography.displayLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SunriseSunsetInfo(
                        icon = R.drawable.ic_sunrise,
                        time = DateUtils.formatToReadableTime(weather.sunRise),
                        label = stringResource(R.string.sunrise)
                    )
                    SunriseSunsetInfo(
                        icon = R.drawable.ic_sunset,
                        time = DateUtils.formatToReadableTime(weather.sunSet),
                        label = stringResource(R.string.sunset)
                    )
                }
            }
            else -> {
                Text(
                    text = stringResource(R.string.no_weather_data_available),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun CurrentWeatherScreenPreview() {
    WeatherAppTheme {
        // Provide a preview state if needed
        // CurrentWeatherScreen(state = ...)
        CurrentWeatherScreen()
    }
}
