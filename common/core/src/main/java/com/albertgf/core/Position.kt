package com.albertgf.core

data class Position(
    private var x:Int = 0,
    private var y:Int = 0
) {
    fun print(): String {
        return "$x $y"
    }

    fun update(direction: Direction) {
        x += direction.get().first
        y += direction.get().second
    }
}