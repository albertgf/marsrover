package com.albertgf.features.generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.RotateRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.coreview.compose.BtnIconCircle
import com.albertgf.coreview.compose.DirectionsInput
import com.albertgf.coreview.compose.InputField
import com.albertgf.features.R

@Composable
fun ScreenGenerator(
    terrain: State<Int>,
    onTerrainChange: (Int) -> Unit,
    posX: State<Int>,
    onXChange: (Int) -> Unit,
    posY: State<Int>,
    onYChange: (Int) -> Unit,
    instructions: State<String>,
    onAddInstruction: (String) -> Unit,
    onRemoveInstruction: () -> Unit,
    onDirections: State<String>,
    onPoint: (String) -> Unit,
    onSend: () -> Unit,
    onConceal: () -> Unit) {
    Column {
        Text(
            text = stringResource(id = R.string.label_terrain),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
                .fillMaxWidth()
        )
        InputField(
            value = terrain,
            onValueChange = { i -> onTerrainChange(i) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(id = R.string.label_x),
                    textAlign = TextAlign.Center
                )
                InputField(
                    value = posX,
                    onValueChange = { i -> onXChange(i) })
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    stringResource(id = R.string.label_y),
                    textAlign = TextAlign.Center
                )
                InputField(
                    value = posY,
                    onValueChange = { i -> onYChange(i) })
            }
        }
        Text(
            stringResource(id = R.string.label_instrucions),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .clip(shape = CircleShape)
                .background(Color.Black)
                .defaultMinSize(100.dp, 32.dp)
                .padding(8.dp)
        ) {
            Text(
                text = instructions.value,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                letterSpacing = 1.sp,
                maxLines = 1,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            BtnIconCircle(
                icon = Icons.Filled.RotateLeft,
                onSend = { c -> onAddInstruction(c) },
                value = "L"
            )
            BtnIconCircle(
                icon = Icons.Filled.ArrowUpward,
                onSend = { c -> onAddInstruction(c) },
                value = "M"
            )
            BtnIconCircle(
                icon = Icons.Filled.RotateRight,
                onSend = { c -> onAddInstruction(c) },
                value = "R"
            )
            BtnIconCircle(
                icon = Icons.Filled.Delete,
                onSend = { onRemoveInstruction() },
                value = ""
            )
        }

        DirectionsInput(
            direction = onDirections,
            onDirectionChange = { onPoint(it) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        Button(
            onClick = {
                onConceal()
                onSend()
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text(
                stringResource(id = R.string.btn_send),
                fontSize = 24.sp
            )

        }
    }
}