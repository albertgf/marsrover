package com.albertgf.coreview.compose

import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class InputsRoverTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun input_field_remove_button_should_return_negative_value() {
        var value: State<Int>
        var i = 1
        composeTestRule.setContent {
            value = MutableStateFlow(0).asStateFlow().collectAsState()
           InputField(value = value, onValueChange = { i = it})
        }

        composeTestRule.onRoot().onChildAt(0).performClick()

        Assert.assertEquals(i, -1)
    }

    @Test
    fun input_field_positive_button_should_return_positive_value() {
        var value: State<Int>
        var i = 1
        composeTestRule.setContent {
            value = MutableStateFlow(0).asStateFlow().collectAsState()
            InputField(value = value, onValueChange = { i = it})
        }

        composeTestRule.onRoot().onChildAt(2).performClick()

        Assert.assertEquals(i, 1)
    }

    @Test
    fun direction_input_north_click_should_return_direction_N_value() {
        var value: State<String>
        var direction= ""
        composeTestRule.setContent {
            value = MutableStateFlow("").asStateFlow().collectAsState()
            DirectionsInput(direction = value, onDirectionChange = { direction = it})
        }

        composeTestRule.onRoot().onChildAt(0).performClick()

        Assert.assertEquals(direction, "N")
    }

    @Test
    fun direction_input_east_click_should_return_direction_E_value() {
        var value: State<String>
        var direction= ""
        composeTestRule.setContent {
            value = MutableStateFlow("").asStateFlow().collectAsState()
            DirectionsInput(direction = value, onDirectionChange = { direction = it})
        }

        composeTestRule.onRoot().onChildAt(1).performClick()

        Assert.assertEquals(direction, "E")
    }

    @Test
    fun direction_input_south_click_should_return_direction_S_value() {
        var value: State<String>
        var direction= ""
        composeTestRule.setContent {
            value = MutableStateFlow("").asStateFlow().collectAsState()
            DirectionsInput(direction = value, onDirectionChange = { direction = it})
        }

        composeTestRule.onRoot().onChildAt(2).performClick()

        Assert.assertEquals(direction, "S")
    }

    @Test
    fun direction_input_west_click_should_return_direction_W_value() {
        var value: State<String>
        var direction= ""
        composeTestRule.setContent {
            value = MutableStateFlow("").asStateFlow().collectAsState()
            DirectionsInput(direction = value, onDirectionChange = { direction = it})
        }

        composeTestRule.onRoot().onChildAt(3).performClick()

        Assert.assertEquals(direction, "W")
    }
}