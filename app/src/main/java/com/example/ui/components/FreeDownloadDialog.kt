package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun FreeDownloadDialog(
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    var isDownloading by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }
    var isCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            progress = 0f
            while (progress < 1f) {
                delay(120)
                progress += 0.15f
            }
            progress = 1f
            isDownloading = false
            isCompleted = true
            Toast.makeText(
                context,
                "«ጥንተ ጥበብ» ያለምንም ክፍያ በነፃ ወደስልክዎ ወርዷል (Download Complete)!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(2.dp, GoldPrimary, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Top Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                "100% በነፃ (Free)",
                                color = DarkBackground,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(containerColor = GoldPrimary)
                    )

                    IconButton(onClick = onDismissRequest) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = TextParchmentDim)
                    }
                }

                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(GoldPrimary.copy(alpha = 0.15f), RoundedCornerShape(16.dp))
                        .border(1.5.dp, GoldPrimary, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.CheckCircle else Icons.Default.DownloadForOffline,
                        contentDescription = null,
                        tint = if (isCompleted) EmeraldAccent else GoldLight,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = "አፑን ያለምንም ክፍያ በነፃ ያውርዱ",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldLight,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "የአባቶቻችን እውቀት — «ጥንተ ጥበብ» መተግበሪያን በቀጥታ ወደ ስልክዎ አውርደው ያለምንም ክፍያ በነፃ ይጠቀሙበት።",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextParchment,
                        lineHeight = 18.sp
                    ),
                    textAlign = TextAlign.Center
                )

                // App Specs Box
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkBackground),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("የፋይል ስም፡", fontSize = 11.sp, color = TextParchmentDim)
                            Text("ጥንተ_ጥበብ_የአባቶች_እውቀት.apk", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = TextParchment)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("መጠን፡", fontSize = 11.sp, color = TextParchmentDim)
                            Text("18.4 MB (ቀላልና ፈጣን)", fontSize = 11.sp, color = GoldLight)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("ዋጋ፡", fontSize = 11.sp, color = TextParchmentDim)
                            Text("0.00 ብር (ሙሉ በሙሉ በነፃ)", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = EmeraldAccent)
                        }
                    }
                }

                if (isDownloading) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        LinearProgressIndicator(
                            progress = { progress },
                            modifier = Modifier.fillMaxWidth(),
                            color = GoldPrimary,
                            trackColor = DarkCardBorder
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("በማውረድ ላይ...", fontSize = 11.sp, color = TextParchmentDim)
                            Text("${(progress * 100).toInt()}%", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GoldLight)
                        }
                    }
                }

                // Main Download Button
                Button(
                    onClick = {
                        if (!isDownloading) {
                            isDownloading = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .testTag("btn_modal_download_free"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isCompleted) EmeraldAccent else GoldPrimary,
                        contentColor = DarkBackground
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = if (isCompleted) Icons.Default.Check else Icons.Default.Download,
                        contentDescription = null,
                        tint = DarkBackground
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isCompleted) "በስልክዎ ላይ ተቀምጧል ✓" else "አፑን በነፃ አውርድ (Free Download)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }

                // Copy Link & Share Actions
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText("Free Download Link", "https://tinte-tibeb.et/app-free-download.apk"))
                            Toast.makeText(context, "የነፃ ማውረጃ ሊንኩ ተገልብጧል (Link Copied)", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_modal_copy_link"),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(DarkCardBorder)
                        )
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("ሊንኩን ቅዳ", fontSize = 12.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "የአባቶቻችን እውቀት — «ጥንተ ጥበብ» መተግበሪያን ያለምንም ክፍያ በነፃ ከዚህ ሊንክ ያውርዱ፡ https://tinte-tibeb.et/app-free-download.apk"
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "የአባቶቻችን እውቀት አጋራ"))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("btn_modal_share"),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(DarkCardBorder)
                        )
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("አጋራ", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
