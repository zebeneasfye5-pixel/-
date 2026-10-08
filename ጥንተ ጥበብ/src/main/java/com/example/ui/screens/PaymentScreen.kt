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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BankOption
import com.example.data.PricingPackage
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(viewModel: MainViewModel) {
    val banks = viewModel.paymentRepo.banks
    val packages = viewModel.paymentRepo.packages
    val userProfile by viewModel.userProfile.collectAsState()

    var selectedBank by remember { mutableStateOf(banks.first()) }
    var selectedPackage by remember { mutableStateOf(packages[1]) }
    var transactionRef by remember { mutableStateOf("") }
    var showSuccessDialog by remember { mutableStateOf(false) }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog = false },
            title = { Text("ክፍያው ተረጋግጧል!", color = GoldPrimary, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "የመረጡት የጥበብ ፓኬጅ በስኬት ነቅቷል! ተጨማሪ ነጥቦች ወደ አካውንትዎ ገብተዋል። ለፈጣሪው ስላደረጉት ድጋፍ እናመሰግናለን።",
                    color = TextParchment
                )
            },
            confirmButton = {
                Button(
                    onClick = { showSuccessDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                ) {
                    Text("እሺ", color = DarkBackground)
                }
            },
            containerColor = DarkSurface
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ክፍያና የባንክ ድጋፍ", fontWeight = FontWeight.Bold, color = GoldPrimary) },
                navigationIcon = {
                    IconButton(onClick = { viewModel.navigateBack() }, modifier = Modifier.testTag("btn_back_payment")) {
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
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("ያለዎት ቀሪ የጥበብ ነጥብ (Credits)", color = TextParchmentDim, fontSize = 12.sp)
                            Text("${userProfile?.credits ?: 0} ነጥቦች", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = GoldPrimary)
                        }
                        Box(
                            modifier = Modifier.size(44.dp).clip(RoundedCornerShape(10.dp)).background(GoldPrimary.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Stars, contentDescription = null, tint = GoldPrimary)
                        }
                    }
                }
            }

            item {
                Text("1. የጥበብ ፓኬጅ ይምረጡ፡", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    packages.forEach { pkg ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { selectedPackage = pkg }.border(
                                width = if (selectedPackage.id == pkg.id) 2.dp else 1.dp,
                                color = if (selectedPackage.id == pkg.id) GoldPrimary else DarkCardBorder,
                                shape = RoundedCornerShape(12.dp)
                            ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (selectedPackage.id == pkg.id) DarkSurface.copy(alpha = 0.9f) else DarkSurface
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(pkg.title, fontWeight = FontWeight.Bold, color = TextParchment, fontSize = 14.sp)
                                    Text(pkg.subtitle, color = TextParchmentDim, fontSize = 11.sp)
                                }
                                Text("${pkg.priceBirr.toInt()} ብር", fontWeight = FontWeight.Bold, color = GoldPrimary, fontSize = 15.sp)
                            }
                        }
                    }
                }
            }

            item {
                Text("2. የክፍያ ባንክ ይምረጡ፡", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    banks.forEach { bank ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { selectedBank = bank }.border(
                                width = if (selectedBank.id == bank.id) 2.dp else 1.dp,
                                color = if (selectedBank.id == bank.id) Color(bank.iconColorHex) else DarkCardBorder,
                                shape = RoundedCornerShape(12.dp)
                            ),
                            colors = CardDefaults.cardColors(containerColor = DarkSurface),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(bank.name, fontWeight = FontWeight.Bold, color = TextParchment, fontSize = 14.sp)
                                    Text("የሂሳብ ቁጥር፡ ${bank.accountNumber}", color = GoldLight, fontSize = 12.sp)
                                    Text("ስም፡ ${bank.accountHolder} | USSD: ${bank.ussdCode}", color = TextParchmentDim, fontSize = 11.sp)
                                }
                                if (selectedBank.id == bank.id) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = GoldPrimary)
                                }
                            }
                        }
                    }
                }
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth().border(1.dp, DarkCardBorder, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("3. የክፍያ ማረጋገጫ ቁጥር (Transaction Ref)", color = GoldLight, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                        Text(
                            "በ${selectedBank.name} ወደ ${selectedBank.accountNumber} ${selectedPackage.priceBirr.toInt()} ብር ከላኩ በኋላ ከባንኩ የደረሰዎትን የማረጋገጫ ቁጥር ያስገቡ።",
                            color = TextParchmentDim,
                            fontSize = 11.sp
                        )

                        OutlinedTextField(
                            value = transactionRef,
                            onValueChange = { transactionRef = it },
                            label = { Text("ማረጋገጫ ቁጥር (ምሳሌ፡ FT24389XXX)") },
                            modifier = Modifier.fillMaxWidth().testTag("input_tx_ref"),
                            singleLine = true
                        )

                        Button(
                            onClick = {
                                viewModel.makePayment(selectedBank, selectedPackage, transactionRef)
                                showSuccessDialog = true
                            },
                            modifier = Modifier.fillMaxWidth().testTag("btn_submit_payment"),
                            colors = ButtonDefaults.buttonColors(containerColor = GoldPrimary)
                        ) {
                            Icon(Icons.Default.Verified, contentDescription = null, tint = DarkBackground)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("ክፍያውን አረጋግጥና ነጥብ ጨምር", fontWeight = FontWeight.Bold, color = DarkBackground)
                        }
                    }
                }
            }
        }
    }
}
