package com.albertgf.common.domain.repository

import com.albertgf.common.domain.models.RoverDomain
import com.albertgf.core.DataParser
import com.albertgf.core.Rover
import com.albertgf.core.models.Coordinates
import com.albertgf.core.models.RoverData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SendRepository(
    private val rover: Rover,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun send(data: RoverDomain): String {
        return withContext(dispatcher) {
            val roverData = RoverData(
                terrainLimits = Coordinates(data.terrain, data.terrain),
                direction = data.direction,
                movements = data.instructions,
                position = Coordinates(data.x, data.y)
            )

            rover.setup(DataParser.toString(roverData))

            rover.explore()
        }
    }
}