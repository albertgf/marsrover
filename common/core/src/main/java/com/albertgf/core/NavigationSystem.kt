package com.albertgf.core

class NavigationSystem(val terrain: Terrain, var position: Position)  {

    fun printLocation(): String {
        return position.print()
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