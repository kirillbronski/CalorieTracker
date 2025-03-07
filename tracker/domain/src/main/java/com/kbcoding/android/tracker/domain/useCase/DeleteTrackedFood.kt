package com.kbcoding.android.tracker.domain.useCase

import com.kbcoding.android.tracker.domain.model.TrackedFood
import com.kbcoding.android.tracker.domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}