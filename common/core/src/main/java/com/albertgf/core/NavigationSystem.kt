package com.albertgf.core

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.albertgf.core.errors.RoverError

class NavigationSystem(val terrain: Terrain, var position: Position)  {

    fun printLocation(): String {
        return position.print()
    }

    fun isValid() : Either<RoverError, Boolean> {
        if (!terrain.isValid()) return RoverError.NotValidTerrain.left()

        if (!terrain.withinLimits(position.x, position.y)) return RoverError.NotValidPosition.left()

        return true.right()
    }

    fun input(input: String) : String {
        input.forEach {
            position = when (it) {
                'L' -> position.left()
                'R' -> position.right()
                'M' -> position.move(terrain)
                else -> position //manage the possibility of wrong char in input
            }
        }

        return position.print()
    }
}