package com.albertgf.common.domain.repository

import com.albertgf.common.domain.models.RoverDomain
import com.albertgf.core.DataParser
import com.albertgf.core.Rover
import com.albertgf.core.models.Coordinates
import com.albertgf.core.models.RoverData

class SendRepository(private val rover: Rover) {

    fun send(data: RoverDomain) : String {
        val roverData = RoverData(
            terrainLimits = Coordinates(data.terrain, data.terrain),
            direction = data.direction,
            movements = data.instructions,
            position = Coordinates(data.x, data.y)
        )

        rover.setup(DataParser.toString(roverData))

        return rover.explore()
    }
}