package com.ericcerio.weather.presentation.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ericcerio.weather.R
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.presentation.ui.theme.WeatherAppTheme
import com.ericcerio.weather.utils.DateUtils
import com.ericcerio.weather.utils.dimensions.Paddings
import com.ericcerio.weather.utils.dimensions.Size

@Composable
fun WeatherItemCard(data: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(Paddings.MEDIUM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(data.getWeatherIcon(data.condition)),
                contentDescription = data.condition,
                modifier = Modifier.size(Size.XXXX_LARGE)
            )

            Spacer(modifier = Modifier.width(Size.LARGE))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.city, fontWeight = FontWeight.Bold)
                Text(
                    text = DateUtils.formatToReadableDate(data.dateTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = data.getCelsius(),
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Paddings.LARGE),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_sunrise),
                contentDescription = stringResource(R.string.sunrise),
                modifier = Modifier.size(Size.X_LARGE),
            )
            Spacer(modifier = Modifier.width(Size.MICRO))
            Text(
                text = DateUtils.formatToReadableTime(data.sunRise),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.width(Size.LARGE))

            Image(
                painter = painterResource(R.drawable.ic_sunset),
                contentDescription = stringResource(R.string.sunset),
                modifier = Modifier.size(Size.X_LARGE),
            )
            Spacer(modifier = Modifier.width(Size.MICRO))
            Text(
                text = DateUtils.formatToReadableTime(data.sunSet),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun WeatherItemCardPreview() {
    WeatherAppTheme {
        WeatherItemCard(
            data = Weather(
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
    }
}
