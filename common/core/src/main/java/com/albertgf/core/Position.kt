package com.albertgf.core

class Position(
    var x:Int = 0,
    var y:Int = 0,
    var direction: Direction
) : MovementCommand {
    fun print(): String {
        return "$x $y"
    }

    fun update(direction: Direction) : Position {
        x += direction.get().first
        y += direction.get().second
        return this
    }

    override fun move(terrain: Terrain): MovementCommand {
        val pos = Position(x+direction.get().first, y + direction.get().second, direction)
        return if (terrain.withinLimits(pos.x, pos.y))  pos else this
    }

    override fun left(): MovementCommand {
        return Position(x, y, direction.turnLeft())
    }

    override fun right(): MovementCommand {
        return Position(x, y, direction.turnRight())
    }
}