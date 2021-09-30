package com.albertgf.core.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoverData(
    @Json(name = "topRightCorner") val terrainLimits: Coordinates,
    @Json(name = "roverPosition") val position: Coordinates,
    @Json(name = "roverDirection") val direction: String,
    val movements: String
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    val x: Int,
    val y: Int
)