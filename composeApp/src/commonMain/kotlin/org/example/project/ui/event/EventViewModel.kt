package org.example.project.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime
import org.example.project.data.db.EventDto
import org.example.project.data.entity.DateTime
import org.example.project.data.repository.EventRepository
import kotlin.time.ExperimentalTime

class EventViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    val _uiState = MutableStateFlow(EventUiState()).also { uiState ->
        viewModelScope.launch {
            eventRepository.events.collect { events ->
                val itemUiState = events.map { event ->
                    EventItemUiState(event)
                }
                uiState.update { it.copy(events = itemUiState) }
            }
        }
    }
    val uiState: StateFlow<EventUiState> = _uiState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            setRefreshingState(true)
//            val hoge = eventRepository.hoge()
//            _uiState.update { it.copy(hoge = hoge) }
            eventRepository.upsertEvents(mockEvents)
            setRefreshingState(false)
        }
    }

    private fun setRefreshingState(state: Boolean) {
        _uiState.update { it.copy(isRefreshing = state) }
    }
}

// モック
// API keyをもらうまでの間はモックを使う
@OptIn(ExperimentalTime::class)
val mockEvents = listOf(
    EventDto(
        id = 1L,
        title = "Android Kotlin Multiplatform Meetup",
        startedAt = DateTime(LocalDateTime.parse("2025-06-08T14:30:00")),
        place = "Tokyo",
        isFavorite = false
    ),
    EventDto(
        id = 2L,
        title = "Android Jetpack Compose Workshop",
        startedAt = DateTime(LocalDateTime.parse("2025-06-09T14:30:00")),
        place = "Osaka",
        isFavorite = true
    ),
    EventDto(
        id = 3L,
        title = "iOS SwiftUI勉強会",
        startedAt = null,
        place = null,
        isFavorite = false
    ),
    EventDto(
        id = 3L,
        title = "iOSDC 2025 After Party",
        startedAt = DateTime(LocalDateTime.parse("2025-10-01T14:30:00")),
        place = null,
        isFavorite = false
    )
)