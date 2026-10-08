package com.example.ui.screens

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
fun BahireHasabScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val hasab by viewModel.bahireHasabResult.collectAsState()
    var yearText by remember { mutableStateOf("2017") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(LanguageManager.getString("nav_bahire_hasab", lang), fontWeight = FontWeight.Bold, color = GoldPrimary) },
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
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = yearText,
                                onValueChange = { yearText = it },
                                label = { Text("ዓመተ ምሕረት (ዓ/ም)") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            Button(
                                onClick = {
                                    val y = yearText.toIntOrNull() ?: 2017
                                    viewModel.calculateBahireHasab(y)
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = DarkBackground)
                            ) {
                                Text("አስላ", fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("ባህረ ሐሳብ ዘዓመተ ${hasab.year}", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 16.sp)
                        Text("ወንጌላዊ፡ ${hasab.evangelist}", color = TextParchment, fontSize = 13.sp)
                        Text("መጥቅዕ፡ ${hasab.metqe} | አበቅቴ፡ ${hasab.abaqte} | ወንበር፡ ${hasab.wenber}", color = TextParchmentDim, fontSize = 12.sp)
                        Divider(color = DarkCardBorder)
                        Text("ጾመ ነነዌ፡ ${hasab.neneweDate}", color = TextParchment, fontSize = 13.sp)
                        Text("ዓቢይ ጾም፡ ${hasab.lentDate}", color = TextParchment, fontSize = 13.sp)
                        Text("ፋሲካ (ትንሣኤ)፡ ${hasab.fasikaDate}", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 14.sp)
                        Text("ዕርገት፡ ${hasab.ergetAscensionDate}", color = TextParchment, fontSize = 13.sp)
                        Text("ጰራቅሊጦስ፡ ${hasab.pentecostDate}", color = TextParchment, fontSize = 13.sp)
                    }
                }
            }
        }
    }
}
