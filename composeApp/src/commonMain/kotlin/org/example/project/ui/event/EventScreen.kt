package org.example.project.ui.event

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.datetime.LocalDateTime
import org.example.project.data.db.EventDto
import org.example.project.data.entity.Event
import org.example.project.ui.event.section.ErrorDialog
import org.example.project.ui.event.section.EventList
import org.example.project.ui.event.section.NoEvents
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.filter_chip_newest
import pickupconnpassevents.composeapp.generated.resources.filter_chip_online
import pickupconnpassevents.composeapp.generated.resources.filter_chip_upcoming
import theming.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    viewModel: EventViewModel,
    modifier: Modifier = Modifier,
    openUrl: (String) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    uiState.uiEvents.firstOrNull()?.let { event ->
        when (event) {
            is EventUiEvent.Failure -> {
                ErrorDialog(
                    message = event.message,
                    onDismissRequest = {
                        viewModel.consumeEvents(event)
                    }
                )
            }

            is EventUiEvent.OpenUrl -> {
                openUrl(event.url)
                viewModel.consumeEvents(event)
            }
        }
    }

    EventScreen(
        modifier = modifier,
        uiState = uiState,
        page = viewModel.page,
        refresh = viewModel::refresh,
        onFilterChange = viewModel::onFilterChange,
        onEventClick = viewModel::onEventClick,
        onFavoriteButtonClick = viewModel::onFavoriteButtonClick,
    )
}

@Composable
private fun EventScreen(
    modifier: Modifier = Modifier,
    uiState: EventUiState,
    page: Int,
    refresh: (EventFilter) -> Unit,
    onFilterChange: (EventFilter) -> Unit,
    onEventClick: (String) -> Unit,
    onFavoriteButtonClick: (Long, Boolean) -> Unit,
) {
    var selectedFilter by remember { mutableStateOf(uiState.filter) }

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        isRefreshing = uiState.isRefreshing,
        onRefresh = {
            refresh(selectedFilter)
        }
    ) {
        if(uiState.events == null) {
            NoEvents()
        } else {
            Column {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    FilterChip(
                        selected = selectedFilter == EventFilter.Online,
                        onClick = {
                            selectedFilter = EventFilter.Online
                            onFilterChange(selectedFilter)
                        },
                        label = { Text(text = stringResource(Res.string.filter_chip_online)) },
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    FilterChip(
                        selected = selectedFilter == EventFilter.Newest,
                        onClick = {
                            selectedFilter = EventFilter.Newest
                            onFilterChange(selectedFilter)
                        },
                        label = { Text(text = stringResource(Res.string.filter_chip_newest)) },
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    FilterChip(
                        selected = selectedFilter == EventFilter.UpcomingEvents,
                        onClick = {
                            selectedFilter = EventFilter.UpcomingEvents
                            onFilterChange(selectedFilter)
                        },
                        label = { Text(text = stringResource(Res.string.filter_chip_upcoming)) },
                    )
                }
                EventList(
                    uiState = uiState,
                    lastIndex = page - 1,
                    refresh = {
                        refresh(selectedFilter)
                    },
                    onFavoriteButtonClick = onFavoriteButtonClick,
                    onEventCardClick = onEventClick,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EventScreenPreview(
    @PreviewParameter(EventUiStatePreviewParameterProvider::class) uiState: EventUiState
) {
    AppTheme {
        EventScreen(
            uiState = uiState,
            page = 20,
            refresh = {},
            onFilterChange = {},
            onEventClick = {},
            onFavoriteButtonClick = { _, _ -> },
            modifier = Modifier
        )
    }
}

private class EventUiStatePreviewParameterProvider : PreviewParameterProvider<EventUiState> {
    override val values: Sequence<EventUiState> = sequenceOf(
        EventUiState(
            isRefreshing = false,
            events = null,
        ),
        EventUiState(
            isRefreshing = true,
            events = createEvents(),
            filter = EventFilter.Online
        ),
        EventUiState(
            isRefreshing = false,
            events = createEvents(),
        ),
    )
}

private fun createEvents(): List<EventItemUiState> {
    val eventIsFavoriteTrue = EventDto(
        id = 1,
        title = "イベントタイトル",
        url = "https://mobiledev-japan.connpass.com/event/376753/",
        startedAt = LocalDateTime(2026, 1, 1, 0, 0),
        place = "オンライン",
        isFavorite = true,
    )

    val eventIsFavoriteFalse = EventDto(
        id = 1,
        title = "イベントタイトル",
        url = "https://mobiledev-japan.connpass.com/event/376753/",
        startedAt = LocalDateTime(2026, 1, 1, 0, 0),
        place = "オンライン",
        isFavorite = false,
    )

    val eventItemUiStateTrue = EventItemUiState(
        event = eventIsFavoriteTrue,
    )

    val eventItemUiStateFalse = EventItemUiState(
        event = eventIsFavoriteFalse,
    )

    return listOf(
        eventItemUiStateTrue,
        eventItemUiStateFalse,
        eventItemUiStateTrue,
        eventItemUiStateTrue,
        eventItemUiStateFalse,
    )
}
