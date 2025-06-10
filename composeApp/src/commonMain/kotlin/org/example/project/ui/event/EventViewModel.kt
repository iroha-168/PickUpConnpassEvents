package org.example.project.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.repository.EventRepository

class EventViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(EventUiState()).also { uiState ->
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
            eventRepository.refresh()
            setRefreshingState(false)
        }
    }

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

    private fun setRefreshingState(state: Boolean) {
        _uiState.update { it.copy(isRefreshing = state) }
    }
}