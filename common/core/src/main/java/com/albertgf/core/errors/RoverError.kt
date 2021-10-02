package com.albertgf.core.errors

sealed class RoverError {
    object NotValidInput : RoverError()
    object NotValidTerrain : RoverError()
    object NotValidPosition : RoverError()
}
