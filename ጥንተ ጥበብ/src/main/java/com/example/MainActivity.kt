package com.example

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: MainViewModel = viewModel()) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.toastMessage.collectLatest { msg ->
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }

    // Handle back button according to Guidelines
    if (currentScreen != Screen.HOME && currentScreen != Screen.REGISTRATION) {
        BackHandler {
            viewModel.navigateBack()
        }
    }

    when (currentScreen) {
        Screen.REGISTRATION -> RegistrationScreen(viewModel)
        Screen.HOME -> HomeScreen(viewModel)
        Screen.ZODIAC -> ZodiacScreen(viewModel)
        Screen.BAHIRE_HASAB -> BahireHasabScreen(viewModel)
        Screen.LOVE_HARMONY -> LoveHarmonyScreen(viewModel)
        Screen.HEALING -> HealingScreen(viewModel)
        Screen.ORACLE -> OracleScreen(viewModel)
        Screen.ENOCH -> EnochScreen(viewModel)
        Screen.PAYMENT -> PaymentScreen(viewModel)
        Screen.HISTORY -> HistoryScreen(viewModel)
        Screen.PROFILE -> ProfileScreen(viewModel)
    }
}
