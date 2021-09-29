package com.albertgf.core

class Rover {

    private val position = Position()
    private var direction = Direction()

    fun setup(x: Int, y: Int, direction: String) {
        position.initialPosition(x,y)
    }

    fun move() {
        position.move(direction)
    }

    fun turnLeft() {
        direction.turnLeft()
    }

    fun turnRight() {
        direction.turnRight()
    }
}