package com.albertgf.common.domain.models

sealed class ResourceState {
    object IDLE : ResourceState()
    object SETUP : ResourceState()
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
}