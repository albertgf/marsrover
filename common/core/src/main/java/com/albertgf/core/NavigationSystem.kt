package com.albertgf.core

import arrow.core.getOrElse

class NavigationSystem(val terrain: Terrain, var position: Position, val direction: Direction) : MovementCommands {

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
        this.position = terrain.move(position, direction).getOrElse { position }
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