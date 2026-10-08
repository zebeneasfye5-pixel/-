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
import com.example.data.OracleEngine
import com.example.data.OracleQuery
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OracleScreen(viewModel: MainViewModel) {
    val response by viewModel.oracleResponse.collectAsState()
    var selectedCategory by remember { mutableStateOf(OracleEngine.categories.first().first) }
    var seekerName by remember { mutableStateOf("") }
    var motherName by remember { mutableStateOf("") }
    var detailsOrTarget by remember { mutableStateOf("") }
    var specificNeed by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("የጥበብ ጠያቂና መፍትሔ ሥራይ", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_oracle")) {
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
                        Text(
                            "የመጻሕፍት የጥበብ ማውጫ ምድብ ይምረጡ፡",
                            color = GoldLight,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        OracleEngine.categories.forEach { (catName, catDesc) ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                RadioButton(
                                    selected = selectedCategory == catName,
                                    onClick = { selectedCategory = catName },
                                    colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                )
                                Column {
                                    Text(catName, color = TextParchment, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(catDesc, color = TextParchmentDim, fontSize = 11.sp)
                                }
                            }
                        }

                        Divider(color = DarkCardBorder)

                        Text("የጥያቄው አስፈላጊ መረጃዎች፡", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                        OutlinedTextField(
                            value = seekerName,
                            onValueChange = { seekerName = it },
                            label = { Text("የጠያቂው ስም") },
                            modifier = Modifier.fillMaxWidth().testTag("input_oracle_seeker"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = motherName,
                            onValueChange = { motherName = it },
                            label = { Text("የእናት ስም") },
                            modifier = Modifier.fillMaxWidth().testTag("input_oracle_mother"),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = detailsOrTarget,
                            onValueChange = { detailsOrTarget = it },
                            label = { Text("የተፈላጊው ሰው ስም ወይም የነገሩ ስም") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = specificNeed,
                            onValueChange = { specificNeed = it },
                            label = { Text("ልዩ ጉዳይ ወይም ጥያቄዎን ይግለጹ") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3
                        )

                        Button(
                            onClick = {
                                viewModel.consultOracle(
                                    OracleQuery(
                                        category = selectedCategory,
                                        seekerName = seekerName,
                                        motherName = motherName,
                                        detailsOrTarget = detailsOrTarget,
                                        specificNeed = specificNeed
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth().testTag("btn_consult_oracle"),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                        ) {
                            Icon(Icons.Default.Psychology, contentDescription = null, tint = DarkBackground)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("ጥበቡን ከመጽሐፉ ጠይቅ", fontWeight = FontWeight.Bold, color = DarkBackground)
                        }
                    }
                }
            }

            response?.let { res ->
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().border(1.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "የጥበቡ መልስና አስማተ ቀመር",
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary,
                                    fontSize = 17.sp
                                )
                                IconButton(
                                    onClick = { viewModel.speak(res.voiceNarrative) },
                                    modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(GoldPrimary.copy(alpha = 0.2f)).testTag("btn_speak_oracle")
                                ) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Speak", tint = GoldPrimary)
                                }
                            }

                            Text(
                                "ጠያቂ፡ ${res.seekerName} | እናት፡ ${res.motherName} | ኮከብ፡ ${res.zodiacResult.sign.nameAmharic}",
                                color = TextParchment,
                                fontSize = 12.sp
                            )

                            Text(
                                "የመጽሐፉ የግእዝ ድርሳን/አስማት፡",
                                fontWeight = FontWeight.Bold,
                                color = CrimsonLight,
                                fontSize = 13.sp
                            )

                            Surface(color = DarkBackground, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = res.geezScripture,
                                    modifier = Modifier.padding(12.dp),
                                    color = TextParchment,
                                    fontSize = 13.sp,
                                    lineHeight = 20.sp
                                )
                            }

                            Text("ትርጓሜው፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 13.sp)
                            Text(res.amharicExplanation, color = TextParchment, fontSize = 12.sp)

                            Text("የሚታዘዙ ቅመሞችና ተግባራት፡", fontWeight = FontWeight.Bold, color = EmeraldAccent, fontSize = 13.sp)
                            res.sacredPrescription.forEach { item ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.BrightnessAuto, contentDescription = null, tint = EmeraldAccent, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(item, color = TextParchment, fontSize = 12.sp)
                                }
                            }

                            Text("ምቹ ሰዓትና አቅጣጫ፡ ${res.auspiciousTiming}", color = TextParchmentDim, fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}
