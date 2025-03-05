package com.kbcoding.android.calorietracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun CalorieTrackerApp(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Navigation(
        navController = navController
    )
}