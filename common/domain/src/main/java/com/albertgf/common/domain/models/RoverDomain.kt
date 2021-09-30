package com.albertgf.common.domain.models

import androidx.compose.runtime.Composable

data class RoverDomain (
    var terrain:Int = 0,
    var x:Int = 0,
    var y:Int = 0,
    var instructions: String = ""
)