package com.kbcoding.android.calorietracker

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.kbcoding.android.ui.ObserveAsEvents
import com.kbcoding.android.ui.SnackbarController
import kotlinx.coroutines.launch

@Composable
internal fun SetupSnackbar(
    snackbarHostState: SnackbarHostState,
) {

    val scope = rememberCoroutineScope()

    ObserveAsEvents(flow = SnackbarController.events, snackbarHostState) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result = snackbarHostState.showSnackbar(
                message = event.message,
                actionLabel = event.action?.label,
                withDismissAction = true,
                duration = SnackbarDuration.Long,
            )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }
}