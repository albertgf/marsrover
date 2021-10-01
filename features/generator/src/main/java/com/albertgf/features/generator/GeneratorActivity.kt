package com.albertgf.features.generator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.coreview.theme.MarsroverTheme
import com.albertgf.features.R
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GeneratorActivity : ComponentActivity() {
    private val viewModel: GeneratorViewModel by viewModel()

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarsroverTheme {
                val scaffoldState =
                    rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
                val scope = rememberCoroutineScope()

                BackdropScaffold(
                    scaffoldState = scaffoldState,
                    appBar = { AppBar() },
                    backLayerContent = {
                        ScreenResult(viewModel.result.collectAsState()) {
                            scope.launch { scaffoldState.conceal() }
                        }
                    },
                    backLayerContentColor = Color.White,
                    backLayerBackgroundColor = colorResource(id = R.color.gray_500),
                    frontLayerContent = {
                        ScreenGenerator(
                            terrain = viewModel.terrainSize.collectAsState(),
                            onTerrainChange = { viewModel.updateTerrainSize(it) },
                            posX = viewModel.x.collectAsState(),
                            onXChange = { viewModel.updateX(it) },
                            posY = viewModel.y.collectAsState(),
                            onYChange = { viewModel.updateY(it) },
                            instructions = viewModel.instructions.collectAsState(),
                            onAddInstruction = { viewModel.addInstruction(it) },
                            onRemoveInstruction = { viewModel.removeInstruction() },
                            onDirections = viewModel.direction.collectAsState(),
                            onPoint = { viewModel.point(it) },
                            onSend = { viewModel.sendData() },
                            onConceal = { scope.launch { scaffoldState.reveal() } }
                        )
                    },
                    frontLayerContentColor = Color.White,
                    frontLayerScrimColor = colorResource(id = R.color.gray_200),
                    frontLayerBackgroundColor = colorResource(id = R.color.gray_200),
                    gesturesEnabled = false
                ) {

                }
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
}