package org.example.project.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.event_item_location
import pickupconnpassevents.composeapp.generated.resources.event_item_start
import pickupconnpassevents.composeapp.generated.resources.favorite_filled
import pickupconnpassevents.composeapp.generated.resources.favorite_outline

@Composable
fun EventItem(
    id: Long,
    isFavorite: Boolean,
    title: String,
    startedAt: String?,
    place: String?,
    onFavoriteButtonClick: (Long) -> Unit = {},
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
            startedAt = startedAt,
            title = title,
            place = place,
        )

        Spacer(modifier.weight(1f))

        FavoriteButton(
            isFavorite = isFavorite,
            onClick = { onFavoriteButtonClick(id.toLong()) },
        )
    }
}

@Composable
private fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // todo: 渡されてきたisFavoriteを入れる
    var isTapped by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            isTapped = !isTapped
            onClick()
        },
        modifier = modifier
    ) {
        val iconRes = if (isTapped) {
            Res.drawable.favorite_filled
        } else {
            Res.drawable.favorite_outline

        }

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
        )
    }
}

@Composable
private fun EventInfoArea(
    startedAt: String?,
    title: String,
    place: String?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        EventTitle(eventTitle = title)
        EventLocation(location = place)
        EventStartDate(startedAt = startedAt)
    }
}

@Composable
private fun EventTitle(
    eventTitle: String,
    modifier: Modifier = Modifier,
) {
    // todo: 長すぎるタイトルを省略する
    Text(
        modifier = modifier,
        text = eventTitle,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun EventLocation(
    location: String?,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(Res.string.event_item_location),
        )
        Text(
            modifier = modifier,
            text = location ?: "未定", //todo: 文字列リソースにする
            fontSize = 16.sp,
        )
    }
}

@Composable
private fun EventStartDate(
    startedAt: String?,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(Res.string.event_item_start),
        )
        Text(
            text = startedAt ?: "未定",
            fontSize = 16.sp,
        )
    }
}
