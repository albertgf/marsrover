package com.albertgf.common.domain.models

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null,
    val error: Exception? = null
)