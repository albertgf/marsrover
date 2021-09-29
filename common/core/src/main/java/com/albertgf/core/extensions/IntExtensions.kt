package com.albertgf.core.extensions

fun Int.fmod(other: Int) : Int {
    return ((this % other) + other) % other
}