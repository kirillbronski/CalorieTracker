package com.kbcoding.android.tracker.presentation.search

import com.kbcoding.android.tracker.domain.model.TrackableFood

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
