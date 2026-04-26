package com.geroybranda.hero

import android.app.Application
import androidx.room.Room
import com.geroybranda.hero.data.AppDatabase

class GeroyBrandaApp : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "geroy_branda.db"
        ).fallbackToDestructiveMigration().build()
    }
}
