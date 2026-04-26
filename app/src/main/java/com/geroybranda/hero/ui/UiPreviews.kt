package com.geroybranda.hero.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.geroybranda.hero.data.HeroEntity
import com.geroybranda.hero.ui.home.HomeScreen
import com.geroybranda.hero.ui.theme.AppThemeMode
import com.geroybranda.hero.ui.theme.GeroyBrandaTheme

private val previewHero = HeroEntity(
    name = "Доктор Лучик ☀️",
    appearance = "Внешность: в нежно-голубых тонах. Доброе пушистое существо в мягком халате с выразительными глазами.",
    personality = "Тёплый, внимательный, никогда не осуждает — только поддерживает.",
    mission = "Помогает детям не бояться процедур и чувствовать себя героями визита.",
    brandRole = "Узнаваемый герой, который объединяет рекламу, соцсети и офлайн-материалы.",
    cartoonIdea = "Короткий мультфильм: герой провожает ребёнка по клинике и превращает страх в игру.",
    sphereLabel = "Детская клиника",
    emoji = "☀️",
    isFavorite = false
)

@Preview(name = "Экран «Главная» — светлая тема", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeLight() {
    GeroyBrandaTheme(AppThemeMode.Light) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                lastCreated = previewHero,
                onDismissResult = {},
                onCreateHero = { _, _, _, _ -> },
                onToggleFavoriteLast = {}
            )
        }
    }
}

@Preview(name = "Экран «Главная» — тёмная тема", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewHomeDark() {
    GeroyBrandaTheme(AppThemeMode.Dark) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                lastCreated = null,
                onDismissResult = {},
                onCreateHero = { _, _, _, _ -> },
                onToggleFavoriteLast = {}
            )
        }
    }
}
