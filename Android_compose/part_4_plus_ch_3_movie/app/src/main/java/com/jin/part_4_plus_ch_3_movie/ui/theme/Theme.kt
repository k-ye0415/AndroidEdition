package com.jin.part_4_plus_ch_3_movie.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.ColorSet
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.MyColors
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.Pink40
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.Pink80
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.Purple40
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.Purple80
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.PurpleGrey40
import com.jin.part_4_plus_ch_3_movie.ui.theme.color.PurpleGrey80

private val LocalColors = staticCompositionLocalOf { ColorSet.Red.LightColors }

@Composable
fun Part_4_plus_ch_3_movieTheme(
    myColors: ColorSet,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }

        darkTheme -> myColors.DarkColors
        else -> myColors.LightColors
    }

    CompositionLocalProvider(LocalColors provides colorScheme) {
        MaterialTheme(
            colorScheme = colorScheme.material,
            typography = Typography,
            content = content
        )
    }

}