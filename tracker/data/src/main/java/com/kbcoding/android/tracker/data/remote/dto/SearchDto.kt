package com.kbcoding.android.tracker.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchDto(
    @field:Json(name = "products")
    val products: List<Product>?,
)