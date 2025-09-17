package com.ericcerio.weather.presentation.weather.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Brightness4
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericcerio.weather.domain.model.Weather
import com.ericcerio.weather.presentation.ui.theme.WeatherAppTheme
import com.ericcerio.weather.utils.DateUtils

@Composable
fun WeatherItemCard(data: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
            )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (data.condition == "Rain") Icons.Default.WaterDrop else Icons.Default.WbSunny,
                contentDescription = data.condition,
                modifier = Modifier.size(40.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = data.city, fontWeight = FontWeight.Bold)
                Text(
                    text = DateUtils.formatUnixToReadableDate(data.dateTime),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Text(
                text = "${data.temperature}°C",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.WbSunny,
                contentDescription = "Sunrise",
                modifier = Modifier.size(18.dp),
                tint = Color(0xFFFFC107) // Amber color
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = DateUtils.formatUnixToReadableTime(data.sunRise),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Outlined.Brightness4,
                contentDescription = "Sunset",
                modifier = Modifier.size(18.dp),
                tint = Color(0xFF673AB7)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = DateUtils.formatUnixToReadableTime(data.sunSet),
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
