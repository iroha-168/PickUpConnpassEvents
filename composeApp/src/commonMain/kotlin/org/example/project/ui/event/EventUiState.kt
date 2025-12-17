package org.example.project.ui.event

import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDateTime
import org.example.project.data.api.ConnpassApiClient
import org.example.project.data.db.EventDto

data class EventUiState(
    val isRefreshing: Boolean = false,
    val filter: EventFilter = EventFilter.UpcomingEvents,
    val events: List<EventItemUiState>? = null,
    val uiEvents: List<EventUiEvent> = emptyList(),
)
sealed class EventFilter {
    abstract fun toQuery(): ConnpassApiClient.ConnpassQueryFilter

    data object Online: EventFilter() {
        override fun toQuery(): ConnpassApiClient.ConnpassQueryFilter {
            return ConnpassApiClient.ConnpassQueryFilter.Prefecture("online")
        }
    }
    data object Newest: EventFilter() {
        override fun toQuery(): ConnpassApiClient.ConnpassQueryFilter {
            return ConnpassApiClient.ConnpassQueryFilter.Order.Newest
        }
    }

    data object UpcomingEvents: EventFilter() {
        override fun toQuery(): ConnpassApiClient.ConnpassQueryFilter {
            return ConnpassApiClient.ConnpassQueryFilter.Order.UpcomingEvents
        }
    }
}

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