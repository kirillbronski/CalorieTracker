package com.kbcoding.android.onboarding.presentation.nutrientGoal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.navigation.TrackerOverviewScreenRoute
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.onboarding.presentation.components.UnitTextField
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.SnackbarController
import com.kbcoding.android.ui.SnackbarEvent
import com.kbcoding.android.ui.UiEvent
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun NutrientGoalScreen(
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val navController = LocalNavController.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navController.navigate(TrackerOverviewScreenRoute)
                is UiEvent.ShowSnackbar -> {
                    SnackbarController.sendEvent(SnackbarEvent(event.message.asString(context)))
                }

                else -> Unit
            }
        }
    }

    NutrientGoalContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )

}

@Composable
private fun NutrientGoalContent(
    uiState: NutrientGoalState,
    onEvent: (NutrientGoalEvent) -> Unit,
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
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = uiState.carbsRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnCarbRatioEnter(it))
                },
                unit = stringResource(id = R.string.percent_carbs)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = uiState.proteinRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnProteinRatioEnter(it))
                },
                unit = stringResource(id = R.string.percent_proteins)
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = uiState.fatRatio,
                onValueChange = {
                    onEvent(NutrientGoalEvent.OnFatRatioEnter(it))
                },
                unit = stringResource(id = R.string.percent_fats)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                onEvent(NutrientGoalEvent.OnNextClick)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showSystemUi = true, apiLevel = 30)
@Composable
fun NutrientGoalContentPreview() {
    CalorieTrackerTheme {
        NutrientGoalContent(
            uiState = NutrientGoalState(),
            onEvent = {}
        )
    }
}