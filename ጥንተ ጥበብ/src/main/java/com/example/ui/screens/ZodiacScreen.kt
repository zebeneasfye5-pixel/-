package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val zodiacResult by viewModel.zodiacResult.collectAsState()
    var seekerName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(LanguageManager.getString("nav_zodiac", lang), fontWeight = FontWeight.Bold, color = GoldPrimary) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = GoldLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text("የአቡሻህር ሂሳብ (የጠያቂው ስም + የእናት ስም ቀመር)", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        OutlinedTextField(
                            value = seekerName,
                            onValueChange = { seekerName = it },
                            label = { Text("የጠያቂው ስም (ለምሳሌ፡ ሰሎሞን)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = motherName,
                            onValueChange = { motherName = it },
                            label = { Text("የእናት ስም (ለምሳሌ፡ ወለተ ጊዮርጊስ)") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        Button(
                            onClick = { viewModel.calculateZodiac(seekerName, motherName) },
                            modifier = Modifier.fillMaxWidth().height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = DarkBackground),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("ቀመሩን አውጣ (Calculate)", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            zodiacResult?.let { res ->
                val sign = res.sign
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Column {
                                    Text("ቀመር ቊጥር፡ ${res.remainderMod12}", color = GoldLight, fontSize = 12.sp)
                                    Text("${sign.nameGeez} (${sign.nameAmharic})", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 18.sp)
                                }
                                IconButton(onClick = { viewModel.speak("${sign.nameGeez}። ${sign.personality}። ${sign.ancientPrayer}") }) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Listen", tint = GoldLight)
                                }
                            }
                            Text("ንጥረ-ነገር፡ ${sign.element} | መልአክ፡ ${sign.angel}", fontSize = 12.sp, color = TextParchment)
                            Text("ጠባይ፡ ${sign.personality}", fontSize = 12.sp, color = TextParchment)
                            Text("ሀብትና እጣ፡ ${sign.destinyAndWealth}", fontSize = 12.sp, color = TextParchment)
                            Box(modifier = Modifier.fillMaxWidth().background(DarkBackground, RoundedCornerShape(8.dp)).padding(10.dp)) {
                                Text(sign.ancientPrayer, color = GoldLight, fontSize = 12.sp, lineHeight = 18.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
