package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(viewModel: MainViewModel) {
    val consultations by viewModel.consultations.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("የተመዘገቡ የጥበብ ማህደሮች", fontWeight = FontWeight.Bold, color = GoldPrimary) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_history")) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = GoldLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        if (consultations.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.BookmarkBorder, contentDescription = null, tint = TextParchmentDim, modifier = Modifier.size(48.dp))
                    Text("እስካሁን የተመዘገበ የጥበብ ታሪክ የለም", color = TextParchmentDim, fontSize = 14.sp)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(consultations) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth().border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    Badge(containerColor = GoldPrimary.copy(alpha = 0.2f)) {
                                        Text(record.category, color = GoldPrimary, fontSize = 11.sp, modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp))
                                    }
                                    Text(record.title, fontWeight = FontWeight.Bold, color = TextParchment, fontSize = 14.sp)
                                }

                                Row {
                                    IconButton(
                                        onClick = {
                                            viewModel.speak("${record.title}። ${record.geezScript}። ${record.translation}")
                                        },
                                        modifier = Modifier.size(32.dp).testTag("btn_speak_history_${record.id}")
                                    ) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = "Speak", tint = GoldLight, modifier = Modifier.size(18.dp))
                                    }
                                    IconButton(
                                        onClick = { viewModel.toggleFavorite(record.id) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            if (record.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                                            contentDescription = "Favorite",
                                            tint = if (record.isFavorite) GoldPrimary else TextParchmentDim,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                    IconButton(
                                        onClick = { viewModel.deleteConsultation(record.id) },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = CrimsonLight, modifier = Modifier.size(18.dp))
                                    }
                                }
                            }

                            if (record.geezScript.isNotBlank()) {
                                Surface(
                                    color = DarkBackground,
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        record.geezScript,
                                        modifier = Modifier.padding(8.dp),
                                        color = GoldLight,
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp
                                    )
                                }
                            }

                            if (record.translation.isNotBlank()) {
                                Text(record.translation, color = TextParchment, fontSize = 11.sp)
                            }

                            if (record.prescription.isNotBlank()) {
                                Text("ማስታወሻ/ትእዛዝ፡ ${record.prescription}", color = EmeraldAccent, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
