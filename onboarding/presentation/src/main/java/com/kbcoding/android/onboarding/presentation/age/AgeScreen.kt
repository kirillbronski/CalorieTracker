package com.kbcoding.android.onboarding.presentation.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.android.navigation.AgeScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.onboarding.presentation.components.UnitTextField
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.UiEvent
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun AgeScreen(
    scaffoldState: ScaffoldState,
    viewModel: AgeViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val navController = LocalNavController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navController.navigate(AgeScreenRoute)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    AgeContent(
        age = viewModel.age,
        onNextClick = viewModel::onNextClick,
        onAgeEnter = viewModel::onAgeEnter
    )
}

@Composable
private fun AgeContent(
    age: String,
    onNextClick: () -> Unit,
    onAgeEnter: (String) -> Unit,
) {

    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = age,
                onValueChange = onAgeEnter,
                unit = stringResource(id = R.string.years)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showSystemUi = true, apiLevel = 30)
@Composable
fun AgeScreenPreview() {
    CalorieTrackerTheme {
        AgeContent(
            age = "20",
            onNextClick = {},
            onAgeEnter = {}
        )
    }
}