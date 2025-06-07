package org.example.project.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import org.example.project.data.db.EventDto
import org.example.project.data.entity.DateTime
import org.example.project.data.repository.EventRepository

class EventViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState

    init {
        viewModelScope.launch {
            val hoge = eventRepository.hoge()
            _uiState.update { it.copy(hoge = hoge) }

//            eventRepository.insertEvents(mockEvents)
        }
    }
}

data class EventUiState(
    val hoge: String? = null
)

// モック
// API keyをもらうまでの間はモックを使う
val mockEvents = listOf(
    EventDto(
        id = 1L,
        title = "Kotlin Multiplatform Meetup",
        url = "https://example.com/kmp-meetup",
        startedAt = DateTime(Instant.parse("2024-07-01T18:30:00Z")),
        place = "Tokyo, Japan",
        isFavorite = false
    ),
    EventDto(
        id = 2L,
        title = "Android Jetpack Compose Workshop",
        url = "https://example.com/compose-workshop",
        startedAt = DateTime(Instant.parse("2024-07-10T13:00:00Z")),
        place = "Osaka, Japan",
        isFavorite = true
    ),
    EventDto(
        id = 3L,
        title = "iOS SwiftUI勉強会",
        url = "https://example.com/swiftui",
        startedAt = null,
        place = null,
        isFavorite = false
    )
)