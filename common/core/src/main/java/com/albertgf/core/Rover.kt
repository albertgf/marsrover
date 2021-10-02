package com.albertgf.core

import arrow.core.Either
import arrow.core.flatMap
import com.albertgf.core.errors.RoverError

class Rover {
    private lateinit var navigationSystem: NavigationSystem
    private var instructions: String = ""

    fun setup(data: String): Either<RoverError, Boolean> {
        return DataParser.fromString(data).flatMap { input ->
            instructions = input.movements
            navigationSystem = NavigationSystem(
                terrain = Terrain(input.terrainLimits.x, input.terrainLimits.y),
                position = Position(
                    input.position.x,
                    input.position.y,
                    direction = Direction(input.direction)
                )
            )

            return navigationSystem.isValid()
        }
    }

    fun explore(): String {
        return navigationSystem.input(instructions)
    }

    fun print(): String {
        return navigationSystem.printLocation()
    }
}