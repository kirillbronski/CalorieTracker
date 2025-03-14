package com.kbcoding.android.tracker.data.mapper

import com.kbcoding.android.tracker.data.remote.dto.Product
import com.kbcoding.android.tracker.domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood(): TrackableFood? {
    val carbsPer100g = this.nutriments?.carbohydrates100g?.roundToInt()
    val proteinPer100g = this.nutriments?.proteins100g?.roundToInt()
    val fatPer100g = this.nutriments?.fat100g?.roundToInt()
    val caloriesPer100g = this.nutriments?.energyKcal100g?.roundToInt()
    return if (carbsPer100g == null && proteinPer100g == null && fatPer100g == null && caloriesPer100g == null ||
        carbsPer100g == 0 && proteinPer100g == 0 && fatPer100g == 0 && caloriesPer100g == 0
    ) {
        null
    } else {
        TrackableFood(
            name = productName ?: return null,
            carbsPer100g = carbsPer100g ?: 0,
            proteinPer100g = proteinPer100g ?: 0,
            fatPer100g = fatPer100g ?: 0,
            caloriesPer100g = caloriesPer100g ?: 0,
            imageUrl = imageFrontThumbUrl ?: ""
        )
    }
}