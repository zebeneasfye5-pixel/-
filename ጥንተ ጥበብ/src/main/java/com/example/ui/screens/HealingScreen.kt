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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.HealingHerb
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HealingScreen(viewModel: MainViewModel) {
    val query by viewModel.herbQuery.collectAsState()
    val herbs by viewModel.filteredHerbs.collectAsState()
    var selectedHerb by remember { mutableStateOf<HealingHerb?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("የፈውስ ዕፀዋትና መድኃኒት (መጽሐፈ ፈውስ)", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 16.sp) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_healing")) {
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
                OutlinedTextField(
                    value = query,
                    onValueChange = { viewModel.setHerbQuery(it) },
                    label = { Text("በበሽታ ዓይነት ወይም በዕፅ ስም ይፈልጉ (ለምሳሌ፡ ጉንፋን፣ ሆድ...)") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = GoldLight) },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setHerbQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear", tint = TextParchmentDim)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().testTag("input_herb_search"),
                    singleLine = true
                )
            }

            if (selectedHerb != null) {
                val herb = selectedHerb!!
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().border(1.5.dp, EmeraldAccent, RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(herb.nameAmharic, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = EmeraldAccent)
                                    Text("ግእዝ፡ ${herb.nameGeez} | ${herb.scientificName}", fontSize = 12.sp, color = GoldLight)
                                }
                                IconButton(
                                    onClick = {
                                        viewModel.speak("${herb.nameAmharic}። ${herb.preparationMethod}። ${herb.amharicPrayerTranslation}")
                                    },
                                    modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(EmeraldAccent.copy(alpha = 0.2f)).testTag("btn_speak_herb")
                                ) {
                                    Icon(Icons.Default.VolumeUp, contentDescription = "Speak", tint = EmeraldAccent)
                                }
                            }

                            Divider(color = DarkCardBorder)

                            Text("የሚፈውሳቸው ደዌዎች፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 13.sp)
                            herb.ailmentsTreated.forEach { ailment ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = EmeraldAccent, modifier = Modifier.size(14.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(ailment, color = TextParchment, fontSize = 12.sp)
                                }
                            }

                            Text("የአዘገጃጀት ቅደም ተከተል፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 13.sp)
                            Text(herb.preparationMethod, color = TextParchment, fontSize = 12.sp)

                            Text("የአጠቃቀም ዘዴ፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 13.sp)
                            Text(herb.applicationMethod, color = TextParchment, fontSize = 12.sp)

                            Text("የጥንቱ የግእዝ ጸሎት አስማተ ፈውስ፡", fontWeight = FontWeight.Bold, color = CrimsonLight, fontSize = 13.sp)
                            Surface(color = DarkBackground, shape = RoundedCornerShape(8.dp), modifier = Modifier.fillMaxWidth()) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(herb.ancientManuscriptPrayer, color = TextParchment, fontSize = 12.sp, lineHeight = 18.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text("ትርጉም፡ ${herb.amharicPrayerTranslation}", color = GoldLight, fontSize = 11.sp)
                                }
                            }

                            Text("ማስጠንቀቂያ፡ ${herb.safetyAdvisory}", color = CrimsonLight, fontSize = 11.sp)

                            Button(
                                onClick = { selectedHerb = null },
                                modifier = Modifier.align(Alignment.End),
                                colors = ButtonDefaults.buttonColors(containerColor = DarkSurface)
                            ) {
                                Text("ዝጋ", color = TextParchment)
                            }
                        }
                    }
                }
            }

            items(herbs) { herb ->
                Card(
                    modifier = Modifier.fillMaxWidth().clickable { selectedHerb = herb }.border(1.dp, DarkCardBorder, RoundedCornerShape(12.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(herb.nameAmharic, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                            Text("ግእዝ፡ ${herb.nameGeez}", fontSize = 12.sp, color = TextParchmentDim)
                            Text(herb.ailmentsTreated.joinToString(", "), fontSize = 11.sp, color = TextParchment, maxLines = 1)
                        }
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = GoldLight)
                    }
                }
            }
        }
    }
}
