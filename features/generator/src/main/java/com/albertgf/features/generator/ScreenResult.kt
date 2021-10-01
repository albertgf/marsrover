package com.albertgf.features.generator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.common.domain.models.Resource
import com.albertgf.common.domain.models.ResourceState
import com.albertgf.coreview.compose.PrimaryButton
import com.albertgf.features.R

@Composable
fun ScreenResult(data: State<Resource<String>>, onReveal: () -> Unit) {
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
                contentDescription = stringResource(id = R.string.cd_sending_data),
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
                contentDescription = stringResource(id = R.string.cd_explore),
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