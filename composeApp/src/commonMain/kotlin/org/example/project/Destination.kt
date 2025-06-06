package org.example.project

import org.jetbrains.compose.resources.StringResource
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.tab_name_events
import pickupconnpassevents.composeapp.generated.resources.tab_name_favorites

enum class Destination(
    val route: String,
    val labelRes: StringResource
) {
    Events("events", Res.string.tab_name_events),
    Favorites("favorites", Res.string.tab_name_favorites);
}