package com.kbcoding.android.onboarding.presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.kbcoding.android.domain.model.ActivityLevel
import com.kbcoding.android.navigation.GoalScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.onboarding.presentation.components.SelectableButton
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.UiEvent
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun ActivityScreen(
    viewModel: ActivityViewModel = hiltViewModel()
) {

    val navController = LocalNavController.current


    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navController.navigate(GoalScreenRoute)
                else -> Unit
            }
        }
    }

    ActivityContent(
        selectedActivityLevel = viewModel.selectedActivityLevel,
        onActivityLevelSelect = viewModel::onActivityLevelSelect,
        onNextClick = viewModel::onNextClick
    )

}

@Composable
private fun ActivityContent(
    selectedActivityLevel: ActivityLevel,
    onActivityLevelSelect: (ActivityLevel) -> Unit,
    onNextClick: () -> Unit,
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
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = selectedActivityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelSelect(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelSelect(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onActivityLevelSelect(ActivityLevel.High)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
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
fun ActivityContentPreview() {
    CalorieTrackerTheme {
        ActivityContent(
            selectedActivityLevel = ActivityLevel.Medium,
            onActivityLevelSelect = {},
            onNextClick = {},
        )
    }
}