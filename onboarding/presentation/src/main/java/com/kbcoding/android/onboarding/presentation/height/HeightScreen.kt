package com.kbcoding.android.onboarding.presentation.height

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
fun HeightScreen(
    scaffoldState: ScaffoldState,
    viewModel: HeightViewModel = hiltViewModel()
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
        height = viewModel.height,
        onNextClick = viewModel::onNextClick,
        onHeightEnter = viewModel::onHeightEnter
    )

}

@Composable
fun HeightContent(
    height: String,
    onNextClick: () -> Unit,
    onHeightEnter: (String) -> Unit,
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
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = height,
                onValueChange = onHeightEnter,
                unit = stringResource(id = R.string.cm)
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
            height = "180",
            onNextClick = {},
            onHeightEnter = {}
        )
    }
}