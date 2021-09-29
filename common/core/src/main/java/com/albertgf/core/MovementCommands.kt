package com.albertgf.core

interface MovementCommands {
    fun move() : MovementCommands
    fun left() : MovementCommands
    fun right() : MovementCommands
}