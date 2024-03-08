package com.kevin.composestudy

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.kevin.composestudy.ui.theme.ColorPalette
import com.kevin.composestudy.ui.theme.Typography

@Composable
fun KevinTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ColorPalette, typography = Typography, content = content)
}
@Composable
fun KevinDialogThemeOverlay(content: @Composable () -> Unit) {
    val dialogColors = darkColorScheme(
        primary = Color.White,
        surface = Color.White.copy(alpha = 0.12f).compositeOver(Color.Black),
        onSurface = Color.White
    )

    // Copy the current [Typography] and replace some text styles for this theme.
    val currentTypography = MaterialTheme.typography
//    val dialogTypography = currentTypography.copy(
//        body2 = currentTypography.body1.copy(
//            fontWeight = FontWeight.Normal,
//            fontSize = 20.sp,
//            lineHeight = 28.sp,
//            letterSpacing = 1.sp
//        ),
//        button = currentTypography.button.copy(
//            fontWeight = FontWeight.Bold,
//            letterSpacing = 0.2.em
//        )
//    )
    MaterialTheme(colorScheme = dialogColors, typography = currentTypography, content = content)
}