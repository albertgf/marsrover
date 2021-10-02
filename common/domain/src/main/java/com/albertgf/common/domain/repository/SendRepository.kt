package com.albertgf.common.domain.repository

import com.albertgf.common.domain.models.Resource
import com.albertgf.common.domain.models.ResourceState
import com.albertgf.common.domain.models.RoverDomain
import com.albertgf.core.DataParser
import com.albertgf.core.Rover
import com.albertgf.core.models.Coordinates
import com.albertgf.core.models.RoverData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SendRepository(
    private val rover: Rover,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun send(data: RoverDomain): Flow<Resource<String>> = flow {
        emit(Resource(state = ResourceState.IDLE))
        val roverData = RoverData(
            terrainLimits = Coordinates(data.terrain, data.terrain),
            direction = data.direction,
            movements = data.instructions,
            position = Coordinates(data.x, data.y)
        )
        delay(1500)
        emit(Resource(state = ResourceState.SETUP))
        rover.setup(DataParser.toString(roverData)).
            fold( {
                emit(Resource<String>(state = ResourceState.ERROR))
            },{
                delay(1500)
                emit(Resource(state = ResourceState.LOADING))
                val result = rover.explore()
                delay(1500)
                emit(Resource(state = ResourceState.SUCCESS, data = result))
            })
    }.flowOn(dispatcher)
}