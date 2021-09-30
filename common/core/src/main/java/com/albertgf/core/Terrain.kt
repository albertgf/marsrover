package com.albertgf.core

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

data class Terrain(
    val width: Int,
    val height: Int
) {
    fun move(position: Position, direction: Direction): Option<Position> {
        val x = position.x + direction.get().first
        val y = position.y + direction.get().second

        if ((x > width - 1 || y > height - 1) || x < 0 || y < 0) return None

        return Some(position.update(direction))
    }
     fun withinLimits(x: Int, y: Int): Boolean {
        return y <= height - 1 && y >= 0 && x < width && x >= 0
    }
}