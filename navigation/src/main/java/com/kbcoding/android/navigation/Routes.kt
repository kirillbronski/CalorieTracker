package com.kbcoding.android.navigation

import kotlinx.serialization.Serializable

@Serializable
data object MainGraph {

}

@Serializable
data object WelcomeScreenRoute

@Serializable
data object AgeScreenRoute

@Serializable
data object GenderScreenRoute

@Serializable
data object HeightScreenRoute

@Serializable
data object WeightScreenRoute

@Serializable
data object ActivityScreenRoute

@Serializable
data object GoalScreenRoute

@Serializable
data object NutrientGoalScreenRoute

@Serializable
data object TrackerOverviewScreenRoute

@Serializable
data class SearchScreenRoute(
    val mealName: String,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int
)