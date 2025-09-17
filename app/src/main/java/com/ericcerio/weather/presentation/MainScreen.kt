package com.ericcerio.weather.presentation

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    val tabTitles = listOf("Current", "History")
// Remember the pager state
    val pagerState = rememberPagerState { tabTitles.size }
}
