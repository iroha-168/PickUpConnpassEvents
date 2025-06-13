package org.example.project.ui.event

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import org.example.project.ui.event.section.EmptyEvents
import org.example.project.ui.event.section.EventList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(
    viewModel: EventViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    PullToRefreshBox(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp),
        isRefreshing = uiState.isRefreshing,
        onRefresh = {
            viewModel.refresh()
        }
    ) {
        if (uiState.events.isNullOrEmpty()) {
            EmptyEvents()
        } else {
            EventList(
                uiState = uiState,
                onFavoriteButtonClick = viewModel::onFavoriteButtonClick,
                refresh = viewModel::refresh,
            )
        }
    }
}