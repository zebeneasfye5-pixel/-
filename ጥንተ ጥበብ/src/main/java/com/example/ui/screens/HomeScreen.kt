package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppLanguage
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.components.FreeDownloadBanner
import com.example.ui.components.FreeDownloadDialog
import com.example.ui.components.PublishStatusCard
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    val profile by viewModel.userProfile.collectAsState()
    var showDownloadDialog by remember { mutableStateOf(false) }

    if (showDownloadDialog) {
        FreeDownloadDialog(onDismissRequest = { showDownloadDialog = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.2f))
                                .border(1.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.MenuBook, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(18.dp))
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = LanguageManager.getString("app_title", lang),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary,
                                    fontSize = 15.sp
                                )
                            )
                            Text(
                                text = LanguageManager.getString("link_name", lang),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextParchmentDim,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                },
                actions = {
                    Button(
                        onClick = { showDownloadDialog = true },
                        colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary, contentColor = DarkBackground),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.testTag("btn_top_download_free")
                    ) {
                        Icon(Icons.Default.Download, contentDescription = null, tint = DarkBackground, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("አፑን አውርዱ", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    var showLangMenu by remember { mutableStateOf(false) }
                    IconButton(onClick = { showLangMenu = true }) {
                        Icon(Icons.Default.Language, contentDescription = "Language", tint = GoldPrimary)
                    }
                    DropdownMenu(
                        expanded = showLangMenu,
                        onDismissRequest = { showLangMenu = false },
                        modifier = Modifier.background(DarkSurface)
                    ) {
                        AppLanguage.values().forEach { l ->
                            DropdownMenuItem(
                                text = { Text(l.displayName, color = TextParchment) },
                                onClick = {
                                    viewModel.setLanguage(l.code)
                                    showLangMenu = false
                                }
                            )
                        }
                    }

                    IconButton(onClick = { viewModel.navigateTo(Screen.PROFILE) }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = GoldLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBackground)
            )
        },
        containerColor = DarkBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(2.dp))
                FreeDownloadBanner(onDownloadClick = { showDownloadDialog = true })
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().height(140.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "ሰላም፣ ${profile?.fullName ?: "ተጠቃሚ"}",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = GoldLight)
                        )
                        Text(
                            text = "የጥንታዊ አባቶቻችን ሚስጥራት፣ ሄኖክ፣ አቡሻህርና የፈውስ ጥበባት",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextParchment)
                        )
                    }
                }
            }

            item {
                PublishStatusCard()
            }

            item {
                Text(
                    text = "የጥንት መጻሕፍትና የቀመር ማውጫዎች",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = GoldPrimary)
                )
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        HomeCard("ኮከብ ቆጠራ", "በአቡሻህር ቀመር", Icons.Default.Brightness7, GoldPrimary, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.ZODIAC)
                        }
                        HomeCard("ባህረ ሐሳብ", "የፀሐይና ጨረቃ ቀመር", Icons.Default.CalendarMonth, CrimsonSecondary, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.BAHIRE_HASAB)
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        HomeCard("መስተፋቅር", "የፍቅርና ስምምነት ቃል", Icons.Default.Favorite, CrimsonLight, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.LOVE_HARMONY)
                        }
                        HomeCard("የፈውስ ዕፀዋት", "ዳማከሴ፣ ጤናአዳም፣ ግራዋ", Icons.Default.LocalFlorist, EmeraldAccent, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.HEALING)
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        HomeCard("የጥበብ ጠያቂ", "መፍትሔ ሥራይና በረከት", Icons.Default.Psychology, GoldLight, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.ORACLE)
                        }
                        HomeCard("መጽሐፈ ሄኖክ", "ሰባቱ ሰማያትና ደጆች", Icons.Default.AutoStories, BronzeTertiary, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.ENOCH)
                        }
                    }

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        HomeCard("ክፍያና ባንክ", "ቴሌብርና ንግድ ባንክ", Icons.Default.AccountBalance, GoldPrimary, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.PAYMENT)
                        }
                        HomeCard("ማህደር", "የተመዘገቡ ጥበባት", Icons.Default.Bookmark, TextParchmentDim, Modifier.weight(1f)) {
                            viewModel.navigateTo(Screen.HISTORY)
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
fun HomeCard(title: String, subtitle: String, icon: ImageVector, color: Color, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.height(130.dp).clickable(onClick = onClick).border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(14.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Box(
                modifier = Modifier.size(36.dp).clip(RoundedCornerShape(8.dp)).background(color.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
            }
            Column {
                Text(title, fontWeight = FontWeight.Bold, color = TextParchment, fontSize = 14.sp)
                Text(subtitle, color = TextParchmentDim, fontSize = 11.sp, maxLines = 1)
            }
        }
    }
}
