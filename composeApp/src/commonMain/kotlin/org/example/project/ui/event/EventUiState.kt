package org.example.project.ui.event

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import org.example.project.data.db.EventDto

data class EventUiState(
    val hoge: String? = null,
    val isRefreshing: Boolean = false,
    val events: List<EventItemUiState>? = null,
)

data class EventItemUiState(
    val event: EventDto,
) {
    val formattedDate = event.startedAt?.date?.formatJapanese()

    fun LocalDateTime.formatJapanese(): String {
        val dayOfWeek = when (this.dayOfWeek) {
            DayOfWeek.MONDAY -> "(月)"
            DayOfWeek.TUESDAY -> "(火)"
            DayOfWeek.WEDNESDAY -> "(水)"
            DayOfWeek.THURSDAY -> "(木)"
            DayOfWeek.FRIDAY -> "(金)"
            DayOfWeek.SATURDAY -> "(土)"
            DayOfWeek.SUNDAY -> "(日)"
            else -> {}
        }
        val year = "${this.year}" + "年"
        val monthNumber = "${this.monthNumber}" + "月"
        val dayOfMonth = "${this.dayOfMonth}" + "日"

        val hour = this.hour.toString().padStart(2, '0')
        val minute = this.minute.toString().padStart(2, '0')

        return "$year$monthNumber$dayOfMonth$dayOfWeek $hour:$minute"
    }
}