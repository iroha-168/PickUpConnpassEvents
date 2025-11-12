package org.example.project.ui.event

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.event.section.ErrorDialog
import org.example.project.ui.event.section.EventList
import org.example.project.ui.event.section.NoEvents

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    viewModel: EventViewModel,
    modifier: Modifier = Modifier,
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
        }
    }

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        isRefreshing = uiState.isRefreshing,
        onRefresh = {
            viewModel.refresh()
        }
    ) {
        if(uiState.events == null) {
            NoEvents()
        } else {
            EventList(
                uiState = uiState,
                lastIndex = viewModel.page - 1,
                refresh = viewModel::refresh,
                onFavoriteButtonClick = viewModel::onFavoriteButtonClick,
                onEventCardClick = viewModel::onEventClick,
            )
        }
    }
}
