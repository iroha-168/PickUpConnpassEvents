package org.example.project.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.repository.EventRepository

class EventViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    private var start = 1
    private val page = 20

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
            when (val result = eventRepository.refresh(start, page)) {
                is EventRepository.Result.Success -> {
                    start += page
                }
                is EventRepository.Result.Failure -> {
                    val errorMessage = result.error.message ?: "An unexpected error occurred"
                    _uiState.update {
                        it.copy(uiEvents = it.uiEvents + EventUiEvent.Failure(message = errorMessage))
                    }
                }
            }
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

    fun consumeEvents(event: EventUiEvent) {
        val newEvents = _uiState.value.uiEvents.filterNot { it == event }
        _uiState.update { it.copy(uiEvents = newEvents) }
    }

    private fun setRefreshingState(state: Boolean) {
        _uiState.update { it.copy(isRefreshing = state) }
    }
}