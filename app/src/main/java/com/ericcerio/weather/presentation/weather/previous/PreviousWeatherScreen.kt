package com.ericcerio.weather.presentation.weather.previous

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.presentation.weather.components.WeatherItemCard
import com.ericcerio.weather.utils.dimensions.Paddings
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.res.stringResource
import com.ericcerio.weather.R

@Composable
fun PreviousWeatherScreen(
    viewModel: PreviousWeatherViewModel = hiltViewModel()
) {
    val savedWeathers = viewModel.savedWeathers.observeAsState().value
    PreviousWeatherScreen(savedWeathers.orEmpty())
}

@Composable
fun PreviousWeatherScreen(previousWeatherList: List<Weather>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Text(
            text = stringResource(R.string.saved_weather_data),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = Paddings.LARGE)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(previousWeatherList.size) { item ->
                WeatherItemCard(data = previousWeatherList[item])
            }
        }
    }
}

@Preview
@Composable
fun PreviousWeatherScreenPreview() {
    PreviousWeatherScreen(
        listOf(
            Weather(
                city = "Cebu City",
                country = "Philippines",
                temperature = 27.0,
                condition = "Clear",
                dateTime = 1758064950,
                sunRise = System.currentTimeMillis(),
                sunSet = System.currentTimeMillis(),
                icon = "",
                windSpeed = 0.0
            ),
            Weather(
                city = "Cebu City",
                country = "Philippines",
                temperature = 27.0,
                condition = "Clear",
                dateTime = 1758064950,
                sunRise = System.currentTimeMillis(),
                sunSet = System.currentTimeMillis(),
                icon = "",
                windSpeed = 0.0
            )
        )
    )
}
