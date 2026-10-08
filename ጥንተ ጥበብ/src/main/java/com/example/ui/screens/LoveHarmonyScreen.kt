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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LoveHarmonyInput
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoveHarmonyScreen(viewModel: MainViewModel) {
    val result by viewModel.loveHarmonyResult.collectAsState()
    var seekerName by remember { mutableStateOf("") }
    var seekerMotherName by remember { mutableStateOf("") }
    var targetName by remember { mutableStateOf("") }
    var targetMotherName by remember { mutableStateOf("") }
    var intentionType by remember { mutableStateOf("የትዳር ሰላምና ፍቅር") }

    val intentions = listOf(
        "የትዳር ሰላምና ፍቅር",
        "የልብ ስምምነትና መግባባት",
        "የተጣላ ማስታረቅ",
        "የፍቅር መስህብ (መስተፋቅር)"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("መስተፋቅርና የፍቅር ቀመር", fontWeight = FontWeight.Bold, color = GoldPrimary)
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("btn_back_love")
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = GoldLight)
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
            verticalArrangement = Arrangement.spacedBy(14.dp)
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
                            "የጥንቱ መስተፋቅር መረጃ ማሟያ (ስም + የእናት ስም)",
                            color = GoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            "ጥንታዊ መጻሕፍት እንደሚያስተምሩት፣ ፍጹም የፍቅርና የስምምነት ቀመር ለማውጣት የሁለቱም ወገኖች ስምና የእናት ስም በግእዝ አበገደ ቀመር ይሰላል።",
                            color = TextParchmentDim,
                            fontSize = 12.sp
                        )

                        OutlinedTextField(
                            value = seekerName,
                            onValueChange = { seekerName = it },
                            label = { Text("የጠያቂው ስም (ለምሳሌ፡ ሰሎሞን)") },
                            modifier = Modifier.fillMaxWidth().testTag("input_seeker_name"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = seekerMotherName,
                            onValueChange = { seekerMotherName = it },
                            label = { Text("የጠያቂው እናት ስም (ለምሳሌ፡ ወለተ ማርያም)") },
                            modifier = Modifier.fillMaxWidth().testTag("input_seeker_mother"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = targetName,
                            onValueChange = { targetName = it },
                            label = { Text("የተፈላጊው/ዋ ስም (ለምሳሌ፡ ሳባ)") },
                            modifier = Modifier.fillMaxWidth().testTag("input_target_name"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = targetMotherName,
                            onValueChange = { targetMotherName = it },
                            label = { Text("የተፈላጊው/ዋ እናት ስም (ለምሳሌ፡ ወለተ ጊዮርጊስ)") },
                            modifier = Modifier.fillMaxWidth().testTag("input_target_mother"),
                            singleLine = true
                        )

                        Text("የጥያቄው ዓላማ፡", color = TextParchment, fontSize = 13.sp, fontWeight = FontWeight.Bold)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            intentions.take(2).forEach { intent ->
                                FilterChip(
                                    selected = intentionType == intent,
                                    onClick = { intentionType = intent },
                                    label = { Text(intent, fontSize = 11.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = GoldPrimary,
                                        selectedLabelColor = DarkBackground
                                    )
                                )
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
                                        intentionType = intentionType,
                                        birthDay = "1"
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("btn_calculate_love"),
                            colors = ButtonDefaults.buttonColors(containerColor = CrimsonSecondary)
                        ) {
                            Icon(Icons.Default.Favorite, contentDescription = null, tint = TextParchment)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("የመስተፋቅር ቀመር አውጣ", fontWeight = FontWeight.Bold, color = TextParchment)
                        }
                    }
                }
            }

            result?.let { res ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "የስምምነት ውጤት፡ ${res.compatibilityPercentage}%",
                                    color = GoldPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )

                                IconButton(
                                    onClick = { viewModel.speak(res.audioScript) },
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(GoldPrimary.copy(alpha = 0.2f))
                                        .testTag("btn_speak_love")
                                ) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Speak", tint = GoldPrimary)
                                }
                            }

                            Text(
                                "የከዋክብት ንጽጽር፡ ${res.seekerZodiac.nameAmharic} (${res.seekerZodiac.element}) ↔ ${res.targetZodiac.nameAmharic} (${res.targetZodiac.element})",
                                color = TextParchment,
                                fontSize = 13.sp
                            )

                            Text(
                                res.elementHarmony,
                                color = GoldLight,
                                fontSize = 13.sp
                            )

                            Divider(color = DarkCardBorder)

                            Text(
                                "የመጽሐፉ የግእዝ ቀመር ቃል (የጸሎት አስማት)፡",
                                color = CrimsonLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )

                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(8.dp),
                                color = DarkBackground
                            ) {
                                Text(
                                    text = res.geezFormulaText,
                                    modifier = Modifier.padding(12.dp),
                                    color = TextParchment,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp
                                )
                            }

                            Text(
                                "ትርጓሜው፡",
                                color = GoldLight,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            Text(
                                text = res.amharicMeaning,
                                color = TextParchment,
                                fontSize = 12.sp
                            )

                            Text(
                                "የሚያስፈልጉ የተፈጥሮ ነገሮች (ዕፀዋትና ሽቶ)፡",
                                color = EmeraldAccent,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                            res.naturalMaterialsNeeded.forEach { item ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = EmeraldAccent, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(item, color = TextParchment, fontSize = 12.sp)
                                }
                            }

                            Text(
                                "ምክርና አተገባበር፡ ${res.sacredAdvisory}",
                                color = TextParchmentDim,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
