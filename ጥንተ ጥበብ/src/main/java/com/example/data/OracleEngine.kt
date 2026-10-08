package com.example.data

data class OracleQuery(
    val category: String,
    val seekerName: String,
    val motherName: String,
    val detailsOrTarget: String,
    val specificNeed: String
)

data class OracleResponse(
    val category: String,
    val seekerName: String,
    val motherName: String,
    val zodiacResult: ZodiacCalculationResult,
    val geezScripture: String,
    val amharicExplanation: String,
    val sacredPrescription: List<String>,
    val telsemSymbol: String,
    val auspiciousTiming: String,
    val voiceNarrative: String
)

object OracleEngine {
    val categories = listOf(
        "መስተፋቅርና ፍቅር" to "የፍቅር፣ የትዳር ሰላምና የልብ ስምምነት ጥበብ",
        "መፍትሔ ሥራይ" to "ከዓይነ ጥላ፣ ከድግምትና ከክፉ መንፈስ መከላከያ",
        "ሀብትና ንግድ" to "የበረከት፣ የሥራ ስኬትና የሀብት ማውጫ",
        "ዕውቀትና ጥናት" to "የአእምሮ ብሩህነት፣ የትምህርትና የማስታወስ ጥበብ",
        "ፈውስና ጤና" to "የሥጋና የነፍስ ደዌ መፈወሻ ዕፀዋት",
        "የመንገድ ሰላም" to "የጉዞ፣ የባህር ማዶና የፈተና መከላከያ"
    )

    fun consultOracle(query: OracleQuery): OracleResponse {
        val zodiac = ZodiacEngine.calculateZodiac(query.seekerName, query.motherName)
        val sName = query.seekerName.ifBlank { "ገብረ እግዚአብሔር" }
        val mName = query.motherName.ifBlank { "ወለተ ማርያም" }
        val target = query.detailsOrTarget.ifBlank { "ፍቅርና ሰላም" }

        val geez = """
            በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
            አስማተ ሰሎሞን ወዳዊት፤
            አልፋ ወዖሜጋ፣ ኤልሻዳይ፣ ጸባኦት፤
            ይትፈታሕ ኩሉ ዕንቅፋት ወይሠረር ሰላም ዲበ $sName ወልደ $mName፤
            ይኩን ንጹሐ ከመ ብርሃነ ፀሐይ።
        """.trimIndent()

        val amharic = "በ$sName ላይ ያለው የጭንቀትና የፈተና ደመና ሁሉ በእግዚአብሔር ቃልና በቅዱሳን መላእክት ጥበቃ ይወገድ፤ ሰላምና በረከት ይንገሥ።"
        val prescription = listOf(
            "በዳማከሴና በጤናአዳም እንፋሎት ለ፫ ተከታታይ ቀናት መታጠን",
            "በቤት ውስጥ ከርቤና ዕጣን ማጨስ",
            "ምጽዋት ለተቸገረ ሰው በዕለተ ሰንበት መስጠት"
        )
        val symbol = "፠ ☩ ✡ ሰላም ወበረከት ✡ ☩ ፠"
        val timing = "በዕለተ ${zodiac.sign.luckyDay} ማለዳ ፀሐይ ስትወጣ"
        val audio = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። ጸሎተ በረከት ወሰላም ለ$sName ወልደ $mName።"

        return OracleResponse(
            category = query.category,
            seekerName = sName,
            motherName = mName,
            zodiacResult = zodiac,
            geezScripture = geez,
            amharicExplanation = amharic,
            sacredPrescription = prescription,
            telsemSymbol = symbol,
            auspiciousTiming = timing,
            voiceNarrative = audio
        )
    }
}
