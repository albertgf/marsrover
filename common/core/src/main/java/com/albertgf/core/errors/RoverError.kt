package com.albertgf.core.errors

sealed class RoverError {
    object NotValidInput : RoverError()
}
