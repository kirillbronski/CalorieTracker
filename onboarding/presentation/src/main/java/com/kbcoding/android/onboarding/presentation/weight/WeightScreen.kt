package com.kbcoding.android.onboarding.presentation.weight

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.navigation.WeightScreenRoute
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.onboarding.presentation.components.UnitTextField
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.UiEvent
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun WeightScreen(
    scaffoldState: ScaffoldState,
    viewModel: WeightViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val navController = LocalNavController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navController.navigate(WeightScreenRoute)
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    HeightContent(
        weight = viewModel.weight,
        onNextClick = viewModel::onNextClick,
        onWeightEnter = viewModel::onWeightEnter
    )

}

@Composable
fun HeightContent(
    weight: String,
    onNextClick: () -> Unit,
    onWeightEnter: (String) -> Unit,
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
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = weight,
                onValueChange = onWeightEnter,
                unit = stringResource(id = R.string.kg)
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
fun HeightContentPreview(modifier: Modifier = Modifier) {
    CalorieTrackerTheme {
        HeightContent(
            weight = "80.0",
            onNextClick = {},
            onWeightEnter = {}
        )
    }
}