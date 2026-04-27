package com.geroybranda.hero.ui.gallery

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.geroybranda.hero.data.HeroEntity
import com.geroybranda.hero.ui.components.HeroListCard
import com.geroybranda.hero.ui.components.screenPadding

@Composable
fun GalleryScreen(
    heroes: List<HeroEntity>,
    onToggleFavorite: (HeroEntity) -> Unit,
    onDelete: (HeroEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    if (heroes.isEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(screenPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.PhotoLibrary,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = "Пока нет сохранённых героев",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Создайте первого на вкладке «Главная».",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .animateContentSize(),
            contentPadding = screenPadding,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = "Галерея",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "${heroes.size} персонажей",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
            }
            items(heroes, key = { it.id }) { hero ->
                HeroListCard(
                    hero = hero,
                    onToggleFavorite = { onToggleFavorite(hero) },
                    onDelete = { onDelete(hero) }
                )
            }
        }
    }
}
