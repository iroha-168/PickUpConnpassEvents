package org.example.project.ui.event

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import org.example.project.ui.component.EventItem

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
        // todo: enumでイベントの有無のステータスを管理してもいいかも
        if (uiState.events == null) {
            // todo: sectionにempty用の画面を作成する
            Text(
                text = "No Events...",
                modifier = Modifier.fillMaxSize()
            )
        } else {
            // todo: sectionにファイル作って移動させる
            LazyColumn(
                modifier = Modifier
            ) {
//                item {
//                    Text(
//                        text = "Koin & Ktorのテスト： ${uiState.hoge}",
//                    )
//                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
//                }

                items(uiState.events!!) {
                    val event = it.event
                    EventItem(
                        id = event.id,
                        title = event.title,
                        startedAt = it.formattedDate,
                        place = event.place,
                        isFavorite = event.isFavorite,
                    )
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }
        }
    }
}