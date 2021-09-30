package com.albertgf.core

class Position(
    var x:Int = 0,
    var y:Int = 0,
    var direction: Direction
) {
    fun print(): String {
        return "$x $y ${direction.print()}"
    }

    fun move(terrain: Terrain): Position {
        val difX = direction.x()
        val difY = direction.y()
        if (terrain.withinLimits(x + difX, y + difY)){
            x += difX
            y += difY
        }
        return this
    }

    fun left(): Position{
        direction.turnLeft()
        return this
    }

    fun right(): Position {
        direction.turnRight()
        return this
    }
}