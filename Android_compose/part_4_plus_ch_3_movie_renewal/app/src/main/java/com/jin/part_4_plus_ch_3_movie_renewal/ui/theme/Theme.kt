package com.jin.part_4_plus_ch_3_movie_renewal.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.color.ColorSet
import com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.color.MyColors


private val localColors = staticCompositionLocalOf {
    ColorSet.Red.lightColors
}
@Composable
fun Part_4_plus_ch_3_movie_renewalTheme(
    myColors: ColorSet = ColorSet.Red,
    typography: Typography = Typography,
    shapes:Shapes = Shapes,
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
            typography = typography,
            shapes = shapes,
            content = content
        )
    }
}

val MaterialTheme.myColors : MyColors
    @Composable
    @ReadOnlyComposable
    get() = localColors.current