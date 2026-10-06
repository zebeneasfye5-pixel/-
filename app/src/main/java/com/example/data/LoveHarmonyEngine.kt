package com.example.data

data class LoveHarmonyInput(
    val seekerName: String,
    val seekerMotherName: String,
    val targetName: String,
    val targetMotherName: String,
    val intentionType: String, // "የትዳር ሰላምና ፍቅር", "የተጣሉትን ማስታረቅ", "የልብ መስህብና መተሳሰብ", "የቤተሰብ አንድነት"
    val birthDay: String
)

data class LoveHarmonyResult(
    val seekerZodiac: ZodiacSign,
    val targetZodiac: ZodiacSign,
    val compatibilityPercentage: Int,
    val elementHarmony: String,
    val geezFormulaText: String,
    val amharicMeaning: String,
    val naturalMaterialsNeeded: List<String>,
    val timingAndDirection: String,
    val sacredAdvisory: String,
    val audioScript: String
)

object LoveHarmonyEngine {
    fun generateHarmony(input: LoveHarmonyInput): LoveHarmonyResult {
        val seekerResult = ZodiacEngine.calculateZodiac(input.seekerName, input.seekerMotherName)
        val targetResult = ZodiacEngine.calculateZodiac(input.targetName, input.targetMotherName)

        val sElement = seekerResult.sign.element.substringBefore(" ")
        val tElement = targetResult.sign.element.substringBefore(" ")

        // Calculate harmony between elements
        val (compatibility, harmonyText) = when {
            sElement == tElement -> Pair(94, "የተመሳሳይ ንጥረ-ነገር (${sElement}) ጥምረት፤ መንፈሳቸው በቀላሉ ይግባባል፤ ጥልቅ የልብ መግባባት አላቸው።")
            (sElement == "እሳት" && tElement == "ነፋስ") || (sElement == "ነፋስ" && tElement == "እሳት") ->
                Pair(96, "እሳት ከነፋስ ጋር፤ ነፋሱ እሳቱን ያቀጣጥለዋል፤ እሳቱ ነፋሱን ያሞቀዋል፤ ከፍተኛ የፍቅር መስህብና መተሳሰብ አላቸው።")
            (sElement == "ውሃ" && tElement == "መሬት") || (sElement == "መሬት" && tElement == "ውሃ") ->
                Pair(92, "ውሃ ከመሬት ጋር፤ ውሃው መሬቱን ያለመልመዋል፤ መሬቱ ውሃውን ይጠብቀዋል፤ የጸናና ፍሬያማ የትዳር ተስፋ አላቸው።")
            (sElement == "እሳት" && tElement == "ውሃ") || (sElement == "ውሃ" && tElement == "እሳት") ->
                Pair(76, "እሳት ከውሃ ጋር፤ የተለያየ ባሕርይ ቢሆንም በትዕግሥትና በጥበብ ሲቀላቀሉ አዲስ ኃይል ይፈጥራሉ፤ ማስታረቂያ ጸሎት ያስፈልጋል።")
            else -> Pair(84, "የተመጣጠነ የባሕርያት ጥምረት፤ በንግግርና በመተሳሰብ ረጅም ዘመን አብረው ይጓዛሉ።")
        }

        val sName = input.seekerName.trim()
        val tName = input.targetName.trim()
        val intention = input.intentionType

        val geezFormula = """
            በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
            አስማተ ፍቅር ወተፋቅሮ ዘሰሎሞን ጥበበኛ፤
            አክሲዮስ፣ ሜልኪዮስ፣ ፈርፋርዮስ፣ ይትረከቡ በሰላም ወበፍቅር፤
            ከመ ተፋቀሩ አብርሃም ወሣራ፣ ይስሐቅ ወርብቃ፣ ያዕቆብ ወራሔል፤
            ከማሁ ይትፋቀሩ $sName ምስለ $tName።
            ይሰበር ኩሉ ፅልዕ ወጽልመት፣ ይትገበር ሰላም በልቦሙ፤
            በሥልጣነ ቃለ ጽድቅ ወበኅይለ መስቀል፤
            ሰላም፣ ሰላም፣ ሰላም ለእሊአሆሙ ለዘለዓለም።
        """.trimIndent()

        val amharicMeaning = """
            በአብ በወልድ በመንፈስ ቅዱስ በአንድ አምላክ ስም።
            ጥበበኛው ሰሎሞን የጻፈው የፍቅርና የመተሳሰብ የሰላም ቃል ይኸው ነው።
            አብርሃምና ሣራ፣ ይስሐቅና ርብቃ፣ ያዕቆብና ራሔል በፍጹም ሰላም እንደተፋቀሩ ሁሉ፤
            በ$sName እና በ$tName መካከልም ፍጹም የሆነ ፍቅር፣ ይቅርታና መተሳሰብ ይስፈን።
            ጠብና ጥላቻ፣ አለመግባባትና የክፉ መንፈስ ሹክሹክታ ሁሉ ይሰበር፤ በልባቸው ውስጥ ሰላምና ፍቅር ይንገሥ።
        """.trimIndent()

        val materials = listOf(
            "ንጹሕ የማር ጠብታ (ጣፋጭ ቃልና ፍቅርን ይወክላል)",
            "የጽጌሬዳ ውኃ (Rose water - ንጹሕ ሽታና ማራኪ መንፈስ)",
            "ነጭ ዕጣንና ከርቤ (የጥላቻንና የክፉ ዓይንን ጭስ ያባርራል)",
            "የወይራ ቅጠል (የሰላምና የእርቅ ምልክት)"
        )

        val timing = when (seekerResult.sign.luckyDay) {
            "ዓርብ (Friday)" -> "በዕለተ ዓርብ ማለዳ ፀሐይ በወጣች በ ፩ (1) ሰዓት ወደ ምሥራቅ አቅጣጫ ዞሮ ፯ (7) ጊዜ ይነበብ።"
            "ሰኞ (Monday)" -> "በዕለተ ሰኞ ምሽት ጨረቃ በወጣችበት ሰዓት በረጋ መንፈስ ፯ ጊዜ ይጸለያል።"
            else -> "በጠዋት ንጋት የፀሐይ ብርሃን ሲፈነጥቅ ወደ ምሥራቅ አቅጣጫ ዞሮ ንጹሕ ውሃና ማር ይዞ ፯ ጊዜ መድገም።"
        }

        val advisory = """
            ማሳሰቢያ ከጥንታዊው መጽሐፍ፡
            ይህ ጥበብ የተሰጠው ለጋብቻ ሰላም፣ ለተጣሉ ባለትዳሮችና ወዳጆች እርቅ፣ እንዲሁም እውነተኛ ፍቅርን ለማጽናት ብቻ ነው።
            የሰውን ነፃ ፈቃድ ለመግፈፍ፣ ለዝሙት ወይም ሰውን ለመበደል ማዋል ፈጽሞ የተከለከለና ውጤቱ ወደ ባለቤቱ የሚመለስ ነው።
            በቅን ልብና በፍቅር ዓላማ የተደረገው ግን የሰላም ፍሬ ያፈራል።
        """.trimIndent()

        val audioScript = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። የፍቅርና የስምምነት ቃል ለ${sName} እና ለ${tName}። አብርሃምና ሣራ እንደተፋቀሩ ፍቅርና ሰላም በመካከላችሁ ይጽና።"

        return LoveHarmonyResult(
            seekerZodiac = seekerResult.sign,
            targetZodiac = targetResult.sign,
            compatibilityPercentage = compatibility,
            elementHarmony = harmonyText,
            geezFormulaText = geezFormula,
            amharicMeaning = amharicMeaning,
            naturalMaterialsNeeded = materials,
            timingAndDirection = timing,
            sacredAdvisory = advisory,
            audioScript = audioScript
        )
    }
}
