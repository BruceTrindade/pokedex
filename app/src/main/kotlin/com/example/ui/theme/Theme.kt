package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = PokeBlack,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = PokeBlack,
    surface = Color.White,
    onSurface = PokeBlack,
)

private val DarkColors = darkColorScheme(
    primary = PokeWhite,
    onPrimary = PokeBlack,
    background = Background,
    onBackground = Color.White,
    surface = Background,
    onSurface = Color.White,
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        content = content,
    )
}
