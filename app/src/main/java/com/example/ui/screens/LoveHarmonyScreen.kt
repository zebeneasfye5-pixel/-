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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageManager
import com.example.data.LoveHarmonyInput
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoveHarmonyScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val loveResult by viewModel.loveHarmonyResult.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var seekerName by remember { mutableStateOf("") }
    var seekerMotherName by remember { mutableStateOf("") }
    var targetName by remember { mutableStateOf("") }
    var targetMotherName by remember { mutableStateOf("") }
    var intention by remember { mutableStateOf("የትዳር ሰላምና ፍቅር") }
    var birthDay by remember { mutableStateOf("ዕለተ ሰኞ") }

    val intentions = listOf(
        "የትዳር ሰላምና ፍቅር",
        "የተጣሉትን ማስታረቅና ይቅርታ",
        "የልብ መስህብና መተሳሰብ",
        "የቤተሰብ አንድነት"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_love", lang),
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, CrimsonSecondary.copy(alpha = 0.5f), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Favorite, contentDescription = null, tint = CrimsonLight)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "የመስተፋቅርና የስምምነት አስፈላጊ መረጃዎች",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldLight
                                )
                            )
                        }
                        Text(
                            text = "በጥንታዊው መጽሐፍ መሠረት፤ የፍቅርና የስምምነት ቃለ-ቀመር ለመስራት የሁለቱ ሰዎች ስም፣ የእናቶቻቸው ስምና ዓላማቸው ተለይቶ መታወቅ አለበት፡",
                            fontSize = 12.sp,
                            color = TextParchmentDim
                        )

                        OutlinedTextField(
                            value = seekerName,
                            onValueChange = { seekerName = it },
                            label = { Text("የጠያቂው ሙሉ ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_love_seeker"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = seekerMotherName,
                            onValueChange = { seekerMotherName = it },
                            label = { Text("የጠያቂው እናት ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_love_seeker_mother"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = targetName,
                            onValueChange = { targetName = it },
                            label = { Text("የፍላጎቱ / የትዳር አጋር ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_love_target"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = targetMotherName,
                            onValueChange = { targetMotherName = it },
                            label = { Text("የአጋሩ እናት ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_love_target_mother"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        Text(
                            text = "የስምምነቱ ዓላማ (Intention):",
                            fontSize = 12.sp,
                            color = GoldLight,
                            fontWeight = FontWeight.SemiBold
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            intentions.forEach { itn ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    RadioButton(
                                        selected = intention == itn,
                                        onClick = { intention = itn },
                                        colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                    )
                                    Text(
                                        text = itn,
                                        color = TextParchment,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                viewModel.calculateLoveHarmony(
                                    LoveHarmonyInput(
                                        seekerName = seekerName,
                                        seekerMotherName = seekerMotherName,
                                        targetName = targetName,
                                        targetMotherName = targetMotherName,
                                        intentionType = intention,
                                        birthDay = birthDay
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("btn_calculate_love"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = CrimsonSecondary,
                                contentColor = TextParchment
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.AutoAwesome, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("መስተፋቅሩንና ቀመሩን አውጣ", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            loveResult?.let { res ->
                item {
                    LoveResultCard(
                        result = res,
                        onSpeak = { text -> viewModel.speak(text) }
                    )
                }
            }
        }
    }
}

@Composable
fun LoveResultCard(
    result: com.example.data.LoveHarmonyResult,
    onSpeak: (String) -> Unit
) {
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
                        text = "የኮከብና የፍቅር ስምምነት ሚዛን",
                        style = MaterialTheme.typography.labelSmall.copy(color = GoldLight)
                    )
                    Text(
                        text = "${result.compatibilityPercentage}% ስምምነት",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                }

                FilledTonalIconButton(
                    onClick = { onSpeak(result.audioScript) },
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    )
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Play Audio")
                }
            }

            Text(
                text = result.elementHarmony,
                style = MaterialTheme.typography.bodySmall.copy(color = TextParchment)
            )

            Divider(color = DarkCardBorder)

            // Ancient Ge'ez Formula
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(12.dp))
                    .border(1.dp, CrimsonSecondary, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = "ትክክለኛው የመስተፋቅርና የስምምነት ቃል (ልሳነ ግእዝ)",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = CrimsonLight,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = result.geezFormulaText,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = GoldLight,
                            lineHeight = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            // Amharic Meaning
            SectionText("የቃሉ ትርጓሜ", result.amharicMeaning)

            // Materials
            Column {
                Text(
                    text = "የሚያስፈልጉ የተፈጥሮ ዕቃዎች (Materials Needed):",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight,
                        fontSize = 13.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                result.naturalMaterialsNeeded.forEach { item ->
                    Text(
                        text = "• $item",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextParchment)
                    )
                }
            }

            SectionText("የሚደገምበት ሰዓትና አቅጣጫ", result.timingAndDirection)

            // Sacred Covenant
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurface, RoundedCornerShape(8.dp))
                    .border(1.dp, DarkCardBorder, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            ) {
                Text(
                    text = result.sacredAdvisory,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextParchmentDim,
                        fontSize = 11.sp,
                        lineHeight = 16.sp
                    )
                )
            }
        }
    }
}
