package org.example.project.ui.favorite.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.EventInfoArea

@Composable
fun FavoriteItem(
    id: Long,
    isFavorite: Boolean,
    title: String,
    startedAt: String?,
    place: String?,
    onFavoriteButtonClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.run {
            fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.LightGray)
                .padding(16.dp)
        }
    ) {
        EventInfoArea(
            modifier = Modifier.weight(weight = 6f),
            startedAt = startedAt,
            title = title,
            place = place,
        )

        Spacer(modifier.weight(1f))

        FavoriteButton(
            id = id,
            isFavorite = isFavorite,
            onClick = onFavoriteButtonClick,
            modifier = Modifier.size(32.dp).weight(1f)
        )
    }
}