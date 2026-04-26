package com.geroybranda.hero.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.geroybranda.hero.GeroyBrandaApp
import com.geroybranda.hero.data.HeroEntity
import com.geroybranda.hero.data.HeroRepository
import com.geroybranda.hero.data.ThemePreferences
import com.geroybranda.hero.domain.BusinessSphere
import com.geroybranda.hero.domain.HeroColorTone
import com.geroybranda.hero.domain.HeroGenerator
import com.geroybranda.hero.domain.HeroPersonality
import com.geroybranda.hero.domain.VisualStyle
import com.geroybranda.hero.ui.theme.AppThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.random.Random

class AppViewModel(application: Application) : AndroidViewModel(application) {

    private val app = application as GeroyBrandaApp
    private val repository = HeroRepository(app.database.heroDao())
    private val themePreferences = ThemePreferences(application)

    val heroes: StateFlow<List<HeroEntity>> = repository.observeHeroes()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val favorites: StateFlow<List<HeroEntity>> = repository.observeFavorites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val themeMode: StateFlow<AppThemeMode> = themePreferences.themeMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppThemeMode.System)

    private val _lastCreated = MutableStateFlow<HeroEntity?>(null)
    val lastCreated: StateFlow<HeroEntity?> = _lastCreated

    fun dismissLastCreated() {
        _lastCreated.value = null
    }

    fun createHero(
        sphere: BusinessSphere,
        personality: HeroPersonality,
        style: VisualStyle,
        color: HeroColorTone
    ) {
        viewModelScope.launch {
            val generated = HeroGenerator.generate(
                sphere = sphere,
                personality = personality,
                style = style,
                color = color,
                random = Random(System.nanoTime())
            )
            val entity = generated.toEntity()
            repository.insert(entity)
            _lastCreated.value = entity
        }
    }

    fun toggleFavorite(hero: HeroEntity) {
        viewModelScope.launch {
            repository.update(hero.copy(isFavorite = !hero.isFavorite))
            _lastCreated.value?.takeIf { it.id == hero.id }?.let { current ->
                _lastCreated.value = current.copy(isFavorite = !current.isFavorite)
            }
        }
    }

    fun deleteHero(hero: HeroEntity) {
        viewModelScope.launch {
            repository.delete(hero.id)
            if (_lastCreated.value?.id == hero.id) _lastCreated.value = null
        }
    }

    fun clearAllHeroes() {
        viewModelScope.launch {
            repository.clearAll()
            _lastCreated.value = null
        }
    }

    fun setThemeMode(mode: AppThemeMode) {
        viewModelScope.launch {
            themePreferences.setThemeMode(mode)
        }
    }
}
