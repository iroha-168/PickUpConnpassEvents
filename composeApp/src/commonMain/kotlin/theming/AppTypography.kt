package theming

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val headlineLarge: TextStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
    val headlineMedium: TextStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
    val bodyMedium: TextStyle = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
)