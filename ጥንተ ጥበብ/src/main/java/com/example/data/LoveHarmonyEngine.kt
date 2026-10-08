package com.example.data

data class LoveHarmonyInput(
    val seekerName: String,
    val seekerMotherName: String,
    val targetName: String,
    val targetMotherName: String,
    val intentionType: String,
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

        val (compatibility, harmonyText) = when {
            sElement == tElement -> Pair(94, "የተመሳሳይ ንጥረ-ነገር (${sElement}) ጥምረት፤ መንፈሳቸው በቀላሉ ይግባባል፤ ጥልቅ የልብ መግባባት አላቸው።")
            (sElement == "እሳት" && tElement == "ነፋስ") || (sElement == "ነፋስ" && tElement == "እሳት") ->
                Pair(96, "እሳት ከነፋስ ጋር፤ ነፋሱ እሳቱን ያቀጣጥለዋል፤ እሳቱ ነፋሱን ያሞቀዋል፤ ከፍተኛ የፍቅር መስህብና መተሳሰብ አላቸው።")
            (sElement == "ውሃ" && tElement == "መሬት") || (sElement == "መሬት" && tElement == "ውሃ") ->
                Pair(92, "ውሃ ከመሬት ጋር፤ ውሃው መሬቱን ያለመልመዋል፤ መሬቱ ውሃውን ይጠብቀዋል፤ የጸናና ፍሬያማ የትዳር ተስፋ አላቸው።")
            else -> Pair(84, "የተመጣጠነ የባሕርያት ጥምረት፤ በንግግርና በመተሳሰብ ረጅም ዘመን አብረው ይጓዛሉ።")
        }

        val sName = input.seekerName.trim()
        val tName = input.targetName.trim()

        val geezFormula = """
            በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
            አስማተ ፍቅር ወተፋቅሮ ዘሰሎሞን ጥበበኛ፤
            አክሲዮስ፣ ሜልኪዮስ፣ ፈርፋርዮስ፣ ይትረከቡ በሰላም ወበፍቅር፤
            ከመ ተፋቀሩ አብርሃም ወሣራ፣ ይስሐቅ ወርብቃ፣ ያዕቆብ ወራሔል፤
            ከማሁ ይትፋቀሩ $sName ምስለ $tName።
            ይሰበር ኩሉ ፅልዕ ወጽልመት፣ ይትገበር ሰላም በልቦሙ፤
            ሰላም፣ ሰላም፣ ሰላም ለእሊአሆሙ ለዘለዓለም።
        """.trimIndent()

        val amharicMeaning = """
            በአብ በወልድ በመንፈስ ቅዱስ በአንድ አምላክ ስም።
            ጥበበኛው ሰሎሞን የጻፈው የፍቅርና የመተሳሰብ የሰላም ቃል ይኸው ነው።
            አብርሃምና ሣራ በፍጹም ሰላም እንደተፋቀሩ ሁሉ፤
            በ$sName እና በ$tName መካከልም ፍጹም የሆነ ፍቅር፣ ይቅርታና መተሳሰብ ይስፈን።
        """.trimIndent()

        val materials = listOf(
            "ንጹሕ የማር ጠብታ (ጣፋጭ ቃልና ፍቅርን ይወክላል)",
            "የጽጌሬዳ ውኃ (Rose water - ንጹሕ ሽታና ማራኪ መንፈስ)",
            "ነጭ ዕጣንና ከርቤ (የጥላቻንና የክፉ ዓይንን ጭስ ያባርራል)",
            "የወይራ ቅጠል (የሰላምና የእርቅ ምልክት)"
        )

        val timing = "በጠዋት ንጋት የፀሐይ ብርሃን ሲፈነጥቅ ወደ ምሥራቅ አቅጣጫ ዞሮ ንጹሕ ውሃና ማር ይዞ ፯ ጊዜ መድገም።"

        val advisory = """
            ማሳሰቢያ ከጥንታዊው መጽሐፍ፡
            ይህ ጥበብ የተሰጠው ለጋብቻ ሰላም፣ ለተጣሉ ባለትዳሮችና ወዳጆች እርቅ፣ እንዲሁም እውነተኛ ፍቅርን ለማጽናት ብቻ ነው።
        """.trimIndent()

        val audioScript = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ። የፍቅርና የስምምነት ቃል ለ${sName} እና ለ${tName}። ፍቅርና ሰላም በመካከላችሁ ይጽና።"

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
