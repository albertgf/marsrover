package com.albertgf.core


data class Terrain(
    val width: Int,
    val height: Int
) {
     fun withinLimits(x: Int, y: Int): Boolean {
        return y <= height - 1 && y >= 0 && x < width && x >= 0
    }
}