package org.example.project.ui.event.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.empty_events_not_found
import pickupconnpassevents.composeapp.generated.resources.sentiment_neutral

@Composable
fun EmptyEvents(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(80.dp),
            painter = painterResource(Res.drawable.sentiment_neutral),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier,
            text = stringResource(Res.string.empty_events_not_found),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
