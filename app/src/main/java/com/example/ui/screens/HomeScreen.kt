package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.AppLanguage
import com.example.data.LanguageManager
import com.example.ui.MainViewModel
import com.example.ui.Screen
import com.example.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    val context = LocalContext.current
    val lang by viewModel.selectedLanguage.collectAsState()
    val profile by viewModel.userProfile.collectAsState()
    var isDownloading by remember { mutableStateOf(false) }
    var downloadProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            downloadProgress = 0f
            while (downloadProgress < 1f) {
                kotlinx.coroutines.delay(200)
                downloadProgress += 0.2f
            }
            isDownloading = false
            Toast.makeText(context, "«ጥንተ ጥበብ» ወደስልክዎ ወርዷል (APK Saved)", Toast.LENGTH_LONG).show()
        }
    }

    var showDownloadDialog by remember { mutableStateOf(false) }

    if (showDownloadDialog) {
        com.example.ui.components.FreeDownloadDialog(
            onDismissRequest = { showDownloadDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(GoldPrimary.copy(alpha = 0.2f))
                                .border(1.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = LanguageManager.getString("app_title", lang),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldPrimary,
                                    fontSize = 16.sp
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
                    // Prominent Side-by-Side "አፑን አውርዱ" Command Button
                    Button(
                        onClick = { showDownloadDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = GoldPrimary,
                            contentColor = DarkBackground
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.testTag("btn_top_download_free")
                    ) {
                        Icon(
                            Icons.Default.Download,
                            contentDescription = null,
                            tint = DarkBackground,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "አፑን አውርዱ",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }

                    // Language picker icon
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

                    // Profile / History button
                    IconButton(
                        onClick = { viewModel.navigateTo(Screen.PROFILE) },
                        modifier = Modifier.testTag("btn_top_profile")
                    ) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = GoldLight)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkBackground,
                    titleContentColor = GoldPrimary
                )
            )
        },
        containerColor = DarkBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Prominent Side-by-Side Free Download Banner right at the top
            item {
                Spacer(modifier = Modifier.height(2.dp))
                com.example.ui.components.FreeDownloadBanner(
                    onDownloadClick = { showDownloadDialog = true }
                )
            }
            item {
                Spacer(modifier = Modifier.height(4.dp))
                // Hero Banner
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(18.dp))
                        .border(1.dp, DarkCardBorder, RoundedCornerShape(18.dp)),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = R.drawable.img_hero_manuscript),
                            contentDescription = "Ancient Ethiopian Manuscript",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            DarkBackground.copy(alpha = 0.85f),
                                            DarkBackground
                                        )
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            val userName = profile?.fullName ?: "ተጠቃሚ"
                            Text(
                                text = "ሰላም፣ $userName",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = GoldLight
                                )
                            )
                            Text(
                                text = "የጥንታዊ አባቶቻችን ሚስጥራት፣ ሄኖክ፣ አቡሻህርና የፈውስ ጥበባት",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextParchment
                                )
                            )
                        }
                    }
                }
            }

            // Credits & Status Bar
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = DarkSurface),
                    shape = RoundedCornerShape(14.dp),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = Brush.horizontalGradient(listOf(GoldDark, DarkCardBorder))
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.Stars,
                                contentDescription = null,
                                tint = GoldPrimary,
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = if (profile?.isPremium == true) "የጥበብ ዘለቄታ (VIP)" else "ቀሪ ፈቃዶች (Credits)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextParchmentDim
                                )
                                Text(
                                    text = if (profile?.isPremium == true) "ያልተገደበ (Unlimited)" else "${profile?.credits ?: 0} ጊዜ",
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = GoldLight
                                )
                            }
                        }

                        Button(
                            onClick = { viewModel.navigateTo(Screen.PAYMENT) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GoldPrimary,
                                contentColor = DarkBackground
                            ),
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp),
                            modifier = Modifier.testTag("btn_home_recharge")
                        ) {
                            Text("ሞላ / ባንክ", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        }
                    }
                }
            }

            // Download & Share Link Card: "የአባቶቻችን እውቀት"
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, CrimsonSecondary.copy(alpha = 0.6f), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Download,
                                    contentDescription = null,
                                    tint = GoldLight,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = "የአባቶቻችን እውቀት (ጥንተ ጥበብ)",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = GoldLight
                                        )
                                    )
                                    Text(
                                        text = "ድህረ-ገጽና መተግበሪያ ወደ ስልክዎ አውርደው ይጠቀሙ",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextParchmentDim
                                        )
                                    )
                                }
                            }

                            AssistChip(
                                onClick = { },
                                label = { Text("4.9 ★", fontSize = 11.sp, color = GoldLight) },
                                colors = AssistChipDefaults.assistChipColors(containerColor = DarkSurface)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        AnimatedVisibility(visible = isDownloading) {
                            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                                LinearProgressIndicator(
                                    progress = { downloadProgress },
                                    modifier = Modifier.fillMaxWidth(),
                                    color = GoldPrimary,
                                    trackColor = DarkCardBorder
                                )
                                Text(
                                    text = "መተግበሪያውን በማውረድ ላይ... ${(downloadProgress * 100).toInt()}%",
                                    fontSize = 11.sp,
                                    color = TextParchmentDim,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilledTonalButton(
                                onClick = { showDownloadDialog = true },
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("btn_download_app"),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = GoldPrimary,
                                    contentColor = DarkBackground
                                )
                            ) {
                                Icon(Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("አፑን በነፃ አውርድ (Free)", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }

                            OutlinedButton(
                                onClick = {
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "የአባቶቻችን እውቀት — ጥንተ ጥበብ መተግበሪያንና ድህረ-ገጽን ከዚህ ሊንክ አውርደው ይጠቀሙ፡ https://tinte-tibeb.et"
                                        )
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "የአባቶቻችን እውቀት አጋራ"))
                                },
                                modifier = Modifier.testTag("btn_share_link"),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldLight),
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(GoldPrimary)
                                )
                            ) {
                                Icon(Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("አጋራ", fontSize = 12.sp)
                            }

                            IconButton(
                                onClick = {
                                    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                    clipboard.setPrimaryClip(ClipData.newPlainText("Link", "https://tinte-tibeb.et"))
                                    Toast.makeText(context, "ሊንኩ ተገልብጧል (Link Copied)", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.testTag("btn_copy_link")
                            ) {
                                Icon(Icons.Default.ContentCopy, contentDescription = "Copy Link", tint = TextParchment)
                            }
                        }
                    }
                }
            }

            // Live Publishing Status & Free Public Link Card
            item {
                com.example.ui.components.PublishStatusCard()
            }

            // Main Core Features Title
            item {
                Text(
                    text = "የጥንት መጻሕፍትና የቀመር ማውጫዎች",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary
                    ),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Grid of ancient manuscript capabilities
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    FeatureRow(
                        item1 = FeatureItem(
                            title = LanguageManager.getString("nav_zodiac", lang),
                            subtitle = "በአቡሻህር ቀመር የኮከብ፣ ዕጣ ፈንታና ባሕርይ ምርምር",
                            icon = Icons.Default.Brightness7,
                            color = GoldPrimary,
                            onClick = { viewModel.navigateTo(Screen.ZODIAC) },
                            tag = "card_zodiac"
                        ),
                        item2 = FeatureItem(
                            title = LanguageManager.getString("nav_bahire_hasab", lang),
                            subtitle = "የፀሐይና ጨረቃ ቀመር፣ ዓመተ ምሕረት፣ አበቅቴና በዓላት",
                            icon = Icons.Default.CalendarMonth,
                            color = CrimsonSecondary,
                            onClick = { viewModel.navigateTo(Screen.BAHIRE_HASAB) },
                            tag = "card_bahire_hasab"
                        )
                    )

                    FeatureRow(
                        item1 = FeatureItem(
                            title = LanguageManager.getString("nav_love", lang),
                            subtitle = "የፍቅር፣ የእርቅና የስምምነት ጥንታዊ የመጽሐፍ ቃላት",
                            icon = Icons.Default.Favorite,
                            color = CrimsonLight,
                            onClick = { viewModel.navigateTo(Screen.LOVE_HARMONY) },
                            tag = "card_love"
                        ),
                        item2 = FeatureItem(
                            title = LanguageManager.getString("nav_healing", lang),
                            subtitle = "ዳማከሴ፣ ጤናአዳም፣ ግራዋና የፈውስ ዕፀዋት መጽሐፍ",
                            icon = Icons.Default.LocalFlorist,
                            color = EmeraldAccent,
                            onClick = { viewModel.navigateTo(Screen.HEALING) },
                            tag = "card_healing"
                        )
                    )

                    FeatureRow(
                        item1 = FeatureItem(
                            title = LanguageManager.getString("nav_oracle", lang),
                            subtitle = "መፍትሔ ሥራይ፣ ዓይነ ጥላ፣ በረከትና ጥናት ጠያቂ",
                            icon = Icons.Default.Psychology,
                            color = GoldLight,
                            onClick = { viewModel.navigateTo(Screen.ORACLE) },
                            tag = "card_oracle"
                        ),
                        item2 = FeatureItem(
                            title = LanguageManager.getString("nav_enoch", lang),
                            subtitle = "ሰባቱ ሰማያት፣ ፮ቱ የፀሐይ ደጆችና የሄኖክ ሚስጥራት",
                            icon = Icons.Default.AutoStories,
                            color = BronzeTertiary,
                            onClick = { viewModel.navigateTo(Screen.ENOCH) },
                            tag = "card_enoch"
                        )
                    )

                    FeatureRow(
                        item1 = FeatureItem(
                            title = LanguageManager.getString("nav_payment", lang),
                            subtitle = "ቴሌብር፣ ንግድ ባንክ፣ አዋሽና የገቢ ማግኛ ሥርዓት",
                            icon = Icons.Default.AccountBalance,
                            color = GoldPrimary,
                            onClick = { viewModel.navigateTo(Screen.PAYMENT) },
                            tag = "card_payment"
                        ),
                        item2 = FeatureItem(
                            title = "የተመዘገቡ ጥበባት",
                            subtitle = "የቀደሙ ጥያቄዎችና የተመረጡ ጸሎቶች ማህደር",
                            icon = Icons.Default.Bookmark,
                            color = TextParchmentDim,
                            onClick = { viewModel.navigateTo(Screen.HISTORY) },
                            tag = "card_history"
                        )
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

data class FeatureItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit,
    val tag: String
)

@Composable
fun FeatureRow(item1: FeatureItem, item2: FeatureItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FeatureCard(item = item1, modifier = Modifier.weight(1f))
        FeatureCard(item = item2, modifier = Modifier.weight(1f))
    }
}

@Composable
fun FeatureCard(item: FeatureItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .height(150.dp)
            .clickable(onClick = item.onClick)
            .testTag(item.tag)
            .border(1.dp, DarkCardBorder, RoundedCornerShape(14.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(item.color.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = null,
                    tint = item.color,
                    modifier = Modifier.size(24.dp)
                )
            }

            Column {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextParchment
                    ),
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = item.subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextParchmentDim,
                        fontSize = 11.sp,
                        lineHeight = 14.sp
                    ),
                    maxLines = 2
                )
            }
        }
    }
}
