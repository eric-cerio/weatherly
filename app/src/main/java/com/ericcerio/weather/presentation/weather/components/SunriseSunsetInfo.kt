package com.ericcerio.weather.presentation.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ericcerio.weather.R
import com.ericcerio.weather.presentation.ui.theme.WeatherAppTheme
import com.ericcerio.weather.utils.dimensions.Size

@Composable
fun SunriseSunsetInfo(icon: Int, time: String, label: String) {
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = label, // Important for accessibility
            modifier = Modifier.size(Size.XX_LARGE),
        )
        Spacer(modifier = Modifier.height(Size.MICRO))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant // A slightly muted color
        )
        Text(
            text = time,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun SunriseSunsetInfoPreview() {
    WeatherAppTheme {
        Surface {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SunriseSunsetInfo(
                    icon = R.drawable.ic_sunrise,
                    label = "Sunrise",
                    time = "5:41 AM"
                )
                SunriseSunsetInfo(
                    icon = R.drawable.ic_sunset,
                    label = "Sunset",
                    time = "5:56 PM"
                )
            }
        }
    }
}
