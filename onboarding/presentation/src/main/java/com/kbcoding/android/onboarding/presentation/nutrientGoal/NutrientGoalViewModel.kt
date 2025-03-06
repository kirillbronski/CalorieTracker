package com.kbcoding.android.onboarding.presentation.nutrientGoal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kbcoding.android.domain.preferences.Preferences
import com.kbcoding.android.domain.use_case.FilterOutDigits
import com.kbcoding.android.onboarding.domain.use_case.ValidateNutrients
import com.kbcoding.android.ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
): ViewModel() {

    private val _uiState = MutableStateFlow(NutrientGoalState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvent) {
        when(event) {
            is NutrientGoalEvent.OnCarbRatioEnter -> {
                _uiState.value = uiState.value.copy(
                    carbsRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                _uiState.value = uiState.value.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnFatRatioEnter -> {
                _uiState.value = uiState.value.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbsRatioText = uiState.value.carbsRatio,
                    proteinRatioText = uiState.value.proteinRatio,
                    fatRatioText = uiState.value.fatRatio
                )
                when(result) {
                    is ValidateNutrients.Result.Success -> {
                        preferences.saveCarbRatio(result.carbsRatio)
                        preferences.saveProteinRatio(result.proteinRatio)
                        preferences.saveFatRatio(result.fatRatio)
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackbar(result.message))
                        }
                    }
                }
            }
        }
    }
}