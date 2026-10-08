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
import com.example.data.EnochChapter
import com.example.data.EnochEngine
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnochScreen(viewModel: MainViewModel) {
    var selectedChapter by remember { mutableStateOf<EnochChapter?>(EnochEngine.chapters.firstOrNull()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("መጽሐፈ ሄኖክ (የሰማይ ብርሃናት ቀመር)", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_enoch")) {
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
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("ስለ መጽሐፈ ሄኖክ ቀመር", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            "መጽሐፈ ሄኖክ በግእዝ ቋንቋ ብቻ ተጠብቆ የቆየ ታላቅ ጥንታዊ የከዋክብት፣ የፀሐይ፣ የጨረቃ፣ የወራቶችና የመላእክት ሥርዓት መዝገብ ነው።",
                            color = TextParchment,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            selectedChapter?.let { ch ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(ch.titleGeez, fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 16.sp)
                                    Text(ch.titleAmharic, color = GoldLight, fontSize = 13.sp)
                                }
                                IconButton(
                                    onClick = {
                                        viewModel.speak("${ch.titleAmharic}። ${ch.sacredManuscriptPassage}። ${ch.astronomicalWisdom}")
                                    },
                                    modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(GoldPrimary.copy(alpha = 0.2f)).testTag("btn_speak_enoch")
                                ) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Speak", tint = GoldPrimary)
                                }
                            }

                            Text(ch.summary, color = TextParchment, fontSize = 12.sp)

                            Text("የግእዝ ብራና ጽሑፍ ጥቅስ፡", color = CrimsonLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                            Surface(color = DarkBackground, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = ch.sacredManuscriptPassage,
                                    modifier = Modifier.padding(12.dp),
                                    color = TextParchment,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp
                                )
                            }

                            Text("የከዋክብትና የዘመን ቀመር ምስጢር፡", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(ch.astronomicalWisdom, color = TextParchment, fontSize = 12.sp)

                            Text("መንፈሳዊ ትምህርት፡", color = EmeraldAccent, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                            Text(ch.spiritualMeaning, color = TextParchment, fontSize = 12.sp)
                        }
                    }
                }
            }

            item {
                Text("ሌሎች የሄኖክ ምዕራፎች፡", color = GoldPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            items(EnochEngine.chapters) { ch ->
                Card(
                    modifier = Modifier.fillMaxWidth().border(1.dp, DarkCardBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(12.dp),
                    onClick = { selectedChapter = ch }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(ch.titleGeez, fontWeight = FontWeight.Bold, color = TextParchment, fontSize = 14.sp)
                            Text(ch.titleAmharic, color = TextParchmentDim, fontSize = 12.sp)
                        }
                        Icon(Icons.Default.MenuBook, contentDescription = null, tint = GoldLight)
                    }
                }
            }
        }
    }
}
