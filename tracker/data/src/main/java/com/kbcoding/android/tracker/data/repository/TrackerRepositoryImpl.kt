package com.kbcoding.android.tracker.data.repository

import com.kbcoding.android.tracker.data.local.TrackerDao
import com.kbcoding.android.tracker.data.mapper.toTrackableFood
import com.kbcoding.android.tracker.data.mapper.toTrackedFood
import com.kbcoding.android.tracker.data.mapper.toTrackedFoodEntity
import com.kbcoding.android.tracker.data.remote.OpenFoodApi
import com.kbcoding.android.tracker.domain.model.TrackableFood
import com.kbcoding.android.tracker.domain.model.TrackedFood
import com.kbcoding.android.tracker.domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {

            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize
            )
            println("Search DTO: $searchDto")
            val products = searchDto.products.orEmpty()
            println("Products: $products")
            Result.success(
                products.filter {
                    val carbohydrates = it.nutriments?.carbohydrates100g ?: 0.0
                    val proteins = it.nutriments?.proteins100g ?: 0.0
                    val fat = it.nutriments?.fat100g ?: 0.0
                    val energyKcal = it.nutriments?.energyKcal100g ?: 0.0

                    val calculatedCalories = carbohydrates * 4f + proteins * 4f + fat * 9f
                    val lowerBound = calculatedCalories * 0.99f
                    val upperBound = calculatedCalories * 1.01f

                    energyKcal in (lowerBound..upperBound)
                }.mapNotNull {
                    it.toTrackableFood()
                }
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}