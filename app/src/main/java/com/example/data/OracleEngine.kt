package com.example.data

data class OracleQuery(
    val category: String, // "መስተፋቅር", "መፍትሔ ሥራይ", "ሀብትና ንግድ", "ዕውቀትና ጥናት", "ፈውስና ጤና", "የመንገድ ሰላም"
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

        val (geez, amharic, prescription, symbol, timing, audio) = when {
            query.category.contains("መስተፋቅር") -> {
                val g = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    አስማተ ሰሎሞን ዘመስተፋቅር ወተፋቅሮ፤
                    አክሲዮስ፣ ሜልኪዮስ፣ ፈርፋርዮስ፣ ሳዶቅዮስ፤
                    ይትረከቡ በሰላም ወበፍቅር ከመ አብርሃም ወሣራ፤
                    ከማሁ ይትፋቀሩ $sName ወልደ $mName ምስለ $target።
                    ይሰበር ኩሉ ጽልዕ፣ ይንሣዕ ፍቅር ወሰላም ለዘለዓለም።
                """.trimIndent()
                val a = "ጥበበኛው ሰሎሞን የጻፈው የፍቅርና የስምምነት ቃል፤ በ$sName እና በ$target መካከል ፍጹም ፍቅርና መተሳሰብ ይስፈን። ጠብና ኩርፊያ ሁሉ ይሰበር።"
                val p = listOf(
                    "ንጹሕ የማር ጠብታና የጽጌሬዳ ውኃ ማዘጋጀት",
                    "በጠዋት ንጋት ፀሐይ ስትወጣ ፯ (7) ጊዜ መድገም",
                    "በነጭ ዕጣንና ከርቤ ማጨስ",
                    "ጥበቡን ለመልካም ዓላማ ብቻ ማዋል"
                )
                val s = "፠ ☩ ✡ ፍቅር ወሰላም ✡ ☩ ፠"
                val t = "በዕለተ ${zodiac.sign.luckyDay} በጠዋት ፩ ሰዓት"
                val aud = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። የፍቅርና የስምምነት ቃለ መስተፋቅር ለ$sName ምስለ $target።"
                Tuplet(g, a, p, s, t, aud)
            }
            query.category.contains("መፍትሔ ሥራይ") -> {
                val g = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ጸሎተ መፍትሔ ሥራይ ወዓይነ ጥላ፤
                    አልፋ ወዖሜጋ፣ ኤልሻዳይ፣ ጸባኦት፤
                    በዝንቱ አስማተ መለኮት ይትፈታሕ ኩሉ ሥራይ፣
                    ይሰበር ኩሉ ድግምት ወዓይነ ጥላ ዘሰብእ ወዘአጋንንት፤
                    እምላዕለ $sName ወልደ $mName፤
                    ይኩን ንጹሐ ከመ ብርሃነ ፀሐይ በሥልጣነ መስቀል ክርስቶስ።
                """.trimIndent()
                val a = "የመፍትሔ ሥራይና የዓይነ ጥላ ማስተስሪያ፤ በ$sName ላይ የተደረገ የክፉ ሰው ዓይን፣ ምቀኝነትና ድግምት ሁሉ በእግዚአብሔር ሥልጣንና በቅዱሳን መላእክት ኃይል ፈጽሞ ይፈታ፤ ሕይወቱ እንደ ፀሐይ ትብራ።"
                val p = listOf(
                    "በዳማከሴና በጤናአዳም እንፋሎት ለ፫ (3) ተከታታይ ቀናት መታጠን",
                    "ጸሎቱን ጠዋት ፀሐይ ከመውጣቷ በፊት በንጹሕ ውኃ ላይ ፯ ጊዜ አንብቦ ፊትን መታጠብ",
                    "በቤት ውስጥ ከርቤና ዕጣን ማጨስ",
                    "ምጽዋት ለተቸገረ ሰው በዕለተ ሰንበት መስጠት"
                )
                val s = "☩ ☩ ☩ መፍትሔ ሥራይ ወዓይነ ጥላ ☩ ☩ ☩"
                val t = "በዕለተ ማክሰኞ ወይም ሐሙስ ማለዳ"
                val aud = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። ጸሎተ መፍትሔ ሥራይ ወዓይነ ጥላ ለ$sName ወልደ $mName። ኩሉ ሥራይ ይትፈታሕ።"
                Tuplet(g, a, p, s, t, aud)
            }
            query.category.contains("ሀብት") -> {
                val g = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    አስማተ በረከት ወሀብት ዘነቢይ ዳዊት፤
                    ባርክኤል መልአክ፣ ሰዲቅኤል፣ ዑራኤል፤
                    ክሥቱ አናቅጸ ሰማይ ወአውርዱ በረከተ ዲበ ንዋየ $sName፤
                    ከመ ተባረከ ቤተ አብርሃም ወከመ ተባረከ መዝገበ ሰሎሞን፤
                    ይኩን ንግዱ ብሩሀ ወሀብቱ ዕጹፈ በሰላም።
                """.trimIndent()
                val a = "የበረከትና የሀብት ማውጫ ቃል፤ በ$sName የሥራ መስክና ንግድ ላይ የሰማይ በረከት ይፍሰስ፤ እጁ የያዘው ይባረክ፤ ኪሳራና እጦት ይራቅ።"
                val p = listOf(
                    "በሥራ ቦታ የወይራ ዘይትና ንጹሕ ዕጣን ማዘጋጀት",
                    "በዕለተ እሑድ ማለዳ ጸሎቱን ፲፪ (12) ጊዜ መድገም",
                    "ከሚያገኙት ትርፍ የመጀመሪያውን ፍሬ ለድሆች መመጽወት",
                    "በታማኝነትና በፍትሕ መነገድ"
                )
                val s = "፠ ✡ በረከት ወሀብት ✡ ፠"
                val t = "በዕለተ እሑድ ወይም ዓርብ ማለዳ"
                val aud = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። አስማተ በረከት ወሀብት ለ$sName። በረከተ ሰማይ ወምድር ይረድ ዲቤከ።"
                Tuplet(g, a, p, s, t, aud)
            }
            query.category.contains("ዕውቀት") -> {
                val g = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    አስማተ ጥበብ ወማእምር ዘአስተዮ ዑራኤል ለዕዝራ፤
                    አብርህ ልብየ ወአርኁ አዕይንተ አእምሮትየ፤
                    አክሲዮስ፣ ራጉኤል፣ ፋኑኤል፤
                    ጸግዎ ለ$sName ወልደ $mName ጥበበ፣ ዕውቀተ ወማእምረ፤
                    ከመ ኢይረስዕ ኩሎ ዘአንበበ ወዘሰምዐ።
                """.trimIndent()
                val a = "ቅዱስ ዑራኤል ለዕዝራ ጥበብን በጽዋ እንዳጠጣው፤ ለ$sName የአእምሮ ብሩህነት፣ የመረዳትና ፈተናን የማለፍ ልዩ ጥበብ ይሰጠው፤ ያነበበውን እንዳይረሳ።"
                val p = listOf(
                    "በየጠዋቱ ፫ የዘቢብ ፍሬዎች ወይም ንጹሕ ማር በጸሎት መመገብ",
                    "ለጥናት ከመቀመጥ በፊት ጸሎቱን ፫ ጊዜ ማንበብ",
                    "በንጹሕ አየር ውስጥ አእምሮን ማረጋጋት"
                )
                val s = "☩ ✡ ጥበብ ወአእምሮት ✡ ☩"
                val t = "በዕለተ ረቡዕ ማለዳ በንጋት"
                val aud = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። አስማተ ጥበብ ወማእምር ለ$sName ወልደ $mName። አብርህ ልቡ ወአእምሮቱ።"
                Tuplet(g, a, p, s, t, aud)
            }
            else -> {
                val g = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ጸሎተ ሰላም ወዕቀባ ዘመላእክተ ጽድቅ፤
                    ሚካኤል፣ ገብርኤል፣ ሩፋኤል ወዑራኤል፤
                    ዕቀቡ ነፍሶ ወሥጋሁ ለ$sName ወልደ $mName፤
                    በቤት ወበፍኖት፣ በባሕር ወበየብስ፤
                    አድኅንዎ እምኩሉ ዕንቅፋት ወጸላዒ።
                """.trimIndent()
                val a = "የሰላምና የጥበቃ ጸሎት፤ አራቱ የመላእክት አለቆች $sName ን በሄደበት ሁሉ ከክፉ አደጋና ከጠላት ወጥመድ ይጠብቁት።"
                val p = listOf(
                    "ጸሎቱን ከመንገድ በፊት ማንበብ",
                    "የወይራ ቅጠል ይዞ መጓዝ",
                    "አእምሮን ከጭንቀት አርቆ በአምላክ መታመን"
                )
                val s = "☩ ሰላም ወዕቀባ ☩"
                val t = "በማናቸውም የጉዞ ቀን ማለዳ"
                val aud = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። ጸሎተ ዕቀባ ወሰላም ለ$sName። እግዚአብሔር ያብጻሕከ በሰላም።"
                Tuplet(g, a, p, s, t, aud)
            }
        }

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

    private data class Tuplet(
        val geez: String,
        val amharic: String,
        val prescription: List<String>,
        val symbol: String,
        val timing: String,
        val audio: String
    )
}
