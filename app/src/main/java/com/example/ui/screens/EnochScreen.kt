package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EnochChapter
import com.example.data.EnochEngine
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnochScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var selectedChapter by remember { mutableStateOf<EnochChapter?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_enoch", lang),
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = GoldLight)
                    }
                },
                actions = {
                    if (isSpeaking) {
                        IconButton(onClick = { viewModel.stopSpeaking() }) {
                            Icon(Icons.Default.Stop, contentDescription = "Stop", tint = CrimsonLight)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (selectedChapter != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        OutlinedButton(
                            onClick = { selectedChapter = null },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("ወደ ሄኖክ ምዕራፎች ተመለስ")
                        }
                    }

                    item {
                        val ch = selectedChapter!!
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp),
                                verticalArrangement = Arrangement.spacedBy(14.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = ch.titleGeez,
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = GoldLight
                                            )
                                        )
                                        Text(
                                            text = ch.titleAmharic,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextParchmentDim
                                            )
                                        )
                                    }

                                    FilledTonalIconButton(
                                        onClick = {
                                            viewModel.speak("${ch.titleAmharic}። ${ch.summary}። ${ch.sacredManuscriptPassage}")
                                        },
                                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                                            containerColor = GoldPrimary,
                                            contentColor = DarkBackground
                                        )
                                    ) {
                                        Icon(Icons.Default.VolumeUp, contentDescription = "Listen")
                                    }
                                }

                                Text(
                                    text = ch.summary,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        color = TextParchment,
                                        lineHeight = 20.sp
                                    )
                                )

                                // Ge'ez Manuscript Scripture Box
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(DarkBackground, RoundedCornerShape(12.dp))
                                        .border(1.dp, GoldDark, RoundedCornerShape(12.dp))
                                        .padding(14.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = "ጥንታዊ የብራና ቃል (መጽሐፈ ሄኖክ)",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = GoldLight,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = ch.sacredManuscriptPassage,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextParchment,
                                                lineHeight = 20.sp
                                            )
                                        )
                                    }
                                }

                                SectionText("የሥነ-ከዋክብትና የቀመር ምስጢር", ch.astronomicalWisdom)
                                SectionText("መንፈሳዊ ትምህርትና ፍቺ", ch.spiritualMeaning)
                            }
                        }
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "በዓለም ብቸኛ የሆነው የኢትዮጵያ መጽሐፈ ሄኖክ ሙሉ ቅጂ ጥበባት",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextParchmentDim),
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    items(EnochEngine.chapters) { ch ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedChapter = ch }
                                .border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
                            colors = CardDefaults.cardColors(containerColor = DarkSurface),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .background(GoldPrimary.copy(alpha = 0.15f), RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.AutoStories,
                                            contentDescription = null,
                                            tint = GoldPrimary,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = ch.titleGeez,
                                            fontWeight = FontWeight.Bold,
                                            color = TextParchment,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = ch.titleAmharic,
                                            color = GoldLight,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = ch.summary,
                                            color = TextParchmentDim,
                                            fontSize = 11.sp,
                                            maxLines = 2
                                        )
                                    }
                                }

                                Icon(
                                    Icons.Default.ChevronRight,
                                    contentDescription = null,
                                    tint = GoldPrimary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
