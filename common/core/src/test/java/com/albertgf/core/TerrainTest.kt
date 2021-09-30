package com.albertgf.core

import org.junit.Assert
import org.junit.Test

class TerrainTest {

    private val terrain = Terrain(5,5)

    @Test
    fun inside_limits() {
        Assert.assertTrue(terrain.withinLimits(0,0))
    }

    @Test
    fun outside_limits_below_0() {
        Assert.assertFalse(terrain.withinLimits(-1,0))
    }

    @Test
    fun outside_limits_above_height() {
        Assert.assertFalse(terrain.withinLimits(0,5))
    }
}