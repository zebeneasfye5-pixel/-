package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.components.FreeDownloadBanner
import com.example.ui.components.FreeDownloadDialog
import com.example.ui.components.PublishStatusCard
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    val profile by viewModel.userProfile.collectAsState()
    val selectedLang by viewModel.selectedLanguage.collectAsState()
    var showDownloadDialog by remember { mutableStateOf(false) }

    if (showDownloadDialog) {
        FreeDownloadDialog(onDismissRequest = { showDownloadDialog = false })
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("መገለጫና ቅንብሮች", fontWeight = FontWeight.Bold, color = GoldPrimary) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_profile")) {
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
                    modifier = Modifier.fillMaxWidth().border(1.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.2f))
                                .border(2.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(36.dp))
                        }

                        Text(
                            profile?.fullName ?: "ተጠቃሚ",
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            fontSize = 18.sp
                        )

                        Text(
                            "ስልክ፡ ${profile?.phoneNumber ?: "ያልተመዘገበ"} | ፆታ፡ ${profile?.gender ?: "ያልተመረጠ"}",
                            color = TextParchment,
                            fontSize = 13.sp
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Badge(containerColor = EmeraldAccent.copy(alpha = 0.2f)) {
                                Text("የተረጋገጠ አባል", color = EmeraldAccent, fontSize = 11.sp, modifier = Modifier.padding(4.dp))
                            }
                            Badge(containerColor = GoldPrimary.copy(alpha = 0.2f)) {
                                Text("${profile?.credits ?: 0} ነጥቦች", color = GoldPrimary, fontSize = 11.sp, modifier = Modifier.padding(4.dp))
                            }
                        }
                    }
                }
            }

            item {
                FreeDownloadBanner(onDownloadClick = { showDownloadDialog = true })
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("የመተግበሪያው ቋንቋ ይምረጡ፡", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)

                        AppLanguage.values().forEach { lang ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = selectedLang == lang.code,
                                        onClick = { viewModel.setLanguage(lang.code) },
                                        colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                    )
                                    Text("${lang.displayName} (${lang.script})", color = TextParchment, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }

            item {
                PublishStatusCard()
            }

            item {
                Button(
                    onClick = { viewModel.navigateTo(Screen.REGISTRATION) },
                    modifier = Modifier.fillMaxWidth().testTag("btn_edit_profile"),
                    colors = ButtonDefaults.buttonColors(containerColor = DarkSurfaceVariant)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = TextParchment)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("መረጃ እንደገና አዘምን (ይለፍ ቃል ቀይር)", color = TextParchment)
                }
            }
        }
    }
}
