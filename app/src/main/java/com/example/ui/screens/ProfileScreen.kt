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
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: MainViewModel) {
    val profile by viewModel.userProfile.collectAsState()
    val lang by viewModel.selectedLanguage.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "የተጠቃሚ መገለጫ",
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }) {
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.2f))
                                .border(2.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(40.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = profile?.fullName ?: "የጥንተ ጥበብ ተጠቃሚ",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = GoldLight
                        )

                        Text(
                            text = profile?.phoneNumber ?: "",
                            fontSize = 13.sp,
                            color = TextParchmentDim
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        AssistChip(
                            onClick = { },
                            label = {
                                Text(
                                    if (profile?.isPremium == true) "የጥበብ ዘለቄታ (VIP Member)" else "መደበኛ ተጠቃሚ (${profile?.credits ?: 0} ፈቃዶች)",
                                    fontSize = 12.sp,
                                    color = if (profile?.isPremium == true) GoldLight else TextParchment
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Stars,
                                    contentDescription = null,
                                    tint = if (profile?.isPremium == true) GoldLight else GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(containerColor = DarkSurfaceVariant)
                        )
                    }
                }
            }

            // Language Selection Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "የመተግበሪያው ቋንቋ (App Language):",
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            AppLanguage.values().forEach { l ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    RadioButton(
                                        selected = lang == l.code,
                                        onClick = { viewModel.setLanguage(l.code) },
                                        colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "${l.displayName} (${l.script})",
                                        color = TextParchment,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Live Publishing Status & Free Public Link Card
            item {
                com.example.ui.components.PublishStatusCard()
            }

            // Action Buttons
            item {
                Button(
                    onClick = { viewModel.navigateTo(Screen.PAYMENT) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_profile_payment"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.AccountBalance, contentDescription = null, tint = DarkBackground)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("ፈቃድ ይሙሉ / ባንክ ይክፈሉ", fontWeight = FontWeight.Bold)
                }
            }

            item {
                OutlinedButton(
                    onClick = { viewModel.navigateTo(Screen.REGISTRATION) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_profile_switch_account"),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = TextParchmentDim),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.SwapHoriz, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("መለያ ይቀይሩ / እንደገና ይመዝገቡ")
                }
            }
        }
    }
}
