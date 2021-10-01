package com.albertgf.features.generator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albertgf.common.domain.models.Resource
import com.albertgf.common.domain.models.ResourceState
import com.albertgf.common.domain.models.RoverDomain
import com.albertgf.common.domain.repository.SendRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class GeneratorViewModel(private val sendRepository: SendRepository) : ViewModel() {

    private val _terrain = MutableStateFlow(1)
    val terrainSize = _terrain.asStateFlow()

    private val _x = MutableStateFlow(0)
    val x = _x.asStateFlow()

    private val _y = MutableStateFlow(0)
    val y = _y.asStateFlow()

    private val _instructions = MutableStateFlow("")
    val instructions = _instructions.asStateFlow()

    private val _direction = MutableStateFlow("S")
    val direction = _direction.asStateFlow()

    private val _result = MutableStateFlow(Resource<String>(state = ResourceState.IDLE))
    val result = _result.asStateFlow()

    fun updateTerrainSize(value: Int) {
        val terrain = _terrain.value + value
        if (terrain > 0) {
            _terrain.value = terrain
        }
    }

    fun updateX(value: Int) {
        val newX = _x.value + value
        if (newX >= 0 && newX < _terrain.value) _x.value = newX
    }

    fun updateY(value: Int) {
        val newY = _y.value + value
        if (newY >= 0 && newY < _terrain.value) _y.value = newY
    }

    fun addInstruction(char: String) {
        _instructions.value += char
    }

    fun removeInstruction() {
        if (_instructions.value.isNotEmpty()) _instructions.value = instructions.value.dropLast(1)
    }

    fun point(direction: String) {
        _direction.value = direction
    }

    fun sendData() {
        viewModelScope.launch {
            sendRepository.send(
                RoverDomain(
                    terrain = _terrain.value,
                    x = _x.value,
                    y = _y.value,
                    instructions = _instructions.value,
                    direction = _direction.value
                )).collect {
                    _result.value = it
            }
        }
    }
}