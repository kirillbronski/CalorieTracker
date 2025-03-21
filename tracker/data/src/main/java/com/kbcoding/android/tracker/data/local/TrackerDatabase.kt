package com.kbcoding.android.tracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kbcoding.android.tracker.data.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1
)
abstract class TrackerDatabase: RoomDatabase() {

    abstract val dao: TrackerDao
}