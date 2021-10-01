package com.albertgf.features.generator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.common.domain.models.Resource
import com.albertgf.common.domain.models.ResourceState
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
                val scaffoldState =
                    rememberBackdropScaffoldState(initialValue = BackdropValue.Concealed)
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                BackdropScaffold(
                    scaffoldState = scaffoldState,
                    appBar = { AppBar() },
                    backLayerContent = { ScreenConnection(viewModel.result.collectAsState()) { scope.launch { scaffoldState.conceal() } } },
                    backLayerContentColor = Color.White,
                    backLayerBackgroundColor = Color.DarkGray,
                    frontLayerContent = {
                        ScreenGenerator { scope.launch { scaffoldState.reveal() } }
                    },
                    frontLayerContentColor = Color.LightGray,
                    frontLayerScrimColor = Color.LightGray,
                    frontLayerBackgroundColor = Color.LightGray,
                    gesturesEnabled = false
                ) {

                }
            }
        }
    }

    @Composable
    fun ScreenConnection(data: State<Resource<String>>, onReveal: () -> Unit) {
        Surface(
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Sending Data")
                Icon(
                    imageVector = imageSending(data.value.state),
                    contentDescription = "",
                    tint = imageSendingColor(data.value.state),
                    modifier =
                    Modifier
                        .padding(16.dp)
                        .height(64.dp)
                        .width(64.dp)
                )
                Text("Explore")
                Icon(
                    imageVector = imageExplore(data.value.state),
                    contentDescription = "",
                    tint = imageExploreColor(data.value.state),
                    modifier =
                    Modifier
                        .padding(16.dp)
                        .height(64.dp)
                        .width(64.dp)
                )
                Text("Ending Position")

                Box(
                    modifier = Modifier.clip(shape = CircleShape).background(Color.Black).padding(8.dp).defaultMinSize(100.dp, 32.dp)
                ) {
                    Text(
                        text = data.value.data ?: "",
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }


                Button(onClick = {
                    onReveal()
                }) {
                    Text(
                        "SEND MORE DATA",
                        color = MaterialTheme.colors.onPrimary
                    )

                }
            }
        }
    }

    private fun imageSending(state: ResourceState): ImageVector {
        return when (state) {
            ResourceState.IDLE -> Icons.Filled.WifiOff
            ResourceState.SETUP -> Icons.Filled.Wifi
            ResourceState.LOADING -> Icons.Filled.Wifi
            ResourceState.SUCCESS -> Icons.Filled.Wifi
        }
    }

    private fun imageSendingColor(state: ResourceState): Color {
        return when (state) {
            ResourceState.IDLE -> Color.White
            ResourceState.SETUP -> Color.White
            ResourceState.LOADING -> Color.Green
            ResourceState.SUCCESS -> Color.Green
        }
    }

    private fun imageExplore(state: ResourceState): ImageVector {
        return when (state) {
            ResourceState.IDLE -> Icons.Filled.Pause
            ResourceState.SETUP -> Icons.Filled.Pause
            ResourceState.LOADING -> Icons.Filled.Explore
            ResourceState.SUCCESS -> Icons.Filled.Explore
        }
    }

    private fun imageExploreColor(state: ResourceState): Color {
        return when (state) {
            ResourceState.IDLE -> Color.Red
            ResourceState.SETUP -> Color.Red
            ResourceState.LOADING -> Color.White
            ResourceState.SUCCESS -> Color.Green
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
                onConceal()
                viewModel.sendData()
            }) {
                Text("SEND DATA")

            }
        }
    }
}