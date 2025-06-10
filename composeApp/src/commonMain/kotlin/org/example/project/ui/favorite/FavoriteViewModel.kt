package org.example.project.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.repository.EventRepository
import org.example.project.ui.event.EventItemUiState
import org.example.project.ui.event.EventUiState

class FavoriteViewModel(
    private val eventRepository: EventRepository,
): ViewModel() {
    private val _uiState = MutableStateFlow(EventUiState()).also { uiState ->
        viewModelScope.launch {
            eventRepository.favoriteEvents.collect { events ->
                val itemUiState = events.map { event ->
                    EventItemUiState(event)
                }
                uiState.update { it.copy(events = itemUiState) }
            }
        }
    }
    val uiState: StateFlow<EventUiState> = _uiState

    fun onFavoriteButtonClick(
        id: Long,
        isFavorite: Boolean,
    ) {
        viewModelScope.launch {
            eventRepository.updateFavorite(
                id = id,
                isFavorite = isFavorite,
            )
        }
    }
}