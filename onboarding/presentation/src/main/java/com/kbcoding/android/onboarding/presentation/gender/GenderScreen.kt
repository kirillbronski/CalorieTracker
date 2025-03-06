package com.kbcoding.android.onboarding.presentation.gender

import androidx.compose.foundation.layout.*
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
import com.kbcoding.android.domain.model.Gender
import com.kbcoding.android.navigation.AgeScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.onboarding.presentation.components.SelectableButton
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.UiEvent
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel()
) {
    val navController = LocalNavController.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navController.navigate(AgeScreenRoute)
                else -> Unit
            }
        }
    }

    GenderContent(
        onNextClick = viewModel::onNextClick,
        onGenderClick = viewModel::onGenderClick,
        selectedGender = viewModel.selectedGender
    )
}

@Composable
private fun GenderContent(
    onNextClick: () -> Unit,
    onGenderClick: (Gender) -> Unit,
    selectedGender: Gender,
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = selectedGender is Gender.Male,
                    color = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = Color.White,
                    onClick = {
                        onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = selectedGender is Gender.Female,
                    color = MaterialTheme.colorScheme.tertiary,
                    selectedTextColor = Color.White,
                    onClick = { onGenderClick(Gender.Female) },
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
fun GenderScreenPreviewWrapper() {
    CalorieTrackerTheme {
        GenderContent(
            onNextClick = {},
            onGenderClick = {},
            selectedGender = Gender.Male
        )
    }
}