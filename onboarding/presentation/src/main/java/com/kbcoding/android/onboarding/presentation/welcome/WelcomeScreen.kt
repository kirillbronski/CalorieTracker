package com.kbcoding.android.onboarding.presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kbcoding.android.navigation.GenderScreenRoute
import com.kbcoding.android.navigation.LocalNavController
import com.kbcoding.android.onboarding.presentation.components.ActionButton
import com.kbcoding.android.ui.LocalSpacing
import com.kbcoding.android.ui.R
import com.kbcoding.android.ui.theme.CalorieTrackerTheme

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
) {

    val navController = LocalNavController.current

    WelcomeContent(
        onNextClick = {
            navController.navigate(GenderScreenRoute)
        },
        modifier = modifier
    )
}

@Composable
private fun WelcomeContent(
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = {
                onNextClick()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview(showSystemUi = true, apiLevel = 30)
@Composable
fun WelcomeScreenPreview() {

    CalorieTrackerTheme {
        WelcomeContent(onNextClick = {})
    }
}
