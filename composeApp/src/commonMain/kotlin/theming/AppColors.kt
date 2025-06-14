package theming

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


data class AppColors(
    val background: Color,
    val primary: Color,
    val onPrimary: Color,
    val surface: Color,
) {
    companion object {
        val light = AppColors(
            background = White,
            primary = LightGray,
            onPrimary = DarkGray,
            surface = White,

        )
        val dark = AppColors(
            background = DarkGray,
            primary = MediumGray,
            onPrimary = LightGray,
            surface = DarkGray,
        )
    }
}

// Colors
val DarkGray = Color(0xFF3C3C43)
val MediumGray = Color(0xFF505055)
val LightGray = Color(0xFFF5F5FC)
val White = Color(0xFFFFFFFF)

@Composable
fun appColor(isDarkTheme: Boolean): AppColors {
    return when (isDarkTheme) {
        false -> AppColors.light
        true -> AppColors.dark
    }
}
