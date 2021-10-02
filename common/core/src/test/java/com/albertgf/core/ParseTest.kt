package com.albertgf.core

import com.albertgf.core.errors.RoverError
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ParseTest {

    private val loader = javaClass.classLoader!!

    @Test
    fun parse_correct_data() {
        val jsonString = String(loader.getResourceAsStream("data.json").readBytes())
        val actual = DataParser.fromString(jsonString)

        assertTrue(actual.isRight())

        val direction = actual.orNull()
        assertEquals(direction?.direction , "N")
    }

    @Test
    fun parse_wrong_data_with_missing_fiels_should_throw_error() {
        val jsonString = String(loader.getResourceAsStream("data-wrong.json").readBytes())
        val actual = DataParser.fromString(jsonString)

        assertTrue(actual.isLeft())
        val error = actual.swap().orNull()
        assertTrue(error is RoverError.NotValidInput)
    }

    @Test
    fun parse() {
        val jsonString = "wrong_data"
        val actual = DataParser.fromString(jsonString)

        assertTrue(actual.isLeft())
    }
}