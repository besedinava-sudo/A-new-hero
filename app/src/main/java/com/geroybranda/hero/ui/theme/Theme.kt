package com.geroybranda.hero.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = BrandDeepBlue,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFD4E6FA),
    onPrimaryContainer = BrandDeepBlue,
    secondary = AccentSoftYellow,
    onSecondary = OnAccentYellow,
    secondaryContainer = Color(0xFFFFF2CC),
    onSecondaryContainer = TextGraphite,
    tertiary = BrandBlue,
    onTertiary = Color.White,
    background = MilkBackground,
    onBackground = TextGraphite,
    surface = CardSurfaceLight,
    onSurface = TextGraphite,
    surfaceVariant = Color(0xFFE6EEF8),
    onSurfaceVariant = TextMutedLight,
    surfaceContainerLowest = Color(0xFFEEF3FA),
    surfaceContainerLow = Color(0xFFF7FAFF),
    surfaceContainer = CardSurfaceLight,
    surfaceContainerHigh = Color(0xFFFFFFFF),
    outline = Color(0xFFC5D0E0),
    outlineVariant = Color(0xFFE0E7F0),
    error = Color(0xFFB3261E),
    onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = DarkAccentBlue,
    onPrimary = Color(0xFF031525),
    primaryContainer = Color(0xFF243447),
    onPrimaryContainer = Color(0xFFD8E9FF),
    secondary = DarkAccentYellow,
    onSecondary = Color(0xFF221A00),
    secondaryContainer = Color(0xFF3D3318),
    onSecondaryContainer = Color(0xFFFFF0C2),
    tertiary = Color(0xFF5BA3FF),
    onTertiary = Color(0xFF001A33),
    background = DarkNavyBackground,
    onBackground = Color(0xFFE6EDF8),
    surface = DarkGraphiteSurface,
    onSurface = Color(0xFFE6EDF8),
    surfaceVariant = Color(0xFF2A3548),
    onSurfaceVariant = Color(0xFFB4C0D4),
    surfaceContainerLowest = Color(0xFF040D18),
    surfaceContainerLow = Color(0xFF0E1624),
    surfaceContainer = DarkGraphiteSurface,
    surfaceContainerHigh = Color(0xFF1C2636),
    outline = Color(0xFF4A5A70),
    outlineVariant = Color(0xFF323F52),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
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
        shapes = HeroShapes,
        content = content
    )
}
