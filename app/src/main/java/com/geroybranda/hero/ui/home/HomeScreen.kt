package com.geroybranda.hero.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geroybranda.hero.data.HeroEntity
import com.geroybranda.hero.domain.BusinessSphere
import com.geroybranda.hero.domain.HeroColorTone
import com.geroybranda.hero.domain.HeroPersonality
import com.geroybranda.hero.domain.VisualStyle
import com.geroybranda.hero.ui.components.ChoiceSection
import com.geroybranda.hero.ui.components.HeroResultCard
import com.geroybranda.hero.ui.components.SectionCard
import com.geroybranda.hero.ui.components.screenPadding

@Composable
fun HomeScreen(
    lastCreated: HeroEntity?,
    onDismissResult: () -> Unit,
    onCreateHero: (BusinessSphere, HeroPersonality, VisualStyle, HeroColorTone) -> Unit,
    onToggleFavoriteLast: () -> Unit,
    modifier: Modifier = Modifier
) {
    var sphere by remember { mutableStateOf(BusinessSphere.CHILD_CLINIC) }
    var personality by remember { mutableStateOf(HeroPersonality.KIND) }
    var style by remember { mutableStateOf(VisualStyle.ANIMAL) }
    var color by remember { mutableStateOf(HeroColorTone.BLUE) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Создайте маскота бренда",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Выберите параметры — и получите имя, образ, миссию и идею для мультфильма.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        SectionCard {
            ChoiceSection(
                title = "Сфера бизнеса",
                options = BusinessSphere.entries,
                selected = sphere,
                label = { it.label },
                onSelect = { sphere = it }
            )
        }

        SectionCard {
            ChoiceSection(
                title = "Характер героя",
                options = HeroPersonality.entries,
                selected = personality,
                label = { it.label },
                onSelect = { personality = it }
            )
        }

        SectionCard {
            ChoiceSection(
                title = "Внешний стиль",
                options = VisualStyle.entries,
                selected = style,
                label = { it.label },
                onSelect = { style = it }
            )
        }

        SectionCard {
            ChoiceSection(
                title = "Цвет героя",
                options = HeroColorTone.entries,
                selected = color,
                label = { it.label },
                onSelect = { color = it }
            )
        }

        Button(
            onClick = { onCreateHero(sphere, personality, style, color) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = MaterialTheme.shapes.extraLarge,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Создать героя",
                style = MaterialTheme.typography.titleLarge
            )
        }

        lastCreated?.let { hero ->
            HeroResultCard(
                hero = hero,
                onFavoriteClick = onToggleFavoriteLast,
                onDismiss = onDismissResult,
                visible = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(Modifier.height(24.dp))
    }
}
