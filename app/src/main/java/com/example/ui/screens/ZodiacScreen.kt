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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageManager
import com.example.data.ZodiacEngine
import com.example.data.ZodiacSign
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val zodiacResult by viewModel.zodiacResult.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var seekerName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) } // 0: አስላ (Calculate), 1: ፲፪ቱ ከዋክብት (All 12)
    var selectedSignDetail by remember { mutableStateOf<ZodiacSign?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_zodiac", lang),
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
                            Icon(Icons.Default.Stop, contentDescription = "Stop Voice", tint = CrimsonLight)
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
            // Tab Switcher
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = DarkSurface,
                contentColor = GoldPrimary
            ) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("የኮከብ ቀመር አስላ", fontWeight = FontWeight.SemiBold) }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("፲፪ቱ ከዋክብት", fontWeight = FontWeight.SemiBold) }
                )
            }

            if (selectedTab == 0) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(containerColor = DarkSurface),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Text(
                                    text = "የአቡሻህር ሂሳብ (የጠያቂው ስም + የእናት ስም ቀመር)",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = GoldLight,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Text(
                                    text = "በጥንታዊው አበግደሀ የፊደል ቁጥር ሂሳብ መሠረት የሁለቱ ስሞች ድምር ለ ፲፪ ተካፍሎ የሚቀረው ቁጥር ትክክለኛውን ኮከብና ባሕርይ ያወጣልናል፡",
                                    fontSize = 12.sp,
                                    color = TextParchmentDim
                                )

                                OutlinedTextField(
                                    value = seekerName,
                                    onValueChange = { seekerName = it },
                                    label = { Text("የጠያቂው ስም (ለምሳሌ፡ ሰሎሞን)") },
                                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = GoldPrimary) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("input_zodiac_seeker"),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = GoldPrimary,
                                        unfocusedBorderColor = DarkCardBorder,
                                        focusedLabelColor = GoldPrimary,
                                        unfocusedLabelColor = TextParchmentDim,
                                        focusedTextColor = TextParchment,
                                        unfocusedTextColor = TextParchment
                                    )
                                )

                                OutlinedTextField(
                                    value = motherName,
                                    onValueChange = { motherName = it },
                                    label = { Text("የእናት ስም (ለምሳሌ፡ ወለተ ጊዮርጊስ)") },
                                    leadingIcon = { Icon(Icons.Default.Face, contentDescription = null, tint = GoldPrimary) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag("input_zodiac_mother"),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = GoldPrimary,
                                        unfocusedBorderColor = DarkCardBorder,
                                        focusedLabelColor = GoldPrimary,
                                        unfocusedLabelColor = TextParchmentDim,
                                        focusedTextColor = TextParchment,
                                        unfocusedTextColor = TextParchment
                                    )
                                )

                                Button(
                                    onClick = {
                                        viewModel.calculateZodiac(seekerName, motherName)
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .testTag("btn_calculate_zodiac"),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GoldPrimary,
                                        contentColor = DarkBackground
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = DarkBackground)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("ቀመሩን አውጣ (Calculate Star)", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }

                    zodiacResult?.let { res ->
                        item {
                            ZodiacResultCard(
                                result = res,
                                onSpeak = { text -> viewModel.speak(text) }
                            )
                        }
                    }
                }
            } else {
                // All 12 Zodiac Directory
                if (selectedSignDetail != null) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            OutlinedButton(
                                onClick = { selectedSignDetail = null },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight)
                            ) {
                                Icon(Icons.Default.ArrowBack, contentDescription = null)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("ወደ ፲፪ቱ ከዋክብት ዝርዝር ተመለስ")
                            }
                        }
                        item {
                            ZodiacDetailView(
                                sign = selectedSignDetail!!,
                                onSpeak = { text -> viewModel.speak(text) }
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(ZodiacEngine.signs) { sign ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedSignDetail = sign }
                                    .border(1.dp, DarkCardBorder, RoundedCornerShape(12.dp)),
                                colors = CardDefaults.cardColors(containerColor = DarkSurface),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(GoldPrimary.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "${sign.id}",
                                                fontWeight = FontWeight.Bold,
                                                color = GoldLight,
                                                fontSize = 14.sp
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Column {
                                            Text(
                                                text = sign.nameGeez,
                                                fontWeight = FontWeight.Bold,
                                                color = TextParchment,
                                                fontSize = 15.sp
                                            )
                                            Text(
                                                text = "${sign.nameAmharic} • ${sign.element}",
                                                color = TextParchmentDim,
                                                fontSize = 12.sp
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
}

@Composable
fun ZodiacResultCard(
    result: com.example.data.ZodiacCalculationResult,
    onSpeak: (String) -> Unit
) {
    val sign = result.sign
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
                        text = "የኮከብ ቀመር ውጤት (ቁጥር ቊ ${result.remainderMod12})",
                        style = MaterialTheme.typography.labelMedium.copy(color = GoldLight)
                    )
                    Text(
                        text = "${sign.nameGeez} (${sign.nameAmharic})",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                }

                FilledTonalIconButton(
                    onClick = {
                        val speech = "${sign.nameGeez}። ንጥረ ነገር፡ ${sign.element}። ጠባይ፡ ${sign.personality}። ${sign.ancientPrayer}"
                        onSpeak(speech)
                    },
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    )
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Speak")
                }
            }

            // Calculation Breakdown Pill
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text("የጠያቂ ሂሳብ፡ ${result.seekerScore}", fontSize = 11.sp, color = TextParchmentDim)
                Text("+", fontSize = 11.sp, color = GoldLight)
                Text("የእናት ሂሳብ፡ ${result.motherScore}", fontSize = 11.sp, color = TextParchmentDim)
                Text("=", fontSize = 11.sp, color = GoldLight)
                Text("ድምር፡ ${result.totalScore}", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GoldLight)
            }

            // Quick Stats Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoTag("ንጥረ ነገር", sign.element, Modifier.weight(1f))
                InfoTag("ጠባቂ መልአክ", sign.angel.substringBefore(" "), Modifier.weight(1f))
                InfoTag("የከበረ ድንጋይ", sign.gemStone, Modifier.weight(1f))
            }

            Divider(color = DarkCardBorder)

            SectionText("የባሕርይ ትንታኔ", sign.personality)
            SectionText("የዕጣ ፈንታና የሀብት ትንበያ", sign.destinyAndWealth)
            SectionText("የጤና ማሳሰቢያ", sign.healthAdvice)
            SectionText("የትዳርና ፍቅር ስምምነት", sign.loveCompatibility)

            // Ancient Prayer Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(10.dp))
                    .border(1.dp, GoldDark, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "ጥንታዊ የኮከብ ጸሎት (ልሳነ ግእዝ)",
                        style = MaterialTheme.typography.labelSmall.copy(color = GoldLight, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sign.ancientPrayer,
                        style = MaterialTheme.typography.bodySmall.copy(color = TextParchment, lineHeight = 18.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun ZodiacDetailView(sign: ZodiacSign, onSpeak: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
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
                        text = sign.nameGeez,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                    Text(
                        text = "${sign.nameAmharic} • ${sign.element}",
                        color = TextParchmentDim,
                        fontSize = 13.sp
                    )
                }

                IconButton(
                    onClick = {
                        val speech = "${sign.nameGeez}። ${sign.personality}። ${sign.ancientPrayer}"
                        onSpeak(speech)
                    }
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Read Aloud", tint = GoldLight)
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoTag("ገዢ ፕላኔት", sign.planet, Modifier.weight(1f))
                InfoTag("መልአክ", sign.angel.substringBefore(" "), Modifier.weight(1f))
                InfoTag("የሚስማማ ቀን", sign.luckyDay.substringBefore(" "), Modifier.weight(1f))
            }

            Divider(color = DarkCardBorder)

            SectionText("የባሕርይ ትንታኔ", sign.personality)
            SectionText("የዕጣ ፈንታና የሀብት ትንበያ", sign.destinyAndWealth)
            SectionText("የጤና ማሳሰቢያ", sign.healthAdvice)
            SectionText("የትዳር ስምምነት", sign.loveCompatibility)
            SectionText("የሚስማማው ቀለም", sign.luckyColor)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(10.dp))
                    .border(1.dp, GoldDark, RoundedCornerShape(10.dp))
                    .padding(12.dp)
            ) {
                Column {
                    Text(
                        text = "ጥንታዊ የጸሎት ቃል (ልሳነ ግእዝ)",
                        style = MaterialTheme.typography.labelSmall.copy(color = GoldLight, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = sign.ancientPrayer,
                        style = MaterialTheme.typography.bodySmall.copy(color = TextParchment, lineHeight = 18.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoTag(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(DarkBackground, RoundedCornerShape(8.dp))
            .border(1.dp, DarkCardBorder, RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = label, fontSize = 10.sp, color = TextParchmentDim)
        Text(text = value, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = GoldLight)
    }
}

@Composable
fun SectionText(title: String, content: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = GoldLight,
                fontSize = 13.sp
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TextParchment,
                lineHeight = 18.sp
            )
        )
    }
}
