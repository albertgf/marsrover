package com.albertgf.coreview.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputField(value: State<Int>, onValueChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        BtnIconCircle(Icons.Filled.Remove, onValueChange, -1)
        Text(
            "${value.value}",
            modifier = Modifier.padding(16.dp),
            fontSize = 64.sp,
            color = MaterialTheme.colors.primary
        )
        BtnIconCircle(Icons.Filled.Add, onValueChange, 1)
    }
}

@Composable
fun DirectionsInput(direction: State<String>, onDirectionChange: (String) -> Unit) {
    Row {
        Button(onClick = { onDirectionChange("N") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "N") Color.Black else Color.Green
            )) {
            Text(text = "N")
        }
        Button(onClick = { onDirectionChange("E") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "E") Color.Black else Color.Green
            )) {
            Text(text = "E")
        }
        Button(onClick = { onDirectionChange("S") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "S") Color.Black else Color.Green
            )) {
            Text(text = "S")
        }
        Button(onClick = { onDirectionChange("W") },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (direction.value == "W") Color.Black else Color.Green
            )) {
            Text(text = "W")
        }
    }
}