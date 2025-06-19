package org.example.project.ui.event

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import org.example.project.data.db.EventDto

data class EventUiState(
    val hoge: String? = null,
    val isRefreshing: Boolean = false,
    val events: List<EventItemUiState>? = null,
    val uiEvents: List<EventUiEvent> = emptyList(),
)

data class EventItemUiState(
    val event: EventDto,
) {
    val formattedDate = event.startedAt?.formatJapanese()

    fun LocalDateTime.formatJapanese(): String {
        val dayOfWeek = when (this.dayOfWeek) {
            DayOfWeek.MONDAY -> "(MON)"
            DayOfWeek.TUESDAY -> "(TUE)"
            DayOfWeek.WEDNESDAY -> "(WED)"
            DayOfWeek.THURSDAY -> "(THU)"
            DayOfWeek.FRIDAY -> "(FRI)"
            DayOfWeek.SATURDAY -> "(SAT)"
            DayOfWeek.SUNDAY -> "(SUN)"
            else -> {}
        }
        val year = "${this.year}"
        val monthNumber = "/${this.monthNumber}"
        val dayOfMonth = "/${this.dayOfMonth}"

        val hour = this.hour.toString()
        val minute = if (this.minute == 0) "00" else this.minute.toString()

        return "$year$monthNumber$dayOfMonth$dayOfWeek $hour:$minute"
    }
}