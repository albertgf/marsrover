package com.albertgf.coreview.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.albertgf.coreview.R

@Composable
fun <T>BtnIconCircle(icon: ImageVector, onSend: (T) -> Unit, value: T) {
    Button(onClick = { onSend(value) },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = CircleShape,
        modifier = Modifier.width(64.dp).height(64.dp).padding(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.add_value),
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxSize()
        )
    }
}