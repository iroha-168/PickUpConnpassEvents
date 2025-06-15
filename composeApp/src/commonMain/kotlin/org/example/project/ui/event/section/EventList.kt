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
import org.example.project.ui.event.component.EventItem
import org.example.project.ui.event.EventUiState

@Composable
fun EventList(
    modifier: Modifier = Modifier,
    onFavoriteButtonClick: (Long, Boolean) -> Unit,
    refresh: () -> Unit,
    uiState: EventUiState,
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
            )
            Spacer(modifier = Modifier.padding(bottom = 8.dp))

            if (index == events.lastIndex) {
                LaunchedEffect(Unit) {
                    refresh()
                }
            }

        }
    }
}