package com.jin.part_4_plus_ch_3_movie_renewal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.color.ColorSet


private val localColors = staticCompositionLocalOf {
    ColorSet.Red.lightColors
}
@Composable
fun Part_4_plus_ch_3_movie_renewalTheme(
    myColors: ColorSet,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        myColors.darkColors
    } else {
        myColors.lightColors
    }

    CompositionLocalProvider(localColors provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme.material,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}