package com.geroybranda.hero.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String,
    val appearance: String,
    val personality: String,
    val mission: String,
    val brandRole: String,
    val cartoonIdea: String,
    val sphereLabel: String,
    val emoji: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)
