package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.data.HealingHerb
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealingScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val herbs by viewModel.filteredHerbs.collectAsState()
    val searchQuery by viewModel.herbQuery.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var selectedHerb by remember { mutableStateOf<HealingHerb?>(null) }
    var selectedTag by remember { mutableStateOf("ሁሉም") }

    val tags = listOf("ሁሉም", "ራስ ምታት", "ሆድ", "ዓይነ ጥላ", "ሳል", "ቆዳ", "ጭንቀት")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_healing", lang),
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
            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { viewModel.setHerbQuery(it) },
                placeholder = { Text("ዕፅዋት ወይም የሕመም ዓይነት ይፈልጉ...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = GoldPrimary) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { viewModel.setHerbQuery("") }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear", tint = TextParchmentDim)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .testTag("input_search_herb"),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = DarkCardBorder,
                    focusedTextColor = TextParchment,
                    unfocusedTextColor = TextParchment
                ),
                shape = RoundedCornerShape(12.dp)
            )

            // Category Filter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(tags) { tag ->
                    FilterChip(
                        selected = selectedTag == tag,
                        onClick = {
                            selectedTag = tag
                            if (tag == "ሁሉም") {
                                viewModel.setHerbQuery("")
                            } else {
                                viewModel.setHerbQuery(tag)
                            }
                        },
                        label = { Text(tag, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = EmeraldAccent,
                            selectedLabelColor = TextParchment,
                            containerColor = DarkSurface,
                            labelColor = TextParchment
                        )
                    )
                }
            }

            // Herb Details or List
            if (selectedHerb != null) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        OutlinedButton(
                            onClick = { selectedHerb = null },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight)
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("ወደ ዕፀዋት ዝርዝር ተመለስ")
                        }
                    }

                    item {
                        HerbDetailCard(
                            herb = selectedHerb!!,
                            onSpeak = { text -> viewModel.speak(text) }
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(herbs) { herb ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedHerb = herb }
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
                                            .size(42.dp)
                                            .background(EmeraldAccent.copy(alpha = 0.2f), RoundedCornerShape(10.dp)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            Icons.Default.Spa,
                                            contentDescription = null,
                                            tint = EmeraldAccent,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "${herb.nameAmharic} (${herb.nameGeez})",
                                            fontWeight = FontWeight.Bold,
                                            color = TextParchment,
                                            fontSize = 15.sp
                                        )
                                        Text(
                                            text = herb.scientificName,
                                            color = TextParchmentDim,
                                            fontSize = 11.sp,
                                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                        )
                                        Text(
                                            text = herb.ailmentsTreated.joinToString(" • "),
                                            color = GoldLight,
                                            fontSize = 12.sp,
                                            maxLines = 1
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

@Composable
fun HerbDetailCard(herb: HealingHerb, onSpeak: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, EmeraldAccent, RoundedCornerShape(16.dp)),
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
                        text = herb.nameAmharic,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                    Text(
                        text = "${herb.nameGeez} • ${herb.scientificName}",
                        color = TextParchmentDim,
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }

                FilledTonalIconButton(
                    onClick = {
                        val speech = "${herb.nameAmharic}። የሚፈውሰው፡ ${herb.ailmentsTreated.joinToString("፣ ")}። ${herb.preparationMethod}። ${herb.ancientManuscriptPrayer}"
                        onSpeak(speech)
                    },
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = EmeraldAccent,
                        contentColor = TextParchment
                    )
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Listen")
                }
            }

            // Treated ailments tags
            Column {
                Text(
                    text = "የሚፈውሳቸው ሕመሞችና ድካሞች:",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight,
                        fontSize = 13.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                herb.ailmentsTreated.forEach { item ->
                    Text(
                        text = "• $item",
                        fontSize = 13.sp,
                        color = TextParchment
                    )
                }
            }

            Divider(color = DarkCardBorder)

            SectionText("የአዘገጃጀት ዘዴ", herb.preparationMethod)
            SectionText("የአጠቃቀም መንገድ", herb.applicationMethod)

            // Ancient Healing Prayer Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(10.dp))
                    .border(1.dp, EmeraldAccent, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "ጥንታዊ የፈውስ ጸሎት (ልሳነ ግእዝ)",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = EmeraldAccent,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = herb.ancientManuscriptPrayer,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = GoldLight,
                            lineHeight = 18.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "ትርጉም፡ ${herb.amharicPrayerTranslation}",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextParchmentDim,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            // Safety Warning
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .border(1.dp, CrimsonSecondary.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Warning, contentDescription = null, tint = CrimsonLight, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = herb.safetyAdvisory,
                        fontSize = 11.sp,
                        color = TextParchmentDim
                    )
                }
            }
        }
    }
}
