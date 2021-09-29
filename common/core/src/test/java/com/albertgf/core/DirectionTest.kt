package com.albertgf.core

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DirectionTest {

    lateinit var direction: Direction

    @Before
    fun before() {
        direction = Direction()
    }

    @Test
    fun initial_position() {
        Assert.assertEquals(direction.string(), "N")
        Assert.assertEquals(direction.get(), Pair(0,1))
    }

    @Test
    fun turn_to_east() {
        direction.turnRight()
        Assert.assertEquals(direction.string(), "E")
        Assert.assertEquals(direction.get(), Pair(1,0))
    }

    @Test
    fun turn_to_west() {
        direction.turnLeft()
        Assert.assertEquals(direction.string(), "W")
        Assert.assertEquals(direction.get(), Pair(-1,0))
    }

    @Test
    fun turn_to_sud() {
        direction.turnLeft()
        direction.turnLeft()
        Assert.assertEquals(direction.string(), "S")
        Assert.assertEquals(direction.get(), Pair(0,-1))
    }
}