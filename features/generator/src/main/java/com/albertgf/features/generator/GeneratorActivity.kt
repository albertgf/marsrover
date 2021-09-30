package com.albertgf.features.generator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.lifecycle.viewmodel.compose.viewModel
import com.albertgf.coreview.compose.BtnIconCircle
import com.albertgf.coreview.compose.DirectionsInput
import com.albertgf.coreview.compose.InputField
import com.albertgf.coreview.theme.MarsroverTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneratorActivity : ComponentActivity() {
    private val viewModel: GeneratorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarsroverTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()) {
                    Column() {
                        InputField(
                            value = viewModel.terrainSize.collectAsState(),
                            onValueChange = { i -> viewModel.updateTerrainSize(i) })
                        Row() {
                            InputField(
                                value = viewModel.x.collectAsState(),
                                onValueChange = { i -> viewModel.updateX(i) })
                            InputField(
                                value = viewModel.y.collectAsState(),
                                onValueChange = { i -> viewModel.updateY(i) })
                        }

                        Row {
                            BtnIconCircle(icon = Icons.Filled.ArrowBack, onSend = { c -> viewModel.addInstruction(c)} , value = "L")
                            BtnIconCircle(icon = Icons.Filled.ArrowUpward, onSend = { c -> viewModel.addInstruction(c)} , value = "M")
                            BtnIconCircle(icon = Icons.Filled.ArrowForward, onSend = { c -> viewModel.addInstruction(c)} , value = "R")
                            BtnIconCircle(icon = Icons.Filled.Delete, onSend = { c -> viewModel.removeInstruction()} , value = "")
                        }

                        Text(
                            text = viewModel.instructions.collectAsState().value
                        )

                        DirectionsInput(direction = viewModel.direction.collectAsState(), onDirectionChange = { viewModel.point(it) })

                        Button(onClick = { viewModel.sendData()}) {
                            Text("SEND DATA")

                        }
                    }
                }
            }
        }
    }
}