package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.LanguageManager
import com.example.data.OracleEngine
import com.example.data.OracleQuery
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OracleScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val response by viewModel.oracleResponse.collectAsState()
    val isSpeaking by viewModel.voiceManager.isSpeaking.collectAsState()

    var selectedCategory by remember { mutableStateOf("መፍትሔ ሥራይ") }
    var seekerName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }
    var details by remember { mutableStateOf("") }
    var specificNeed by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_oracle", lang),
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
                        .border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "የጥበብ መጠይቅ ማውጫ (Manuscript Consultation)",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldLight
                            )
                        )
                        Text(
                            text = "ለመጠየቅ የፈለጉትን የጥበብ ዘርፍ ይምረጡና አስፈላጊውን መረጃ ያስገቡ፡",
                            fontSize = 12.sp,
                            color = TextParchmentDim
                        )

                        // Category Selector
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            OracleEngine.categories.forEach { (cat, desc) ->
                                val isSelected = selectedCategory == cat
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedCategory = cat }
                                        .border(
                                            1.dp,
                                            if (isSelected) GoldPrimary else DarkCardBorder,
                                            RoundedCornerShape(10.dp)
                                        ),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (isSelected) GoldPrimary.copy(alpha = 0.15f) else DarkBackground
                                    ),
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = isSelected,
                                            onClick = { selectedCategory = cat },
                                            colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Column {
                                            Text(
                                                text = cat,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 13.sp,
                                                color = if (isSelected) GoldLight else TextParchment
                                            )
                                            Text(
                                                text = desc,
                                                fontSize = 11.sp,
                                                color = TextParchmentDim
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Divider(color = DarkCardBorder)

                        OutlinedTextField(
                            value = seekerName,
                            onValueChange = { seekerName = it },
                            label = { Text("የጠያቂው ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_oracle_seeker"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = motherName,
                            onValueChange = { motherName = it },
                            label = { Text("የእናት ስም (ለቀመር አስፈላጊ)") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_oracle_mother"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = details,
                            onValueChange = { details = it },
                            label = { Text("ተጨማሪ ዝርዝር / የፍላጎት ወይም የሰው ስም") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_oracle_target"),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        OutlinedTextField(
                            value = specificNeed,
                            onValueChange = { specificNeed = it },
                            label = { Text("የተለየ ጥያቄ ወይም ጭንቀት ካለዎት እዚህ ይግለጹ") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_oracle_need"),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = GoldPrimary,
                                unfocusedBorderColor = DarkCardBorder,
                                focusedTextColor = TextParchment,
                                unfocusedTextColor = TextParchment
                            )
                        )

                        Button(
                            onClick = {
                                viewModel.consultOracle(
                                    OracleQuery(
                                        category = selectedCategory,
                                        seekerName = seekerName,
                                        motherName = motherName,
                                        detailsOrTarget = details,
                                        specificNeed = specificNeed
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("btn_consult_oracle"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.AutoStories, contentDescription = null, tint = DarkBackground)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("የመጽሐፉን መልስ ጠይቅ (Consult Oracle)", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            response?.let { res ->
                item {
                    OracleResultCard(
                        response = res,
                        onSpeak = { text -> viewModel.speak(text) }
                    )
                }
            }
        }
    }
}

@Composable
fun OracleResultCard(
    response: com.example.data.OracleResponse,
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
                        text = "የመጽሐፉ ውሳኔና ቃል",
                        style = MaterialTheme.typography.labelSmall.copy(color = GoldLight)
                    )
                    Text(
                        text = response.category,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldPrimary
                        )
                    )
                }

                FilledTonalIconButton(
                    onClick = { onSpeak(response.voiceNarrative) },
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    )
                ) {
                    Icon(Icons.Default.VolumeUp, contentDescription = "Play Audio")
                }
            }

            Text(
                text = "ለ፡ ${response.seekerName} (እናት፡ ${response.motherName}) • ኮከብ፡ ${response.zodiacResult.sign.nameGeez}",
                fontSize = 12.sp,
                color = TextParchmentDim
            )

            // Telsem Seal Badge
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(8.dp))
                    .border(1.dp, GoldDark, RoundedCornerShape(8.dp))
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = response.telsemSymbol,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldLight,
                    letterSpacing = 2.sp
                )
            }

            // Sacred Ge'ez Text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkBackground, RoundedCornerShape(12.dp))
                    .border(1.dp, CrimsonSecondary, RoundedCornerShape(12.dp))
                    .padding(14.dp)
            ) {
                Column {
                    Text(
                        text = "የጥንቱ መጽሐፍ ቃል (ልሳነ ግእዝ)",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = CrimsonLight,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = response.geezScripture,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = GoldLight,
                            lineHeight = 20.sp
                        )
                    )
                }
            }

            SectionText("የቃሉ ፍቺና ትርጓሜ", response.amharicExplanation)

            // Prescriptions
            Column {
                Text(
                    text = "የሚደረጉ መፍትሔዎችና የቀመር ማዘዣዎች:",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight,
                        fontSize = 13.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                response.sacredPrescription.forEach { item ->
                    Text(
                        text = "• $item",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextParchment)
                    )
                }
            }

            SectionText("የተመደበለት ሰዓትና ዕለት", response.auspiciousTiming)
        }
    }
}
