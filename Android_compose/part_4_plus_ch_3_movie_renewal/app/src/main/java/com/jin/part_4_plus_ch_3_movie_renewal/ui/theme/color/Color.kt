package com.jin.part_4_plus_ch_3_movie_renewal.ui.theme.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val Red200 = Color(0xFFFFAAAA)
val Red300 = Color(0xFFCC5942)
val Red400 = Color(0xFFFF5258)
val Red700 = Color(0xFFEC0000)
val Red800 = Color(0xFFAF0000)
val Red900 = Color(0xFF531F1C)
val Purple200 = Color(0xFF908499)
val Purple400 = Color(0xFF6D59FF)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF635270)
val Purple900 = Color(0xFF200833)
val Green400 = Color(0xFF55D800)
val Blue400 = Color(0xFF395DE8)
val Grey200 = Color(0xFF908499)
val Grey900 = Color(0xFF151515)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


sealed class ColorSet { // abstract class
    open lateinit var lightColors: MyColors
    open lateinit var darkColors: MyColors

    object Red:ColorSet() {
        override var lightColors = MyColors(
            material = lightColorScheme(
                primary = Red700,
                secondary = Purple900,
                surface = White,
                onSurface = Black,
                background = White,
                onBackground = Black,
                error = Red400
            ),
            success = Green400,
            disabledSecondary = Grey200,
            textFiledBackground = Grey200
        )

        override var darkColors = MyColors(
            material = darkColorScheme(),
            success = Green400,
            disabledSecondary = Grey200,
            textFiledBackground = Grey200
        )
    }
}