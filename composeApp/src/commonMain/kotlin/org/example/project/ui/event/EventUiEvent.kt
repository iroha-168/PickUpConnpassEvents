package org.example.project.ui.event

sealed interface EventUiEvent {
    data class OpenUrl(
        val url: String,
    ) : EventUiEvent

    data class Failure(
        val message: String,
    ) : EventUiEvent
}