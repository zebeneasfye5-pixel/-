package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.screens.*
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme(darkTheme = true) {
                val context = LocalContext.current
                val currentScreen by viewModel.currentScreen.collectAsState()

                // Toast notification listener
                LaunchedEffect(Unit) {
                    viewModel.toastMessage.collect { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                }

                // Global BackHandler
                BackHandler(enabled = currentScreen != Screen.HOME && currentScreen != Screen.REGISTRATION) {
                    viewModel.navigateBack()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBackground
                ) {
                    when (currentScreen) {
                        Screen.REGISTRATION -> RegistrationScreen(viewModel = viewModel)
                        Screen.HOME -> HomeScreen(viewModel = viewModel)
                        Screen.ZODIAC -> ZodiacScreen(viewModel = viewModel)
                        Screen.BAHIRE_HASAB -> BahireHasabScreen(viewModel = viewModel)
                        Screen.LOVE_HARMONY -> LoveHarmonyScreen(viewModel = viewModel)
                        Screen.HEALING -> HealingScreen(viewModel = viewModel)
                        Screen.ORACLE -> OracleScreen(viewModel = viewModel)
                        Screen.ENOCH -> EnochScreen(viewModel = viewModel)
                        Screen.PAYMENT -> PaymentScreen(viewModel = viewModel)
                        Screen.HISTORY -> HistoryScreen(viewModel = viewModel)
                        Screen.PROFILE -> ProfileScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
