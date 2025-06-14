package theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    appColor: AppColors = appColor(isDarkTheme),
    appTypography: AppTypography = AppTypography(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) {
        darkColorScheme(
            background = appColor.background,
            primary = appColor.primary,
            onPrimary = appColor.onPrimary,
            surface = appColor.surface,
        )
    } else {
        lightColorScheme(
            background = appColor.background,
            primary = appColor.primary,
            onPrimary = appColor.onPrimary,
            surface = appColor.surface,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
    ) {
        CompositionLocalProvider(
            LocalAppColors provides appColor,
            LocalAppTypography provides appTypography,
            content = content,
        )
    }
}

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current
}

private val LocalAppColors = compositionLocalOf<AppColors> { error("Missing AppColors") }
private val LocalAppTypography = compositionLocalOf<AppTypography> { error("Missing AppTypography") }