package com.kbcoding.android.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kbcoding.android.domain.preferences.Preferences
import com.kbcoding.android.ui.theme.CalorieTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()

        setContent {
            CalorieTrackerTheme {
                CalorieTrackerApp(shouldShowOnboarding)
            }
        }
    }
}

@Composable
fun CalorieTrackerApp(
    isShouldShowOnboarding: Boolean,
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }

    SetupSnackbar(snackbarHostState)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { padding ->
        Navigation(
            isShouldShowOnboarding = isShouldShowOnboarding,
            navController = navController,
            modifier = modifier.padding(padding)
        )
    }
}

