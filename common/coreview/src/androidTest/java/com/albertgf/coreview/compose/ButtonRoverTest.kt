package com.albertgf.coreview.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.albertgf.coreview.R
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ButtonRoverTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun primary_button_click_should_call_lambda() {
        var i = 0
        composeTestRule.setContent {
            PrimaryButton(textId = R.string.add_value, onClick = { i++ })
        }

        composeTestRule.onNodeWithText("add_value").performClick()

        assertEquals(i, 1)
    }

    @Test
    fun btn_circle_icon_should_call_lambda_and_pass_constructor_value() {
        var i = 0
        val value = 1
        composeTestRule.setContent {
            BtnIconCircle(icon = Icons.Default.ArrowForward, onSend = { i = it}, value = 1 )
        }

        composeTestRule.onNodeWithContentDescription("add_value").performClick()

        assertEquals(i , value)
    }
}