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
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BahireHasabScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val hasab by viewModel.bahireHasabResult.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var yearText by remember { mutableStateOf("2017") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_bahire_hasab", lang),
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
            // Year Selector Card
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
                            text = "የዓመተ ምሕረት ቀመር መምረጫ",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = yearText,
                                onValueChange = { yearText = it },
                                label = { Text("ዓመተ ምሕረት (ዓ/ም)") },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_hasab_year"),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = GoldPrimary,
                                    unfocusedBorderColor = DarkCardBorder,
                                    focusedTextColor = TextParchment,
                                    unfocusedTextColor = TextParchment
                                )
                            )

                            Button(
                                onClick = {
                                    val y = yearText.toIntOrNull() ?: 2017
                                    viewModel.calculateBahireHasab(y)
                                },
                                modifier = Modifier
                                    .height(52.dp)
                                    .testTag("btn_calculate_hasab"),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = GoldPrimary,
                                    contentColor = DarkBackground
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("ቀመር አስላ", fontWeight = FontWeight.Bold)
                            }
                        }

                        // Quick year buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf(2016, 2017, 2018, 2019).forEach { y ->
                                FilterChip(
                                    selected = yearText == y.toString(),
                                    onClick = {
                                        yearText = y.toString()
                                        viewModel.calculateBahireHasab(y)
                                    },
                                    label = { Text("$y ዓ/ም", fontSize = 12.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = GoldPrimary,
                                        selectedLabelColor = DarkBackground,
                                        containerColor = DarkSurfaceVariant,
                                        labelColor = TextParchment
                                    )
                                )
                            }
                        }
                    }
                }
            }

            // Results Card
            item {
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
                                    text = "ባህረ ሐሳብ ዘዓመተ ${hasab.year}",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = GoldLight
                                    )
                                )
                                Text(
                                    text = "ወንጌላዊ፡ ${hasab.evangelist} • ${hasab.season.substringBefore(" -")}",
                                    color = TextParchment,
                                    fontSize = 13.sp
                                )
                            }

                            FilledTonalIconButton(
                                onClick = {
                                    val text = "ዓመተ ${hasab.year}። ወንጌላዊ ${hasab.evangelist}። መጥቅዕ ${hasab.metqe}። ጾመ ነነዌ ${hasab.neneweDate}። ፋሲካ ${hasab.fasikaDate}።"
                                    viewModel.speak(text)
                                },
                                colors = IconButtonDefaults.filledTonalIconButtonColors(
                                    containerColor = GoldPrimary,
                                    contentColor = DarkBackground
                                )
                            ) {
                                Icon(Icons.Default.VolumeUp, contentDescription = "Speak")
                            }
                        }

                        // Grid of Metqe, Abaqte, Wenber
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            InfoTag("ወንበር (Wenber)", "${hasab.wenber}", Modifier.weight(1f))
                            InfoTag("አበቅቴ (Abaqte)", "${hasab.abaqte}", Modifier.weight(1f))
                            InfoTag("መጥቅዕ (Metqe)", "${hasab.metqe}", Modifier.weight(1f))
                        }

                        Divider(color = DarkCardBorder)

                        Text(
                            text = "ተንቀሳቃሽ አጽዋማትና በዓላት (Movable Feasts):",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )

                        FeastRow("ጾመ ነነዌ (Fast of Nineveh)", hasab.neneweDate)
                        FeastRow("ዓቢይ ጾም (Great Lent)", hasab.lentDate)
                        FeastRow("ደብረ ዘይት", hasab.debreZeytDate)
                        FeastRow("ሆሣዕና (Hosanna)", hasab.hosannaDate)
                        FeastRow("ስቅለት (Good Friday)", hasab.goodFridayDate)
                        FeastRow("ትንሣኤ / ፋሲካ (Easter)", hasab.fasikaDate, isHighlight = true)
                        FeastRow("ዕርገት (Ascension)", hasab.ergetAscensionDate)
                        FeastRow("ጰራቅሊጦስ (Pentecost)", hasab.pentecostDate)

                        Divider(color = DarkCardBorder)

                        Text(
                            text = "የፀሐይና ጨረቃ ዑደት (Luminaries):",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "• ${hasab.lunarPhase}",
                            fontSize = 12.sp,
                            color = TextParchment
                        )
                        Text(
                            text = "• የፀሐይ ዑደት ዓመት፡ ${hasab.solarCycleYear} ከ ፳፰ ዓመታት ውስጥ",
                            fontSize = 12.sp,
                            color = TextParchment
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(DarkBackground, RoundedCornerShape(10.dp))
                                .border(1.dp, DarkCardBorder, RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = hasab.ancientSolarEquationNote,
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
        }
    }
}

@Composable
fun FeastRow(name: String, date: String, isHighlight: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            fontSize = 13.sp,
            color = if (isHighlight) GoldLight else TextParchment,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = date,
            fontSize = 13.sp,
            color = if (isHighlight) GoldPrimary else TextParchmentDim,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium
        )
    }
}
