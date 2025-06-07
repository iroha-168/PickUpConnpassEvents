package org.example.project.ui.event

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.EventItem
import androidx.compose.runtime.getValue

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
        onRefresh = { viewModel.refresh() }
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            item {
                Text(
                    text = "Koinのテスト： ${uiState.hoge}",
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }

            item {
                EventItem()
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }

            item {
                EventItem()
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }

            item {
                EventItem()
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}