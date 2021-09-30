package com.albertgf.core

interface MovementCommand {
    fun move(terrain: Terrain) : MovementCommand
    fun left() : MovementCommand
    fun right() : MovementCommand
}