package com.kbcoding.android.calorietracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kbcoding.android.navigation.AgeScreenRoute
import com.kbcoding.android.navigation.GenderScreenRoute
import com.kbcoding.android.navigation.HeightScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.navigation.WeightScreenRoute
import com.kbcoding.android.navigation.WelcomeScreenRoute
import com.kbcoding.android.onboarding.presentation.age.AgeScreen
import com.kbcoding.android.onboarding.presentation.gender.GenderScreen
import com.kbcoding.android.onboarding.presentation.welcome.WelcomeScreen

@Composable
fun Navigation(
    isShouldShowOnboarding: Boolean,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    CompositionLocalProvider(
        value = LocalNavController provides navController
    ) {

        NavHost(
            navController = navController,
            startDestination = if (isShouldShowOnboarding) WelcomeScreenRoute else GenderScreenRoute,
            modifier = modifier
                .fillMaxSize()
        ) {
            composable<WelcomeScreenRoute> { WelcomeScreen() }
            composable<GenderScreenRoute> { GenderScreen() }
            composable<AgeScreenRoute> { AgeScreen(scaffoldState) }
            composable<HeightScreenRoute> {  }
            composable< WeightScreenRoute> {  }
        }
    }

}