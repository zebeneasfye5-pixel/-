package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BankOption
import com.example.data.LanguageManager
import com.example.data.PricingPackage
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val lang by viewModel.selectedLanguage.collectAsState()
    val transactions by viewModel.transactions.collectAsState()

    val repo = viewModel.paymentRepo
    var selectedPackage by remember { mutableStateOf(repo.packages[1]) } // Default to Monthly package
    var selectedBank by remember { mutableStateOf(repo.banks[0]) } // Default to Telebirr
    var txReferenceInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = LanguageManager.getString("nav_payment", lang),
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
            // Packages Selection
            item {
                Text(
                    text = "፩. የጥበብ ፈቃድ ፓኬጅ ይምረጡ:",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    repo.packages.forEach { pkg ->
                        val isSelected = selectedPackage.id == pkg.id
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedPackage = pkg }
                                .border(
                                    1.5.dp,
                                    if (isSelected) GoldPrimary else DarkCardBorder,
                                    RoundedCornerShape(12.dp)
                                ),
                            colors = CardDefaults.cardColors(
                                containerColor = if (isSelected) GoldPrimary.copy(alpha = 0.15f) else DarkSurface
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = isSelected,
                                        onClick = { selectedPackage = pkg },
                                        colors = RadioButtonDefaults.colors(selectedColor = GoldPrimary)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = pkg.title,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) GoldLight else TextParchment,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = pkg.subtitle,
                                            color = TextParchmentDim,
                                            fontSize = 11.sp
                                        )
                                    }
                                }

                                Text(
                                    text = "${pkg.priceBirr.toInt()} ብር",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = GoldPrimary
                                )
                            }
                        }
                    }
                }
            }

            // Bank Selection
            item {
                Text(
                    text = "፪. የሚከፍሉበትን ባንክ ይምረጡ:",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(repo.banks) { bank ->
                        val isSelected = selectedBank.id == bank.id
                        FilterChip(
                            selected = isSelected,
                            onClick = { selectedBank = bank },
                            label = { Text(bank.name, fontSize = 12.sp) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(bank.iconColorHex),
                                selectedLabelColor = Color.White,
                                containerColor = DarkSurface,
                                labelColor = TextParchment
                            )
                        )
                    }
                }
            }

            // Bank Account Details Box
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, GoldDark, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = selectedBank.name,
                                fontWeight = FontWeight.Bold,
                                color = GoldLight,
                                fontSize = 16.sp
                            )
                            AssistChip(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("Account", selectedBank.accountNumber))
                                    Toast.makeText(context, "የሂሳብ ቁጥሩ ተገልብጧል", Toast.LENGTH_SHORT).show()
                                },
                                label = { Text("ቁጥር ኮፒ", fontSize = 11.sp) },
                                leadingIcon = {
                                    Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp))
                                }
                            )
                        }

                        Text(
                            text = "የሂሳብ ቁጥር / ስልክ፡ ${selectedBank.accountNumber}",
                            fontWeight = FontWeight.Bold,
                            color = TextParchment,
                            fontSize = 15.sp
                        )

                        Text(
                            text = "የሂሳብ ስም፡ ${selectedBank.accountHolder}",
                            color = TextParchmentDim,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "የሚከፈለው ጠቅላላ ሂሳብ፡ ${selectedPackage.priceBirr.toInt()} ብር",
                            color = GoldPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        Divider(color = DarkCardBorder)

                        OutlinedTextField(
                            value = txReferenceInput,
                            onValueChange = { txReferenceInput = it },
                            label = { Text("የደረሰኝ / ትራንዛክሽን ማመሳከሪያ ቁጥር") },
                            placeholder = { Text("ለምሳሌ፡ TB-9823412 หรือ FT234...", color = TextParchmentDim) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_tx_ref"),
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
                                viewModel.makePayment(selectedBank, selectedPackage, txReferenceInput)
                                txReferenceInput = ""
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("btn_confirm_payment"),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = DarkBackground)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("ክፍያውን አረጋግጥና አስገባ", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Transaction History
            if (transactions.isNotEmpty()) {
                item {
                    Text(
                        text = "የተከናወኑ የክፍያ ደረሰኞች",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = GoldLight
                        )
                    )
                }

                items(transactions) { tx ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, DarkCardBorder, RoundedCornerShape(10.dp)),
                        colors = CardDefaults.cardColors(containerColor = DarkSurface),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = tx.packageType,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    color = TextParchment
                                )
                                Text(
                                    text = "${tx.bankName} • ${tx.transactionRef}",
                                    fontSize = 11.sp,
                                    color = TextParchmentDim
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "${tx.amountBirr.toInt()} ብር",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    color = GoldLight
                                )
                                Text(
                                    text = "ተረጋግጧል ✓",
                                    fontSize = 10.sp,
                                    color = EmeraldAccent
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
