package com.albertgf.core

class Rover {
    private lateinit var navigationSystem: NavigationSystem

    fun setup(x: Int, y: Int, direction: String) {
        navigationSystem = NavigationSystem(
            terrain = Terrain(5, 5),
            position = Position(x, y),
            direction = Direction(direction)
        )
    }

    fun print(): String {
        return navigationSystem.printLocation()
    }
}