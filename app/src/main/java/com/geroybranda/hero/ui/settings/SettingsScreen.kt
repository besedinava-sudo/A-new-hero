package com.geroybranda.hero.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geroybranda.hero.ui.components.SectionCard
import com.geroybranda.hero.ui.components.screenPadding
import com.geroybranda.hero.ui.theme.AppThemeMode

@Composable
fun SettingsScreen(
    themeMode: AppThemeMode,
    onThemeModeChange: (AppThemeMode) -> Unit,
    onClearHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showTerms by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
    var showClearConfirm by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(screenPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Настройки",
            style = MaterialTheme.typography.headlineLarge
        )

        SectionCard {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    text = "Тема оформления",
                    style = MaterialTheme.typography.titleLarge
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppThemeMode.entries.forEach { mode ->
                        val label = when (mode) {
                            AppThemeMode.System -> "Как в системе"
                            AppThemeMode.Light -> "Светлая"
                            AppThemeMode.Dark -> "Тёмная"
                        }
                        FilterChip(
                            selected = themeMode == mode,
                            onClick = { onThemeModeChange(mode) },
                            label = { Text(label) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }
        }

        SectionCard {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Документы",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = { showTerms = true }) {
                    Text("Пользовательское соглашение")
                }
            }
        }

        SectionCard {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Данные",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = { showClearConfirm = true }) {
                    Text("Очистить историю героев", color = MaterialTheme.colorScheme.error)
                }
            }
        }

        SectionCard {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "О приложении",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = { showAbout = true }) {
                    Text("Информация о приложении")
                }
            }
        }
    }

    if (showTerms) {
        AlertDialog(
            onDismissRequest = { showTerms = false },
            confirmButton = {
                TextButton(onClick = { showTerms = false }) {
                    Text("Понятно")
                }
            },
            title = { Text("Пользовательское соглашение") },
            text = {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = TERMS_TEXT,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }

    if (showAbout) {
        AlertDialog(
            onDismissRequest = { showAbout = false },
            confirmButton = {
                TextButton(onClick = { showAbout = false }) {
                    Text("Закрыть")
                }
            },
            title = { Text("Герой бренда") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Версия 1.0",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Приложение помогает придумать маскота для рекламы, соцсетей и брендинга. Все данные хранятся только на вашем устройстве.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        )
    }

    if (showClearConfirm) {
        AlertDialog(
            onDismissRequest = { showClearConfirm = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClearHistory()
                        showClearConfirm = false
                    }
                ) {
                    Text("Удалить", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClearConfirm = false }) {
                    Text("Отмена")
                }
            },
            title = { Text("Очистить историю?") },
            text = {
                Text("Все сохранённые герои будут удалены с устройства. Действие нельзя отменить.")
            }
        )
    }
}

private const val TERMS_TEXT =
    "Настоящее приложение «Герой бренда» предоставляется «как есть» в ознакомительных и творческих целях. " +
        "Сгенерированные персонажи и тексты не являются юридической или маркетинговой консультацией. " +
        "Пользователь самостоятельно проверяет соответствие материалов законодательству, правам третьих лиц и внутренним правилам бренда. " +
        "Разработчик не несёт ответственности за решения, принятые на основе содержимого приложения. " +
        "Данные о героях хранятся локально на устройстве и не передаются разработчику автоматически."
