package com.albertgf.coreview.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.albertgf.coreview.R
import com.albertgf.coreview.theme.MarsroverTheme

@Composable
fun <T>BtnIconCircle(icon: ImageVector, onSend: (T) -> Unit, value: T) {
    Button(onClick = { onSend(value) },
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = CircleShape,
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .padding(0.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.add_value),
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun PrimaryButton( textId:  Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = textId),
            fontSize = 24.sp
        )
    }
}