package com.ericcerio.weather.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {

    fun formatUnixToReadableDate(unixTimestamp: Long): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zoneId = ZoneId.of("Asia/Manila")

        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
            .withZone(zoneId)

        return formatter.format(instant)
    }

    fun formatUnixToReadableTime(unixTimestamp: Long): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zoneId = ZoneId.of("Asia/Manila")

        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            .withZone(zoneId)

        return formatter.format(instant)
    }
}
