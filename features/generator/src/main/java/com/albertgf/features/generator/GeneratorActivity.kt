package com.albertgf.features.generator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.common.domain.models.Resource
import com.albertgf.common.domain.models.ResourceState
import com.albertgf.coreview.compose.BtnIconCircle
import com.albertgf.coreview.compose.DirectionsInput
import com.albertgf.coreview.compose.InputField
import com.albertgf.coreview.compose.PrimaryButton
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
                // A surface container using the 'background' color from the theme
                BackdropScaffold(
                    scaffoldState = scaffoldState,
                    appBar = { AppBar() },
                    backLayerContent = { ScreenConnection(viewModel.result.collectAsState()) { scope.launch { scaffoldState.conceal() } } },
                    backLayerContentColor = Color.White,
                    backLayerBackgroundColor = colorResource(id = R.color.gray_500),
                    frontLayerContent = {
                        ScreenGenerator { scope.launch { scaffoldState.reveal() } }
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
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .background(Color.Black)
                        .padding(8.dp)
                        .defaultMinSize(100.dp, 32.dp)
                ) {
                    Text(
                        text = data.value.data ?: "",
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center,
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                PrimaryButton(
                    textId = R.string.retry,
                    onClick = { onReveal() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 32.dp)
                )
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
    fun ScreenGenerator(onConceal: () -> Unit) {
        Column() {
            Text(
                "Terrain dimensions",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
                    .fillMaxWidth()
            )
            InputField(
                value = viewModel.terrainSize.collectAsState(),
                onValueChange = { i -> viewModel.updateTerrainSize(i) },
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
                        "Initial X",
                        textAlign = TextAlign.Center
                    )
                    InputField(
                        value = viewModel.x.collectAsState(),
                        onValueChange = { i -> viewModel.updateX(i) })
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Initial Y",
                        textAlign = TextAlign.Center
                    )
                    InputField(
                        value = viewModel.y.collectAsState(),
                        onValueChange = { i -> viewModel.updateY(i) })
                }
            }
            Text(
                "Instructions Generator",
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
                    text = viewModel.instructions.collectAsState().value,
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
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "L"
                )
                BtnIconCircle(
                    icon = Icons.Filled.ArrowUpward,
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "M"
                )
                BtnIconCircle(
                    icon = Icons.Filled.RotateRight,
                    onSend = { c -> viewModel.addInstruction(c) },
                    value = "R"
                )
                BtnIconCircle(
                    icon = Icons.Filled.Delete,
                    onSend = { c -> viewModel.removeInstruction() },
                    value = ""
                )
            }



            DirectionsInput(
                direction = viewModel.direction.collectAsState(),
                onDirectionChange = { viewModel.point(it) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
            )

            Button(
                onClick = {
                    onConceal()
                    viewModel.sendData()
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 32.dp)
            ) {
                Text(
                    "SEND DATA",
                    fontSize = 24.sp
                )

            }
        }
    }
}