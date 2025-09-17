package com.ericcerio.weather.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ericcerio.weather.utils.Screen

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Current : BottomNavItem(Screen.CurrentWeatherScreen.rout, "Current", Icons.Filled.Cloud)
    object Saved : BottomNavItem(Screen.PreviousWeatherScreen.rout, "Saved", Icons.Filled.History)
}

val bottomNavItems = listOf(
    BottomNavItem.Current,
    BottomNavItem.Saved
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}

