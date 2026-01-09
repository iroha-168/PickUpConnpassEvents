package org.example.project.ui.event.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.EventInfoArea
import org.jetbrains.compose.ui.tooling.preview.Preview
import theming.AppTheme

@Composable
fun EventItem(
    id: Long,
    isFavorite: Boolean,
    title: String,
    startedAt: String?,
    place: String?,
    onFavoriteButtonClick: (Long, Boolean) -> Unit,
    onEventCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onEventCardClick()
            }
            .background(color = AppTheme.colors.primary)
            .padding(16.dp)
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

@Composable
@Preview
private fun EventItemPreview() {
    AppTheme {
        EventItem(
            id = 1,
            isFavorite = true,
            title = "イベントタイトル",
            startedAt = "2026/01/01",
            place = "オンライン",
            onFavoriteButtonClick = { _, _ -> },
            onEventCardClick = {},
        )
    }
}