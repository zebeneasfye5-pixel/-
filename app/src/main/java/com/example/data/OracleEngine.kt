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
    val referenceBook: String,
    val referenceChapter: String,
    val referencePage: String,
    val referenceSource: String,
    val geezScripture: String,
    val amharicExplanation: String,
    val sacredPrescription: List<String>,
    val telsemSymbol: String,
    val auspiciousTiming: String,
    val voiceNarrative: String
)

object OracleEngine {
    val categories = listOf(
        "የጥይት መከላከያ" to "ጥይት እንዳይመታ፣ አረር እንዲቀዘቅዝና እንዲስት (መክስተ ደመና)",
        "የስለት መከላከያ" to "ሰይፍ፣ ካራና ስለታማ ነገሮች አካልን እንዳይቆርጡ (ሰይፈ ሥላሴ ወአፍልጣ)",
        "ዕፀ መሰውር" to "ከጠላት፣ ከአደጋና ከክፉ ዓይን መሰወሪያና ማምለጫ",
        "መፍትሔ ሥራይ" to "ከዓይነ ጥላ፣ ከድግምት፣ ከመተትና ከክፉ መንፈስ መከላከያ",
        "መስተፋቅርና ፍቅር" to "የትዳር ሰላም፣ የልብ ስምምነትና ማስታረቂያ ጥበብ",
        "ሀብትና ንግድ" to "የበረከት፣ የሥራ ስኬትና የሀብት ማውጫ (ባርክኤል)",
        "ዕውቀትና ጥናት" to "የአእምሮ ብሩህነት፣ የትምህርትና የማስታወስ ጥበብ (ሰሎሞን)",
        "ፈውስና ጤና" to "የሥጋና የነፍስ ደዌ መፈወሻ ቅዱሳን ዕፀዋት",
        "የመንገድ ሰላም" to "የጉዞ፣ የባህር ማዶና የፈተና መከላከያ ጋሻ"
    )

    fun consultOracle(query: OracleQuery): OracleResponse {
        val sName = query.seekerName.ifBlank { "ገብረ እግዚአብሔር" }
        val mName = query.motherName.ifBlank { "ወለተ ማርያም" }
        val target = query.detailsOrTarget.ifBlank { "ጥበቃና ሰላም" }
        val zodiac = ZodiacEngine.calculateZodiac(sName, mName)

        val refBook: String
        val refChap: String
        val refPage: String
        val refSource: String
        val geez: String
        val amharic: String
        val prescription: List<String>
        val symbol: String
        val timing: String

        when (query.category) {
            "የጥይት መከላከያ" -> {
                refBook = "መጽሐፈ ሐጹረ መስቀል ወአስማተ ጥይት ዘጥንት"
                refChap = "ምዕራፍ ፯፡ መክስተ ደመና ወመከላከየ አረር"
                refPage = "ገጽ ፸፪ (ብራና ቁጥር ፫፻፲)"
                refSource = "የደብረ ሊባኖስ ገዳም ጥንታዊ መዝገብ"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ጸሎት በእንተ መስተሐምም ወመክስተ ደመና ዘጥይት፤
                    በስመ ያህ፣ ኤልሻዳይ፣ ጸባኦት፣ አማኑኤል፤
                    ከመ ኢይቅረበኒ አረር ወነደ እሳት ዲበ $sName ወልደ $mName፤
                    ይኩን ጥይት ከመ ማይ ወከመ ሐመድ በቅድሜየ፤
                    በኃይለ መስቀሉ ለክርስቶስ ይትሰበር ኩሉ ነፍጥ ወመንፈሰ ሞት፤
                    ቅዱስ ሚካኤል ወገብርኤል ይኩኑኒ ጋሻ ወዋልታ ለዓለመ ዓለም፤ አሜን።
                """.trimIndent()
                amharic = "የጥይትና የአረር መከላከያ ጥንታዊ ብራና፤ ጥይት አቅጣጫውን እንዲስት ወይም እንደ ውሃና አፈር እንዲቀዘቅዝ ራስን በመስቀል ኃይል መጠበቂያ ፍጹም ቃል ነው።"
                prescription = listOf(
                    "ዕፀ ደብዳቤ ወይም ዕፀ መሰውር በንጹሕ ቀይ ቆዳ ተሰፍቶ በግራ ክንድ ወይም በአንገት ማሰር",
                    "ማለዳ ከመውጣት በፊት ጸሎቱን ፯ (ሰባት) ጊዜ አንብቦ በሦስቱ ጣቶች ማማተብ",
                    "በእምነትና በንጽሕና መመላለስ፤ ለተቸገረ ሰው ምጽዋት ማድረግ"
                )
                symbol = "፠ ☩ 🛡️ ሐጹረ መስቀል ወመክስተ ደመና 🛡️ ☩ ፠"
                timing = "ማለዳ ፀሐይ ስትወጣ ወደ ምሥራቅ ዞሮ ማድረስ"
            }
            "የስለት መከላከያ" -> {
                refBook = "መጽሐፈ ሰይፈ ሥላሴ ወአስማተ አፍልጣ ዘጥንት"
                refChap = "ምዕራፍ ፱፡ መከላከየ ስለት ወአፍልጣ"
                refPage = "ገጽ ፶፰ (ብራና ቁጥር ፻፵፭)"
                refSource = "የዋልድባ ገዳም ጥንታዊ ብራና"
                geez = """
                    በስመ ሥላሴ ቅዱስ፤ አብ እሳት፣ ወልድ እሳት፣ መንፈስ ቅዱስ እሳት፤
                    በዛቲ አስማተ ስለት ኢይምታዐኒ ሰይፍ ወኩናት፣ ሾተል ወካራ ዲበ $sName ወልደ $mName፤
                    ይኩን ኩሉ ብረት ከመ ሰም በቅድመ እሳት፤
                    በስመ ዑራኤል መልአክ ዘይመልሕ ሰይፈ እሳት፤
                    ዕቀበኒ ወአድኅነኒ እምኩሉ አፈ ስለት ወእምነዳደ ብረት፤ አሜን።
                """.trimIndent()
                amharic = "ከሰይፍ፣ ከቢላዋ፣ ከጦርና ከማንኛውም ስለታማ ብረት መከላከያ ጥንታዊ ጋሻ፤ ብረት እንደ ሰም እንዲቀልጥና አካልን እንዳይቆርጥ የሚጸለይ የታመነ ቃል ነው።"
                prescription = listOf(
                    "ዕፀ አፍልጣ ሥር በንጽሕና ቆርጦ ከጥቁር ዕጣን ጋር በወይራ ዘይት አፍልቶ አካልን መቀባት",
                    "ቃሉን ፫ ጊዜ በንጹሕ ውኃ ላይ አንብቦ ማለዳ መጠጣት",
                    "በክንድ ወይም በኪስ በክታብ መያዝ"
                )
                symbol = "፠ ☩ ⚔️ ሰይፈ ሥላሴ ወአፍልጣ ⚔️ ☩ ፠"
                timing = "ማለዳ 1:00 ሰዓት ወይም ወደ አደገኛ ቦታ ከመሄድ በፊት"
            }
            "ዕፀ መሰውር" -> {
                refBook = "መጽሐፈ ዕፀ መሰውር ወኅቡዓት ዘሄኖክ ወኤልያስ"
                refChap = "ምዕራፍ ፬፡ አስማተ መሰውር ወተከውኖ"
                refPage = "ገጽ ፴፱ (ብራና ቁጥር ፹፪)"
                refSource = "የጣና ቂርቆስ ገዳም ጥንታዊ መዝገብ"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ጸሎት በእንተ ዕፀ መሰውር፤
                    በከመ ከወንኮሙ ለሄኖክ ወለኤልያስ እምዓይነ ኃጥአን፤
                    ከማሁ ሰውረኒ ሊተ ለ$sName ወልደ $mName እምዓይነ ፀር ወጸላኢ፤
                    በስመ አስተርእዮ ወኅቡዕ፤ አዖ፣ ፈዖ፣ ኤልሻዳይ፤
                    ዕፅም ዓይኖሙ ወዕበስ አእምሮቶሙ ከመ ኢይርአዩኒ ወኢያእምሩኒ ለዓለም፤ አሜን።
                """.trimIndent()
                amharic = "በጠላት ወይም በአደጋ ጊዜ ከክፉ ዓይንና ከፈላጊዎች መሰወሪያ ጥንታዊ ምስጢር፤ ዓይናቸው እንዳያይና አእምሮታቸው እንዳያስተውል የሚያደርግ ጥበብ ነው።"
                prescription = listOf(
                    "የዕፀ መሰውር ቅጠልና ሥር በንጽሕና ተቆርጦ ከሰሊጥ ዘይት ጋር ግንባርንና ዓይንን መቀባት",
                    "ቃሉን በለሆሳስ ፯ ጊዜ ደግሞ በአደጋው ወቅት መራመድ",
                    "በክታብ ተሰፍቶ በአንገት ወይም በኪስ መያዝ"
                )
                symbol = "፠ ☩ 👁️‍🗨️ ዕፀ መሰውር ዘሄኖክ 👁️‍🗨️ ☩ ፠"
                timing = "በዕለተ ዓርብ ማታ ወይም በአስቸጋሪ ሰዓት"
            }
            "መስተፋቅርና ፍቅር" -> {
                refBook = "መጽሐፈ ተፋቅሮ ወሰላም ዘሰሎሞን"
                refChap = "ምዕራፍ ፬፡ አስማተ ተፋቅሮ ወስምምነት"
                refPage = "ገጽ ፲፰ (ብራና ቁጥር ፷፪)"
                refSource = "የጣና ቂርቆስ ገዳም ብራና"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    በስመ ሰሎሞን ጥበበኛ ንጉሠ እስራኤል፤
                    አፍቅር፣ አሰምር፣ አስተፋቅር በይነ $sName ወልደ $mName ወበይነ $target፤
                    ይኩን ፍቅር ከመ ፍቅረ ዳዊት ወዮናታን፤
                    በኃይለ ዝንቱ ቃል ይትከደን ሰላም ወይኩን ስምምነት ዘኢይትፈታሕ፤ አሜን።
                """.trimIndent()
                amharic = "የትዳር ሰላም፣ የልብ ስምምነትና የተጣሉትን በይቅርታና በመስህብ ቃል ማቀራረቢያ ጥንታዊ መዝገብ ነው።"
                prescription = listOf(
                    "የጽጌረዳ ውኃ ወይም የከርቤ ሽቶ መያዝ",
                    "ቃሉን በማለዳ ፫ ጊዜ በለሆሳስ ማንበብ",
                    "በቅን ልብ ለሰላም መነጋገር"
                )
                symbol = "፠ ☩ ❤️ ተፋቅሮ ወሰላም ❤️ ☩ ፠"
                timing = "እሑድ ማለዳ 1:00 ሰዓት"
            }
            "ሀብትና ንግድ" -> {
                refBook = "መጽሐፈ በረከት ወሀብተ ንግድ"
                refChap = "ምዕራፍ ፮፡ አስማተ ባርክኤል መልአከ በረከት"
                refPage = "ገጽ ፶፪ (ብራና ቁጥር ፺፬)"
                refSource = "የደብረ ዳሞ ገዳም ብራና መዝገብ"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ባርክኤል መልአከ በረከት፤
                    ባርክ ንግደ $sName ወልደ $mName ወሀበኒ ፍሬ በረከት፤
                    አርኅው ደጆተ ሀብት ወአርሕቅ እምኔየ ስእነተ ወጽልመተ፤
                    ይኩን ፍሬ ድካምየ ብሩሀ ከመ ፀሐይ፤ አሜን።
                """.trimIndent()
                amharic = "በታማኝነት መሥራትና የበረከት ጸሎትን ከምጽዋት ጋር ማድረስ ንግድን እንደሚያቀናና በረከትን እንደሚያበዛ የተመዘገበ ምስጢር ነው።"
                prescription = listOf(
                    "ሐሙስ ጠዋት ንግድ ከመጀመር በፊት ማዕጠንት ማጠን",
                    "ቃሉን ፯ ጊዜ ማንበብ",
                    "ከትርፉ የመጀመሪያውን ለምጽዋት መለየት"
                )
                symbol = "፠ ☩ 💰 በረከተ ባርክኤል 💰 ☩ ፠"
                timing = "ዕለተ ሐሙስ ማለዳ በ1:00 ሰዓት"
            }
            "ዕውቀትና ጥናት" -> {
                refBook = "መጽሐፈ ጥበበ ሰሎሞን ወፈሊጣተ አእምሮ"
                refChap = "ምዕራፍ ፩፡ አርኅዎ ልብ ወአዕምሮ"
                refPage = "ገጽ ፲፭ (ብራና ቁጥር ፳፯)"
                refSource = "የአክሱም ጽዮን ጥንታዊ ቤተ መዛግብት"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    እግዚአብሔር አምላከ ጥበብ ወነባቤ አዕምሮ፤
                    አርሁ ልበ $sName ወልደ $mName ከመ ይስመዕ ጥበበ ወያእምር ፍልስፍና፤
                    ከመ ፀሐይ ያብርህ አእምሮቱ ወከመ ወርኅ ያብራህ ሕሊናሁ፤ አሜን።
                """.trimIndent()
                amharic = "ለተማሪዎችና ጥልቅ እውቀት ለሚሹ ሰዎች አእምሮን የሚያነቃቃና የማስታወስ ኃይልን የሚያጎለብት የጥበብ ቃል ነው።"
                prescription = listOf(
                    "ማለዳ በባዶ ሆድ ንጹሕ ውኃ ላይ ቃሉን አንብቦ መጠጣት",
                    "ጥናት ከመጀመር በፊት ወደ ምሥራቅ ዞሮ ፫ ጊዜ ማሰላሰል",
                    "ንጹሕ ማር በትንሹ መቅመስ"
                )
                symbol = "፠ ☩ 📜 ጥበበ ሰሎሞን 📜 ☩ ፠"
                timing = "ማለዳ ከ11:30 እስከ 12:30 ሰዓት"
            }
            "ፈውስና ጤና" -> {
                refBook = "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት"
                refChap = "ክፍል ፪፡ ፈውሰ ርእስ ወከርሥ"
                refPage = "ገጽ ፸፬ (ብራና ቁጥር ፳፰)"
                refSource = "የሐይቅ እስጢፋኖስ ገዳም ብራና መዝገብ"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    ሩፋኤል ፈዋሴ ዱያን፤
                    በዝንቱ ዕፀ ሳቤቅ ወዕፀ ሕይወት ፈውስ ሕማመ $sName ወልደ $mName፤
                    በከመ አድኃንኮ ለጦቢት እምደዌሁ ከማሁ ፈውስ ድዉያነ፤ አሜን።
                """.trimIndent()
                amharic = "የተፈጥሮ ዕፀዋት (ዳማከሴ፣ ጤናአዳም፣ ግራዋ) እና የሩፋኤል የፈውስ ጸሎት የደዌን ኃይል ያርቃሉ።"
                prescription = listOf(
                    "ዳማከሴና ጤናአዳም በሙቅ ውኃ ማፍላት",
                    "እንፋሎቱን ለ፲ ደቂቃ መታጠንና በንጹሕ ማር ጭማቂውን መውሰድ",
                    "የፈውስ ጸሎቱን በንጹሕ እምነት ማድረስ"
                )
                symbol = "፠ ☩ 🌿 ፈዋሴ ዱያን 🌿 ☩ ፠"
                timing = "ፀሐይ ስትወጣ ማለዳ በ1:00 ሰዓት"
            }
            "የመንገድ ሰላም" -> {
                refBook = "መጽሐፈ ሰይፈ ሥላሴ ወዐቃቤ ርእስ"
                refChap = "ክፍል ፩፡ ጋሻ መንፈሳዊ ወዐቃቤ መንገድ"
                refPage = "ገጽ ፴፭ (ብራና ቁጥር ፵፯)"
                refSource = "የዋሸራ ገዳም ጥንታዊ ብራና"
                geez = """
                    በስመ ሥላሴ ቅዱስ አሐዱ አምላክ፤
                    ሰይፈ መለኮት ይቁም በየማነ $sName ወልደ $mName ወበፀጋሙ፤
                    ሚካኤል በቅድሜሁ፣ ገብርኤል በድኅሬሁ፣ ሩፋኤል በየማኑ፣ ዑራኤል በፀጋሙ፤
                    ኢይቅረቦ ኩሉ እኩይ ወመንፈሰ ጽልመት ውስተ ፍኖቱ፤ አሜን።
                """.trimIndent()
                amharic = "በመንገድ ላይ ከሚገጥም ድንገተኛ አደጋና ከክፉ ዓይን የታመነ መንፈሳዊ ጋሻ ነው።"
                prescription = listOf(
                    "ከመንገድ ከመነሳት በፊት ቃሉን አንብቦ ማማተብ",
                    "በሰላም መጓዝ"
                )
                symbol = "፠ ☩ 🧭 ዐቃቤ መንገድ 🧭 ☩ ፠"
                timing = "ከቤት ከመውጣት አስቀድሞ"
            }
            else -> { // መፍትሔ ሥራይ
                refBook = "መጽሐፈ መፍትሔ ሥራይ ወአስማተ ሰሎሞን ዘጥንት"
                refChap = "ምዕራፍ ፫፡ መቅሰፍተ አጋንንት ወዓይነ ጥላ"
                refPage = "ገጽ ፵፪ (ብራና ቁጥር ፻፲፰)"
                refSource = "የደብረ ሊባኖስ ገዳም ጥንታዊ ብራና"
                geez = """
                    በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
                    አስማተ ሰሎሞን ንጉሠ እስራኤል፤
                    ኤልሻዳይ፣ ማኅቶት፣ ጸባኦት፣ አማኑኤል፤
                    ይትፈታሕ ኩሉ ዕንቅፋት ወይሠረር ሰላም ዲበ $sName ወልደ $mName፤
                    በኃይለ መስቀል ይስበር አጋንንተ ፀላዕት ወይኩን ንጹሐ ከመ ብርሃነ ፀሐይ፤ አሜን።
                """.trimIndent()
                amharic = "ከዓይነ ጥላ፣ ከድግምት፣ ከክፉ ዓይንና ከመንፈስ ጭንቀት ለመላቀቅ የታዘዘ ታላቅ መፍትሔ ሥራይ ነው።"
                prescription = listOf(
                    "ማለዳ ፊትና እጅን በንጹሕ ውኃ መታጠብ",
                    "ዕጣንና ከርቤ በከሰል ላይ በማጨስ ቤቱንና ልብስን ማጠን",
                    "ይህን የግእዝ ጸሎት ፯ (7) ጊዜ በድምፅ ማንበብ",
                    "ንጹሕ የብርጭቆ ውኃ ላይ ቃሉን አንብቦ ጠዋት በባዶ ሆድ መጠጣት"
                )
                symbol = "፠ ☩ ✡ መፍትሔ ሥራይ ✡ ☩ ፠"
                timing = "በዕለተ ሰኞና ሐሙስ ማለዳ 12:00 ሰዓት"
            }
        }

        val voice = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። ከ$refBook የተገኘ የ${query.category} መልስ። $amharic"

        return OracleResponse(
            category = query.category,
            seekerName = sName,
            motherName = mName,
            zodiacResult = zodiac,
            referenceBook = refBook,
            referenceChapter = refChap,
            referencePage = refPage,
            referenceSource = refSource,
            geezScripture = geez,
            amharicExplanation = amharic,
            sacredPrescription = prescription,
            telsemSymbol = symbol,
            auspiciousTiming = timing,
            voiceNarrative = voice
        )
    }
}
