package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AppLanguage
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(viewModel: MainViewModel) {
    val lang by viewModel.selectedLanguage.collectAsState()
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("ወንድ") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var showDownloadDialog by remember { mutableStateOf(false) }

    if (showDownloadDialog) {
        com.example.ui.components.FreeDownloadDialog(
            onDismissRequest = { showDownloadDialog = false }
        )
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(scrollState)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        // Side-by-side Link and Free Download Banner
        com.example.ui.components.FreeDownloadBanner(
            onDownloadClick = { showDownloadDialog = true }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Language Switcher Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AppLanguage.values().forEach { appLang ->
                FilterChip(
                    selected = lang == appLang.code,
                    onClick = { viewModel.setLanguage(appLang.code) },
                    label = { Text(appLang.displayName, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = GoldPrimary,
                        selectedLabelColor = DarkBackground,
                        containerColor = DarkSurfaceVariant,
                        labelColor = TextParchment
                    ),
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Header Crest
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(40.dp))
                .background(GoldPrimary.copy(alpha = 0.15f))
                .border(2.dp, GoldPrimary, RoundedCornerShape(40.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.AutoStories,
                contentDescription = "Emblem",
                tint = GoldPrimary,
                modifier = Modifier.size(44.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = LanguageManager.getString("app_title", lang),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = GoldPrimary,
                letterSpacing = 1.5.sp
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = LanguageManager.getString("app_subtitle", lang),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = TextParchmentDim
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = LanguageManager.getString("register_title", lang),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = GoldLight
                    )
                )

                // Full Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = { fullName = it },
                    label = { Text(LanguageManager.getString("full_name", lang)) },
                    leadingIcon = {
                        Icon(Icons.Default.Person, contentDescription = null, tint = GoldPrimary)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_fullname"),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = DarkCardBorder,
                        focusedLabelColor = GoldPrimary,
                        unfocusedLabelColor = TextParchmentDim,
                        focusedTextColor = TextParchment,
                        unfocusedTextColor = TextParchment
                    )
                )

                // Phone Number
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(LanguageManager.getString("phone_number", lang)) },
                    leadingIcon = {
                        Icon(Icons.Default.Phone, contentDescription = null, tint = GoldPrimary)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_phone"),
                    singleLine = true,
                    placeholder = { Text("09... ወይም 07...", color = TextParchmentDim) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = DarkCardBorder,
                        focusedLabelColor = GoldPrimary,
                        unfocusedLabelColor = TextParchmentDim,
                        focusedTextColor = TextParchment,
                        unfocusedTextColor = TextParchment
                    )
                )

                // Gender Selection
                Text(
                    text = LanguageManager.getString("gender", lang),
                    color = TextParchmentDim,
                    style = MaterialTheme.typography.bodySmall
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val genders = listOf("ወንድ (Male)", "ሴት (Female)")
                    genders.forEach { g ->
                        val isSelected = selectedGender.startsWith(g.take(2))
                        OutlinedButton(
                            onClick = { selectedGender = g.take(2) },
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .testTag("btn_gender_${g.take(2)}"),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) GoldPrimary.copy(alpha = 0.2f) else Color.Transparent,
                                contentColor = if (isSelected) GoldLight else TextParchment
                            ),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) GoldPrimary else DarkCardBorder)
                            )
                        ) {
                            Text(g, fontSize = 13.sp)
                        }
                    }
                }

                // Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(LanguageManager.getString("password", lang)) },
                    leadingIcon = {
                        Icon(Icons.Default.Lock, contentDescription = null, tint = GoldPrimary)
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                tint = GoldPrimary
                            )
                        }
                    },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_password"),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = DarkCardBorder,
                        focusedLabelColor = GoldPrimary,
                        unfocusedLabelColor = TextParchmentDim,
                        focusedTextColor = TextParchment,
                        unfocusedTextColor = TextParchment
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        viewModel.registerUser(fullName, phone, selectedGender, password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("btn_register_submit"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.VpnKey, contentDescription = null, tint = DarkBackground)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = LanguageManager.getString("confirm", lang),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "«ጥበብ እምወርቅ ወእምብሩር ትከብር» — ጥበበ ሰሎሞን",
            style = MaterialTheme.typography.bodySmall.copy(
                color = GoldDark,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            ),
            textAlign = TextAlign.Center
        )
    }
}
