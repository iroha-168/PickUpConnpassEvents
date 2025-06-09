package org.example.project.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.event_item_location
import pickupconnpassevents.composeapp.generated.resources.event_item_start
import pickupconnpassevents.composeapp.generated.resources.event_item_tbd

@Composable
fun EventInfoArea(
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
    Text(
        modifier = modifier,
        text = eventTitle,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = Ellipsis
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
            text = location ?: stringResource(Res.string.event_item_tbd),
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
            text = startedAt ?: stringResource(Res.string.event_item_tbd),
            fontSize = 16.sp,
        )
    }
}