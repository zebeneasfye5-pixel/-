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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GlobalGrimoireItem
import com.example.data.GlobalGrimoiresEngine
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalGrimoiresScreen(viewModel: MainViewModel) {
    var selectedItem by remember { mutableStateOf<GlobalGrimoireItem?>(GlobalGrimoiresEngine.grimoires.first()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ዓለም አቀፍ ጥንታውያን ድግምቶች",
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        fontSize = 16.sp
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.navigateBack() },
                        modifier = Modifier.testTag("btn_back_global_grimoires")
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
                        .border(1.dp, CrimsonSecondary, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Warning,
                            contentDescription = null,
                            tint = CrimsonLight,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                "የጥንታውያን የዓለም መጻሕፍት ማዕከል",
                                fontWeight = FontWeight.Bold,
                                color = GoldPrimary,
                                fontSize = 13.sp
                            )
                            Text(
                                "በዓለማችን ታሪክ እጅግ ኃይለኛና አደገኛ የሚባሉ የሰሎሞን፣ የግብፅ፣ የግሪክና የአረብ ግሪሞየሮች ትክክለኛ ቃላትና የአሰራር መመሪያዎች።",
                                color = TextParchment,
                                fontSize = 11.sp,
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            item {
                Text(
                    "መጽሐፍ ይምረጡ፡",
                    fontWeight = FontWeight.Bold,
                    color = GoldLight,
                    fontSize = 13.sp
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    GlobalGrimoiresEngine.grimoires.forEach { item ->
                        val isSelected = selectedItem?.id == item.id
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedItem = item }
                                .border(
                                    if (isSelected) 1.5.dp else 1.dp,
                                    if (isSelected) GoldPrimary else DarkCardBorder,
                                    RoundedCornerShape(12.dp)
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) DarkSurfaceVariant else DarkSurface
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        item.titleAmharic,
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) GoldPrimary else TextParchment,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        "${item.titleOriginal} | ${item.originEra}",
                                        fontSize = 11.sp,
                                        color = TextParchmentDim
                                    )
                                }
                                Surface(
                                    color = CrimsonSecondary.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        "አደገኛ ጥበብ",
                                        color = CrimsonLight,
                                        fontSize = 10.sp,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            selectedItem?.let { grimoire ->
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
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
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        grimoire.titleAmharic,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldPrimary
                                    )
                                    Text(
                                        grimoire.titleOriginal,
                                        fontSize = 12.sp,
                                        color = TextParchmentDim
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        viewModel.speak(
                                            "${grimoire.titleAmharic}። ${grimoire.primaryPurpose}። ${grimoire.originalIncantation}። ትርጉም፡ ${grimoire.amharicTranslation}"
                                        )
                                    },
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(GoldPrimary.copy(alpha = 0.2f))
                                        .testTag("btn_speak_grimoire")
                                ) {
                                    Icon(
                                        Icons.Default.VolumeUp,
                                        contentDescription = "Speak",
                                        tint = GoldPrimary
                                    )
                                }
                            }

                            Divider(color = DarkCardBorder)

                            Surface(
                                color = DarkBackground,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        grimoire.sacredSeal,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = GoldLight
                                    )
                                }
                            }

                            Column {
                                Text("የመጽሐፉ መነሻና ዘመን፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 12.sp)
                                Text(grimoire.originEra, color = TextParchment, fontSize = 12.sp)
                                Text("ቋንቋ፡ ${grimoire.originalLanguage}", color = TextParchmentDim, fontSize = 11.sp)
                            }

                            Column {
                                Text("ዋነኛ ዓላማና ተግባር፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 12.sp)
                                Text(grimoire.primaryPurpose, color = TextParchment, fontSize = 12.sp)
                            }

                            Surface(
                                color = DarkBackground,
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, DarkCardBorder, RoundedCornerShape(10.dp))
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        "የጥንቱ ትክክለኛ የአስማት ቃል (Original Incantation)፡",
                                        fontWeight = FontWeight.Bold,
                                        color = CrimsonLight,
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        grimoire.originalIncantation,
                                        color = GoldLight,
                                        fontSize = 13.sp,
                                        lineHeight = 19.sp,
                                        fontFamily = FontFamily.Serif
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        "የአማርኛ ትርጉም፡",
                                        fontWeight = FontWeight.Bold,
                                        color = TextParchmentDim,
                                        fontSize = 11.sp
                                    )
                                    Text(
                                        grimoire.amharicTranslation,
                                        color = TextParchment,
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp
                                    )
                                }
                            }

                            Text("የታዘዘው የአሰራር ሥርዓትና ቅመሞች፡", fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 12.sp)
                            grimoire.ritualInstructions.forEach { step ->
                                Row(verticalAlignment = Alignment.Top) {
                                    Text("• ", color = GoldPrimary, fontSize = 14.sp)
                                    Text(step, color = TextParchment, fontSize = 12.sp, lineHeight = 17.sp)
                                }
                            }

                            Surface(
                                color = CrimsonSecondary.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        "⚠️ ጥብቅ ማስጠንቀቂያ፡",
                                        fontWeight = FontWeight.Bold,
                                        color = CrimsonLight,
                                        fontSize = 12.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        grimoire.protectiveWarning,
                                        color = TextParchment,
                                        fontSize = 11.sp,
                                        lineHeight = 16.sp
                                    )
                                }
                            }

                            Column {
                                Text("ታሪካዊ ማብራሪያ፡", fontWeight = FontWeight.Bold, color = TextParchmentDim, fontSize = 11.sp)
                                Text(grimoire.historicalContext, color = TextParchmentDim, fontSize = 11.sp, lineHeight = 16.sp)
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}
