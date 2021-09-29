package com.albertgf.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class IntExtensionsTest {

    val max = 4

    @Test
    fun substract_from_0_should_get_minus_max() {
        val value = -1
        val result = (-1).fmod(max)
        assertEquals(result, max + value)
    }

    @Test
    fun get_max_size_should_get_first_element() {
        val value = 4
        val result = (value).fmod(max)
        assertEquals(result, max - value)
    }
}