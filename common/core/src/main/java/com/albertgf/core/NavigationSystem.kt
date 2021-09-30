package com.albertgf.core

class NavigationSystem(val terrain: Terrain, var position: Position)  {

    fun printLocation(): String {
        return position.print()
    }

    fun input(input: String) {
        input.forEach {
            position = when (it) {
                'L' -> position.left() as Position
                'R' -> position.right() as Position
                'M' -> position.move(terrain) as Position
                else -> position
            }
        }
    }
}