package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

const val SHARED_APP_URL = "https://ais-pre-qhu5ws6marzeodwyzcpcy5-125744783365.europe-west1.run.app"

@Composable
fun PublishStatusCard(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(
                1.5.dp,
                Brush.horizontalGradient(listOf(EmeraldAccent, GoldPrimary)),
                RoundedCornerShape(16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header with Live Indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(EmeraldAccent)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "በቀጥታ በነፃ ፐብሊሽ ተደርጓል (Live Online)",
                        fontWeight = FontWeight.Bold,
                        color = EmeraldAccent,
                        fontSize = 13.sp
                    )
                }

                AssistChip(
                    onClick = { },
                    label = {
                        Text(
                            "100% ነፃ (Free)",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkBackground
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(containerColor = GoldPrimary)
                )
            }

            Text(
                text = "የአባቶቻችን እውቀት — «ጥንተ ጥበብ» አፕሊኬሽንና ድህረ-ገጽ ያለ ምንም ክፍያ በነፃ ለህዝብ ክፍት ሆኖ ተስተናግዷል። ማንኛውም ሰው ሊንኩን በመክፈት በቀጥታ ሊጠቀምበትና አፑን ሊያወርድ ይችላል።",
                fontSize = 12.sp,
                color = TextParchment,
                lineHeight = 17.sp
            )

            // Published URL Box
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkBackground),
                shape = RoundedCornerShape(10.dp),
                border = CardDefaults.outlinedCardBorder().copy(
                    brush = androidx.compose.ui.graphics.SolidColor(DarkCardBorder)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "የቀጥታ መክፈቻ ሊንክ (Public Live URL):",
                            fontSize = 10.sp,
                            color = TextParchmentDim
                        )
                        Text(
                            text = SHARED_APP_URL,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = GoldLight,
                            maxLines = 1
                        )
                    }

                    IconButton(
                        onClick = {
                            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            clipboard.setPrimaryClip(ClipData.newPlainText("Published App URL", SHARED_APP_URL))
                            Toast.makeText(context, "የቀጥታ ፐብሊሽ ሊንኩ ተገልብጧል (Link Copied)!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.testTag("btn_copy_published_url")
                    ) {
                        Icon(Icons.Default.ContentCopy, contentDescription = "Copy Link", tint = GoldPrimary)
                    }
                }
            }

            // Quick Actions: Open Browser & Share
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        try {
                            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(SHARED_APP_URL))
                            context.startActivity(browserIntent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "ሊንኩን መክፈት አልተቻለም", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("btn_open_live_app"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = DarkBackground
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("ሊንኩን ክፈት", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                OutlinedButton(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "የአባቶቻችን እውቀት — «ጥንተ ጥበብ» ጥንታዊ መጻሕፍትና የቀመር መተግበሪያ በነፃ በዚህ ሊንክ ተከፍቷል፡ $SHARED_APP_URL"
                            )
                        }
                        context.startActivity(Intent.createChooser(shareIntent, "የአባቶቻችን እውቀት አጋራ"))
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .testTag("btn_share_published_app"),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(GoldPrimary)
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("ለሰው አጋራ", fontSize = 12.sp)
                }
            }

            // Technical Verification Bullets
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkSurfaceVariant, RoundedCornerShape(8.dp))
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "✓ 24/7 የክላውድ ሆስቲንግ (Cloud Run) ያለ ምንም ተጨማሪ ክፍያ ነፃ ነው",
                    fontSize = 11.sp,
                    color = TextParchment
                )
                Text(
                    text = "✓ የAPK / AAB ፋይል ወደ ጉግል ፕሌይ ስቶር (Google Play) ለመጫን ዝግጁ ነው",
                    fontSize = 11.sp,
                    color = TextParchment
                )
                Text(
                    text = "✓ ወደ ጊትሃብ (GitHub) ለመላክና በZIP ለማውረድ በሴቲንግ ይቻላል",
                    fontSize = 11.sp,
                    color = TextParchment
                )
            }
        }
    }
}
