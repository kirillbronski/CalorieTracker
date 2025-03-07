package com.kbcoding.android.tracker.domain.use_case

import com.kbcoding.android.tracker.domain.model.TrackedFood
import com.kbcoding.android.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}