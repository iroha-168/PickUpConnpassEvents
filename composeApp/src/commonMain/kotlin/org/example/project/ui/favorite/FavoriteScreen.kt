package org.example.project.ui.favorite

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import org.example.project.ui.favorite.component.FavoriteItem
import org.example.project.ui.favorite.section.EmptyFavorites

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        if(uiState.events.isNullOrEmpty()) {
            item {
                EmptyFavorites()
            }
        } else {
            items(uiState.events!!) {
                val event = it.event
                FavoriteItem(
                    id = event.id,
                    title = event.title,
                    startedAt = it.formattedDate,
                    place = event.place,
                    isFavorite = event.isFavorite,
                    onFavoriteButtonClick = viewModel::onFavoriteButtonClick,
                )
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }
        }
    }
}
