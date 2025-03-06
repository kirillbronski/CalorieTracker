package com.kbcoding.android.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.kbcoding.android.ui.theme.CalorieTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalorieTrackerTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalorieTrackerApp(
                        //modifier = Modifier.padding(innerPadding)
                    )
                //}
            }
        }
    }
}

@Composable
fun CalorieTrackerApp(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Navigation(
        navController = navController
    )
}

