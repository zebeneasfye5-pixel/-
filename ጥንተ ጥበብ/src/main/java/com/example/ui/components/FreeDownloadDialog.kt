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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun FreeDownloadDialog(onDismissRequest: () -> Unit) {
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
            Toast.makeText(context, "«ጥንተ ጥበብ» በነፃ ወደስልክዎ ወርዷል!", Toast.LENGTH_LONG).show()
        }
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier.fillMaxWidth().border(2.dp, GoldPrimary, RoundedCornerShape(20.dp)),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            shape = RoundedCornerShape(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AssistChip(
                        onClick = { },
                        label = { Text("100% በነፃ (Free)", color = DarkBackground, fontWeight = FontWeight.Bold, fontSize = 11.sp) },
                        colors = AssistChipDefaults.assistChipColors(containerColor = GoldPrimary)
                    )
                    IconButton(onClick = onDismissRequest) {
                        Icon(Icons.Default.Close, contentDescription = "Close", tint = TextParchmentDim)
                    }
                }

                Text(
                    text = "አፑን ያለምንም ክፍያ በነፃ ያውርዱ",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = GoldLight, fontSize = 18.sp),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "የአባቶቻችን እውቀት — «ጥንተ ጥበብ» አፕሊኬሽንን በቀጥታ ወደ ስልክዎ አውርደው ያለምንም ክፍያ በነፃ ይጠቀሙበት።",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextParchment, lineHeight = 18.sp),
                    textAlign = TextAlign.Center
                )

                if (isDownloading) {
                    LinearProgressIndicator(progress = { progress }, modifier = Modifier.fillMaxWidth(), color = GoldPrimary)
                }

                Button(
                    onClick = { if (!isDownloading) isDownloading = true },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = if (isCompleted) EmeraldAccent else GoldPrimary, contentColor = DarkBackground),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isCompleted) "በስልክዎ ላይ ተቀምጧል ✓" else "አፑን በነፃ አውርድ (Free Download)", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
