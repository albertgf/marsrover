package com.albertgf.core

class Rover {
    private lateinit var navigationSystem: NavigationSystem
    private var instructions: String = ""

    fun setup(data: String) {
        val input = DataParser.fromString(data)

        navigationSystem = NavigationSystem(
            terrain = Terrain(input.terrainLimits.x, input.terrainLimits.y),
            position = Position(input.position.x, input.position.y, direction = Direction(input.direction))
        )

        instructions = input.movements
    }

    fun explore(): String {
        return navigationSystem.input(instructions)
    }

    fun print(): String {
        return navigationSystem.printLocation()
    }
}