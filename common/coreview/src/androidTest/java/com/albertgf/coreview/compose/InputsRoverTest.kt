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

    @Test
    fun instructions_input_rotate_left_click_should_return_L_value() {
        var instruction= ""
        composeTestRule.setContent {
            InstructionsInput(onAdd = { instruction = it}, onRemove = {instruction = "R"} )
        }

        composeTestRule.onRoot().onChildAt(0).performClick()

        Assert.assertEquals(instruction, "L")
    }
    @Test
    fun instructions_input_move_click_should_return_M_value() {
        var instruction= ""
        composeTestRule.setContent {
            InstructionsInput(onAdd = { instruction = it}, onRemove = {instruction = "R"} )
        }

        composeTestRule.onRoot().onChildAt(1).performClick()

        Assert.assertEquals(instruction, "M")
    }

    @Test
    fun instructions_input_rotate_right_click_should_return_R_value() {
        var instruction= ""
        composeTestRule.setContent {
            InstructionsInput(onAdd = { instruction = it}, onRemove = {instruction = "R"} )
        }

        composeTestRule.onRoot().onChildAt(2).performClick()

        Assert.assertEquals(instruction, "R")
    }

    @Test
    fun instructions_input_remove_click_should_call_on_remove() {
        var instruction= ""
        composeTestRule.setContent {
            InstructionsInput(onAdd = { instruction = it}, onRemove = {instruction = "remove"} )
        }

        composeTestRule.onRoot().onChildAt(3).performClick()

        Assert.assertEquals(instruction, "remove")
    }
}