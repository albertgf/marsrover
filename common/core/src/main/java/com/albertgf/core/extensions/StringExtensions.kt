package com.albertgf.core.extensions

fun String.removeLast() : String {
    if (this.isNotEmpty()) return this.dropLast(1)
    return this
}