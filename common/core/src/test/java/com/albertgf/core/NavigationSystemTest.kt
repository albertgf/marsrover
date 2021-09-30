package com.albertgf.core

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class NavigationSystemTest {

    private lateinit var navSystem: NavigationSystem

    @Before
    fun setup() {
        navSystem = NavigationSystem(Terrain(5,5), position = Position(0,0), direction = Direction("N"))
    }

    @Test
    fun move_3_times_forward_succes() {
        navSystem.input("MMM")

        Assert.assertEquals(navSystem.printLocation(), "0 3 N")
    }

    @Test
    fun stop_at_terrain_limit() {
        navSystem.input("MMMMMM")

        Assert.assertEquals(navSystem.printLocation(), "0 4 N")
    }

    @Test
    fun stop_at_terrain_limit2() {
        val navSystemExample = NavigationSystem(Terrain(5,5), position = Position(1,2), direction = Direction("N"))
        navSystemExample.input("LMLMLMLMM")

        Assert.assertEquals(navSystemExample.printLocation(), "1 3 N")
    }

}