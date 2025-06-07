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
    private val _uiState = MutableStateFlow(EventUiState())
    val uiState: StateFlow<EventUiState> = _uiState

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            val hoge = eventRepository.hoge()
            _uiState.update { it.copy(hoge = hoge, isRefreshing = false) }
        }
    }
}

data class EventUiState(
    val hoge: String? = null,
    val isRefreshing: Boolean = false,
)