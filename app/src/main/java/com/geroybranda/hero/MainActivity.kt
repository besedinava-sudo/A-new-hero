package com.geroybranda.hero

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geroybranda.hero.ui.AppRoot
import com.geroybranda.hero.ui.AppViewModel
import com.geroybranda.hero.ui.theme.GeroyBrandaTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: AppViewModel = viewModel()
            val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()
            GeroyBrandaTheme(themeMode = themeMode) {
                AppRoot(viewModel)
            }
        }
    }
}
