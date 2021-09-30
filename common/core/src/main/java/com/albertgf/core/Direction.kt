package com.albertgf.core

import com.albertgf.core.extensions.fmod
import java.lang.IllegalArgumentException

class Direction(directionChar: String) {

    private var current: Int

    init {
        current = when (directionChar) {
            "N" -> 0
            "E" -> 1
            "S" -> 2
            "W" -> 3
            else -> throw IllegalArgumentException("Direction not valid")
        }
    }

    /*
    DIRECTIONS SORTED AS N E S W to work with clockwise and counterclockwise on turn
     */
    private val directions = listOf(
        Pair(0,1),
        Pair(1,0),
        Pair(0,-1),
        Pair(-1,0)
    )

    fun turnLeft(): Direction {
        current = (--current).fmod(directions.size)
        return this
    }

    fun turnRight() : Direction {
        current = (++current).fmod(directions.size)
        return this
    }

    fun x(): Int {
        return directions[current].first
    }

    fun y(): Int {
        return directions[current].second
    }

    fun get(): Pair<Int, Int> = directions[current]

    fun print(): String {
        return when(current) {
            0 -> "N"
            1 -> "E"
            2 -> "S"
            3 -> "W"
            else -> ""
        }
    }
}