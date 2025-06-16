package org.example.project.ui.event

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class EventViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    
    @Test
    fun updateStartOnRefreshSuccess() = testScope.runTest {
        // given
        val fakeEventRepository = FakeEventRepository()
        val viewModel = EventViewModel(fakeEventRepository)
        val start = viewModel.start

        // when
        viewModel.refresh()

        // then
        val expected = viewModel.start
        assertEquals(expected, start+20)
    }
}

