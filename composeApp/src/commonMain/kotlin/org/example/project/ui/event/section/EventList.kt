package org.example.project.ui.event.section

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime
import org.example.project.data.db.EventDto
import org.example.project.ui.event.EventItemUiState
import org.example.project.ui.event.EventUiState
import org.example.project.ui.event.component.EventItem
import org.jetbrains.compose.ui.tooling.preview.Preview
import theming.AppTheme

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    uiState: EventUiState,
    lastIndex: Int,
    refresh: () -> Unit,
    onEventCardClick: (String) -> Unit,
    onFavoriteButtonClick: (Long, Boolean) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        val events = uiState.events!!
        itemsIndexed (events) { index, item ->
            val event = item.event
            EventItem(
                id = event.id,
                title = event.title,
                startedAt = item.formattedDate,
                place = event.place,
                isFavorite = event.isFavorite,
                onFavoriteButtonClick = onFavoriteButtonClick,
                onEventCardClick = {
                    onEventCardClick(event.url)
                },
            )
            Spacer(modifier = Modifier.padding(bottom = 8.dp))
            if (index == lastIndex) {
                LaunchedEffect(Unit) {
                    refresh()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun EventListPreview() {
    val event = EventDto(
        id = 1,
        title = "イベントタイトル",
        url = "https://mobiledev-japan.connpass.com/event/376753/",
        startedAt = LocalDateTime(2026, 1, 1, 0, 0),
        place = "オンライン",
        isFavorite = true,
    )

    val eventItemUiState = EventItemUiState(
        event = event,
    )

    val eventUiState = EventUiState(
        events = List(5) { eventItemUiState },
        isRefreshing = false,
    )

    AppTheme {
        EventList(
            uiState = eventUiState,
            lastIndex = 0,
            refresh = {},
            onEventCardClick = {},
            onFavoriteButtonClick = { _, _ -> },
        )
    }
}