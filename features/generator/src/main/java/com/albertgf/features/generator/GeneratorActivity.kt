package com.albertgf.features.generator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.albertgf.coreview.compose.BtnIconCircle
import com.albertgf.coreview.compose.DirectionsInput
import com.albertgf.coreview.compose.InputField
import com.albertgf.coreview.theme.MarsroverTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneratorActivity : ComponentActivity() {
    private val viewModel: GeneratorViewModel by viewModel()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarsroverTheme {
                val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                BackdropScaffold(
                    scaffoldState = scaffoldState,
                    appBar = { AppBar()  },
                    backLayerContent = { ScreenConnection { scope.launch { scaffoldState.conceal() }} },
                    frontLayerContent = {
                        ScreenGenerator { scope.launch { scaffoldState.reveal() } }
                    },
                    gesturesEnabled = false
                ) {

                }
            }
        }
    }

    @Composable
    fun ScreenConnection(onReveal: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center) {
            Button(onClick = {
                onReveal()
            }) {
                Text("SEND CONCEAL")

            }
        }

    }

    @Composable
    fun AppBar() {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Mars Rover",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun ScreenConnection2(onReveal: () -> Unit) {
        Button(onClick = {
            onReveal()
        }) {
            Text("SEND REVEAL")

        }
    }


    @Composable
    fun ScreenGenerator(onConceal: () -> Unit) {
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
                BtnIconCircle(
                    icon = Icons.Filled.ArrowBack,
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "L"
                )
                BtnIconCircle(
                    icon = Icons.Filled.ArrowUpward,
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "M"
                )
                BtnIconCircle(
                    icon = Icons.Filled.ArrowForward,
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "R"
                )
                BtnIconCircle(
                    icon = Icons.Filled.Delete,
                    onSend = { c -> viewModel.removeInstruction() },
                    value = ""
                )
            }

            Text(
                text = viewModel.instructions.collectAsState().value
            )

            DirectionsInput(
                direction = viewModel.direction.collectAsState(),
                onDirectionChange = { viewModel.point(it) })

            Button(onClick = {
                viewModel.sendData()
                onConceal()
            }) {
                Text("SEND DATA")

            }
        }
    }
}