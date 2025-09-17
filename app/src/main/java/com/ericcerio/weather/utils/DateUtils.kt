package com.ericcerio.weather.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    fun formatToReadableDate(unixTimestamp: Long): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zoneId = ZoneId.of("Asia/Manila")

        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH)
            .withZone(zoneId)

        return formatter.format(instant)
    }

    fun formatToReadableTime(unixTimestamp: Long): String {
        val instant = Instant.ofEpochSecond(unixTimestamp)
        val zoneId = ZoneId.of("Asia/Manila")

        val formatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
            .withZone(zoneId)

        return formatter.format(instant)
    }

    // Helper to get hour from unix timestamp
    fun getHour(unixTime: Long): Int {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = unixTime * 1000 // Convert seconds to ms
        return calendar.get(Calendar.HOUR_OF_DAY)
    }
}
