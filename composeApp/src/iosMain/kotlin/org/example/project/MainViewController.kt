package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import co.touchlab.kermit.Logger

fun MainViewController() = ComposeUIViewController {
    App(
        openUrl = { url ->
            Logger.d{"HOGE: url = $url"}
            openUrl(
                url = url,
                context = null,
            )
        }
    )
}