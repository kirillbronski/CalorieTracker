package com.kbcoding.android.tracker.domain.di

import com.kbcoding.android.domain.preferences.Preferences
import com.kbcoding.android.tracker.domain.repository.TrackerRepository
import com.kbcoding.android.tracker.domain.useCase.CalculateMealNutrients
import com.kbcoding.android.tracker.domain.useCase.DeleteTrackedFood
import com.kbcoding.android.tracker.domain.useCase.GetFoodsForDate
import com.kbcoding.android.tracker.domain.useCase.SearchFood
import com.kbcoding.android.tracker.domain.useCase.TrackFood
import com.kbcoding.android.tracker.domain.useCase.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}