package org.example.project.ui.event.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import org.jetbrains.compose.resources.painterResource
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.favorite_filled
import pickupconnpassevents.composeapp.generated.resources.favorite_outline

@Composable
fun FavoriteButton(
    id: Long,
    isFavorite: Boolean,
    onClick: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var isFav by remember { mutableStateOf(isFavorite) }

    IconButton(
        onClick = {
            isFav = !isFav
            onClick(id, isFav)
            Logger.d { "HOGE: FavoriteButton id=$id, isFav=$isFav" }
        },
        modifier = modifier
    ) {
        val iconRes = if (isFav) {
            Res.drawable.favorite_filled
        } else {
            Logger.d { "HOGE: iconRes id=$id, isFav=$isFav" }
            Res.drawable.favorite_outline
        }

        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
        )
    }
}