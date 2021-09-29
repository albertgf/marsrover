package com.albertgf.core

class NavigationSystem(val terrain: Terrain, val position: Position, val direction: Direction) : MovementCommands {

    fun printLocation(): String {
        return "${position.print()} ${direction.print()}"
    }

    fun input(input: String) {
        input.forEach {
            when (it) {
                'L' -> left()
                'R' -> right()
                'M' -> move()
            }
        }
    }

    override fun move(): MovementCommands {
        position.update(direction)
        return this
    }

    override fun left(): MovementCommands {
        direction.turnLeft()
        return this
    }

    override fun right(): MovementCommands {
        direction.turnRight()
        return this
    }
}