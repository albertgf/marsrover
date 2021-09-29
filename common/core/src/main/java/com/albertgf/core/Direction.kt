package com.albertgf.core

import com.albertgf.core.extensions.fmod

class Direction {
    private var current = 0
    /*
    DIRECTIONS SORTED AS N E S W to work with clockwise and counterclockwise on turn
     */
    private val directions = listOf(
        Pair(0,1),
        Pair(1,0),
        Pair(0,-1),
        Pair(-1,0)
    )

    fun turnLeft() {
        current = (--current).fmod(directions.size)
    }

    fun turnRight() {
        current = (++current).fmod(directions.size)
    }

    fun get(): Pair<Int, Int> {
        return directions[current]
    }

    fun string(): String {
        return when(current) {
            0 -> "N"
            1 -> "E"
            2 -> "S"
            3 -> "W"
            else -> ""
        }
    }
}