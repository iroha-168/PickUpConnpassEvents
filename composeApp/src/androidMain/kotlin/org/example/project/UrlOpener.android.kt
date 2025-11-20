package org.example.project

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri

actual fun openUrl(url: String, context: Any?) {
    val context = context as? Context
    // fixme: urlが実在するのに取得できな時がある
    if (url.isBlank() || context == null) return
    val customTabsIntent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .build()
    customTabsIntent.launchUrl(
        context,
        url.toUri()
    )
}