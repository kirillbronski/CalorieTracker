package com.kbcoding.android.calorietracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kbcoding.android.navigation.GenderScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.navigation.WelcomeScreenRoute
import com.kbcoding.android.onboarding.presentation.gender.GenderScreen
import com.kbcoding.android.onboarding.presentation.welcome.WelcomeScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    CompositionLocalProvider(
        value = LocalNavController provides navController
    ) {

        NavHost(
            navController = navController,
            startDestination = WelcomeScreenRoute,
            modifier = modifier
                .fillMaxSize()
        ) {
            composable<WelcomeScreenRoute> { WelcomeScreen() }
            composable<GenderScreenRoute> { GenderScreen() }
        }
    }

}