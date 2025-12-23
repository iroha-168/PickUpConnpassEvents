package org.example.project.ui.event

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.data.repository.EventRepository
import org.example.project.data.repository.Result.Failure
import org.example.project.data.repository.Result.Success

class EventViewModel(
    private val eventRepository: EventRepository
): ViewModel() {
    var start = 1
    val page = 20

    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState

    init {
        onFilterChange(EventFilter.UpcomingEvents)
    }

    fun refresh(filter: EventFilter) {
        if (_uiState.value.isRefreshing) return
        viewModelScope.launch {
            setRefreshingState(true)
            when (val result = eventRepository.refresh(start, page, filter.toQuery())) {
                is Success -> {
                    start += page
                }
                is Failure -> {
                    val errorMessage = result.error.message ?: "An unexpected error occurred"
                    _uiState.update {
                        it.copy(uiEvents = it.uiEvents + EventUiEvent.Failure(message = errorMessage))
                    }
                }
            }
            setRefreshingState(false)
        }
    }

    fun onFilterChange(selectedFilter: EventFilter) {
        _uiState.update { it.copy(filter = selectedFilter) }
        refresh(filter = selectedFilter)
        val selectedFlow = when(selectedFilter) {
            is EventFilter.Online -> eventRepository.onlineEvents
            is EventFilter.Newest -> eventRepository.newestEvents
            is EventFilter.UpcomingEvents -> eventRepository.upcomingEvents
        }
        viewModelScope.launch {
            selectedFlow.collect { events ->
                _uiState.update {
                    it.copy(
                        events = events.map { event ->
                            EventItemUiState(event)
                        }
                    )
                }
            }
        }
        start = 1
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

    fun onEventClick(url: String) {
        _uiState.update { it.copy(uiEvents = it.uiEvents + EventUiEvent.OpenUrl(url)) }
    }

    fun consumeEvents(event: EventUiEvent) {
        val newEvents = _uiState.value.uiEvents.filterNot { it == event }
        _uiState.update { it.copy(uiEvents = newEvents) }
    }

    private fun setRefreshingState(state: Boolean) {
        _uiState.update { it.copy(isRefreshing = state) }
    }
}