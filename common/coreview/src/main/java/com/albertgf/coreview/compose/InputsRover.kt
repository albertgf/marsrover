package com.albertgf.coreview.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(value: State<Int>, onValueChange: (Int) -> Unit, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        BtnIconCircle(Icons.Filled.Remove, onValueChange, -1)
        Text(
            "${value.value}",
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 64.sp,
            color = MaterialTheme.colors.primary
        )
        BtnIconCircle(Icons.Filled.Add, onValueChange, 1)
    }
}

@Composable
fun DirectionsInput(
    direction: State<String>,
    onDirectionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Button(
            onClick = { onDirectionChange("N") },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "N")  {
                    MaterialTheme.colors.primary
                } else MaterialTheme.colors.background
            )
        ) {
            Text(text = "N")
        }
        Button(
            onClick = { onDirectionChange("E") },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "E") {
                    MaterialTheme.colors.primary
                } else MaterialTheme.colors.background
            )
        ) {
            Text(text = "E")
        }
        Button(
            onClick = { onDirectionChange("S") },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "S") {
                    MaterialTheme.colors.primary
                } else MaterialTheme.colors.background
            )
        ) {
            Text(text = "S")
        }
        Button(
            onClick = { onDirectionChange("W") },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "W") {
                    MaterialTheme.colors.primary
                } else MaterialTheme.colors.background
            )
        ) {
            Text(text = "W")
        }
    }
}

@Composable
fun InstructionsInput(onAdd: (String) -> Unit, onRemove: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        BtnIconCircle(
            icon = Icons.Filled.RotateLeft,
            onSend = { c -> onAdd(c) },
            value = "L"
        )
        BtnIconCircle(
            icon = Icons.Filled.ArrowUpward,
            onSend = { c -> onAdd(c) },
            value = "M"
        )
        BtnIconCircle(
            icon = Icons.Filled.RotateRight,
            onSend = { c -> onAdd(c) },
            value = "R"
        )
        BtnIconCircle(
            icon = Icons.Filled.Delete,
            onSend = { onRemove() },
            value = ""
        )
    }
}