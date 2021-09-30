package com.albertgf.common.domain

import com.albertgf.common.domain.repository.SendRepository
import com.albertgf.core.Rover
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Test

import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class SendRepositoryTest {
    private val mockRover = mockk<Rover>()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verify_rover_setup_and_call_explore() = runBlockingTest {
        val repository = SendRepository(mockRover, testDispatcher)

        every { mockRover.setup(any()) } returns Unit
        every { mockRover.explore() } returns "1 3 N"

        repository.send(DomainTestUtil.fakeRoverDomain())

        verify { mockRover.setup(DomainTestUtil.fakeRoverDataParserString()) }
        verify { mockRover.explore() }

        confirmVerified(mockRover)
    }
}