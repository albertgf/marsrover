package com.albertgf.core

import com.albertgf.core.models.RoverData
import com.squareup.moshi.Moshi
import org.junit.Assert.assertEquals
import org.junit.Test

class ParseTest {

    private val loader = javaClass.classLoader!!
    private val moshi = Moshi.Builder().build()

    @Test
    fun parse() {
        val jsonString = String(loader.getResourceAsStream("data.json").readBytes())
        val actual = moshi.adapter(RoverData::class.java).fromJson(jsonString)!!

        assertEquals(actual.direction , "N")
    }
}