package com.geroybranda.hero.ui

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CollectionsBookmark
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.geroybranda.hero.data.HeroEntity
import com.geroybranda.hero.ui.favorites.FavoritesScreen
import com.geroybranda.hero.ui.gallery.GalleryScreen
import com.geroybranda.hero.ui.home.HomeScreen
import com.geroybranda.hero.ui.settings.SettingsScreen

private enum class MainTab(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    HOME("home", "Главная", Icons.Outlined.Home),
    GALLERY("gallery", "Галерея", Icons.Outlined.CollectionsBookmark),
    FAVORITES("favorites", "Избранное", Icons.Outlined.FavoriteBorder),
    SETTINGS("settings", "Настройки", Icons.Outlined.Settings)
}

@Composable
fun AppRoot(viewModel: AppViewModel) {
    val navController = rememberNavController()
    val heroes by viewModel.heroes.collectAsStateWithLifecycle()
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
    val lastCreated by viewModel.lastCreated.collectAsStateWithLifecycle()

    var pendingDelete by remember { mutableStateOf<HeroEntity?>(null) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val current = backStackEntry?.destination
            NavigationBar(tonalElevation = 3.dp) {
                MainTab.entries.forEach { tab ->
                    val selected = current?.hierarchy?.any { it.route == tab.route } == true
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(tab.label) },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainTab.HOME.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = {
                fadeIn(animationSpec = tween(280)) + slideInVertically(
                    animationSpec = tween(280),
                    initialOffsetY = { it / 14 }
                )
            },
            exitTransition = { fadeOut(animationSpec = tween(200)) },
            popEnterTransition = { fadeIn(animationSpec = tween(240)) },
            popExitTransition = {
                fadeOut(animationSpec = tween(200)) + slideOutVertically(
                    animationSpec = tween(240),
                    targetOffsetY = { it / 18 }
                )
            }
        ) {
            composable(MainTab.HOME.route) {
                HomeScreen(
                    lastCreated = lastCreated,
                    onDismissResult = { viewModel.dismissLastCreated() },
                    onCreateHero = { s, p, st, c -> viewModel.createHero(s, p, st, c) },
                    onToggleFavoriteLast = {
                        lastCreated?.let { viewModel.toggleFavorite(it) }
                    }
                )
            }
            composable(MainTab.GALLERY.route) {
                GalleryScreen(
                    heroes = heroes,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onDelete = { pendingDelete = it }
                )
            }
            composable(MainTab.FAVORITES.route) {
                FavoritesScreen(
                    favorites = favorites,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onDelete = { pendingDelete = it }
                )
            }
            composable(MainTab.SETTINGS.route) {
                SettingsScreen(
                    themeMode = themeMode,
                    onThemeModeChange = { viewModel.setThemeMode(it) },
                    onClearHistory = { viewModel.clearAllHeroes() }
                )
            }
        }
    }

    pendingDelete?.let { target ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteHero(target)
                        pendingDelete = null
                    }
                ) {
                    Text("Удалить", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingDelete = null }) {
                    Text("Отмена")
                }
            },
            title = { Text("Удалить героя?") },
            text = { Text("«${target.name}» будет удалён из галереи и избранного.") }
        )
    }
}
