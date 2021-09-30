package com.albertgf.core

data class Position(
    var x:Int = 0,
    var y:Int = 0
) {
    fun print(): String {
        return "$x $y"
    }

    fun update(direction: Direction) : Position {
        x += direction.get().first
        y += direction.get().second
        return this
    }
}