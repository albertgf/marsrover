package com.albertgf.core

class Position {

    private var x = 0
    private var y = 0

    fun initialPosition(x: Int, y: Int) {
        this.x = x
        this.y = y
    }

    fun move(direction: Direction) {
        x += direction.get().first
        y += direction.get().second
    }

    fun print(): String {
        return "$x $y"
    }
}