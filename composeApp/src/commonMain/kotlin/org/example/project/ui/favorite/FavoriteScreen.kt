package org.example.project.ui.favorite

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.ui.component.EventItem

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        item {
            Text(
                text = "お気に入り画面",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
//        item {
//            EventItem()
//            Spacer(modifier = Modifier.padding(bottom = 8.dp))
//        }
//
//        item {
//            EventItem()
//            Spacer(modifier = Modifier.padding(bottom = 8.dp))
//        }
//
//        item {
//            EventItem()
//            Spacer(modifier = Modifier.padding(bottom = 8.dp))
//        }
    }
}