package org.example.project.ui.event.section

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pickupconnpassevents.composeapp.generated.resources.Res
import pickupconnpassevents.composeapp.generated.resources.error_dialog_button
import pickupconnpassevents.composeapp.generated.resources.error_dialog_title
import theming.AppTheme

@Composable
fun ErrorDialog(
    onDismissRequest: () -> Unit,
    message: String,
) {
    AlertDialog(
        modifier = Modifier.size(300.dp, 220.dp),
        onDismissRequest = onDismissRequest,
        title = { Text(text = stringResource(Res.string.error_dialog_title)) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onDismissRequest) {
                Text(text = stringResource(Res.string.error_dialog_button))
            }
        }
    )
}

@Preview
@Composable
fun ErrorDialogPreview() {
    AppTheme {
        ErrorDialog(
            onDismissRequest = {},
            message = "This is an error message."
        )
    }
}