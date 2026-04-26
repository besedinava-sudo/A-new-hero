package com.geroybranda.hero.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = IndigoNight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFE8EAF6),
    onPrimaryContainer = IndigoNight,
    secondary = MintFresh,
    onSecondary = Color(0xFF00382E),
    tertiary = CoralAccent,
    onTertiary = Color.White,
    background = SurfaceLight,
    onBackground = Color(0xFF1B1B1F),
    surface = CardLight,
    onSurface = Color(0xFF1B1B1F),
    surfaceVariant = Color(0xFFE1E2EC),
    onSurfaceVariant = Color(0xFF444654),
    outline = Color(0xFF757685)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFB8C4FF),
    onPrimary = Color(0xFF1A237E),
    primaryContainer = Color(0xFF2F3555),
    onPrimaryContainer = Color(0xFFE8EAF6),
    secondary = Color(0xFF63E8CF),
    onSecondary = Color(0xFF00382E),
    tertiary = Color(0xFFFF8A80),
    onTertiary = Color(0xFF5F0D0D),
    background = SurfaceDark,
    onBackground = Color(0xFFE4E1E6),
    surface = CardDark,
    onSurface = Color(0xFFE4E1E6),
    surfaceVariant = Color(0xFF46475A),
    onSurfaceVariant = Color(0xFFC6C5D0),
    outline = Color(0xFF90909A)
)

@Composable
fun GeroyBrandaTheme(
    themeMode: AppThemeMode,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val darkTheme = when (themeMode) {
        AppThemeMode.Dark -> true
        AppThemeMode.Light -> false
        AppThemeMode.System -> systemDark
    }

    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = HeroTypography,
        content = content
    )
}
