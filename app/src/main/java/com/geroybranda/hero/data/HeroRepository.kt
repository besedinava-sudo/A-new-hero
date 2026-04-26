package com.geroybranda.hero.data

import kotlinx.coroutines.flow.Flow

class HeroRepository(
    private val dao: HeroDao
) {
    fun observeHeroes(): Flow<List<HeroEntity>> = dao.observeAll()

    fun observeFavorites(): Flow<List<HeroEntity>> = dao.observeFavorites()

    suspend fun insert(hero: HeroEntity) = dao.insert(hero)

    suspend fun update(hero: HeroEntity) = dao.update(hero)

    suspend fun delete(id: String) = dao.deleteById(id)

    suspend fun clearAll() = dao.clearAll()
}
