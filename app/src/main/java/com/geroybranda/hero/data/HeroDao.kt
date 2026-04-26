package com.geroybranda.hero.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<HeroEntity>>

    @Query("SELECT * FROM heroes WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun observeFavorites(): Flow<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hero: HeroEntity)

    @Update
    suspend fun update(hero: HeroEntity)

    @Query("DELETE FROM heroes WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM heroes")
    suspend fun clearAll()
}
