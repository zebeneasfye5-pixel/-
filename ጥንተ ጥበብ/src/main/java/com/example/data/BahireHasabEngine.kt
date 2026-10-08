package com.example.data

data class EthiopianDateResult(
    val year: Int,
    val evangelist: String,
    val wenber: Int,
    val abaqte: Int,
    val metqe: Int,
    val metqeMonth: String,
    val neneweDate: String,
    val fasikaDate: String,
    val lentDate: String,
    val debreZeytDate: String,
    val hosannaDate: String,
    val goodFridayDate: String,
    val ergetAscensionDate: String,
    val pentecostDate: String,
    val solarCycleYear: Int,
    val lunarPhase: String,
    val season: String,
    val ancientSolarEquationNote: String
)

object BahireHasabEngine {
    private val evangelists = listOf("ማቴዎስ", "ማርቆስ", "ሉቃስ", "ዮሐንስ")

    fun calculate(year: Int = 2017): EthiopianDateResult {
        val ameteAlem = year + 5500
        val evangelistIndex = (ameteAlem % 4)
        val evangelist = evangelists[evangelistIndex]

        val medeb = year % 19
        val wenber = if (medeb == 0) 18 else (medeb - 1)
        val abaqte = (wenber * 11) % 30
        val metqe = if (abaqte == 0) 30 else (30 - abaqte)
        val metqeMonth = if (metqe > 14) "መስከረም" else "ጥቅምት"

        val neneweMonth = if (metqeMonth == "መስከረም") "ጥር" else "የካቲት"
        val rawDay = (metqe + 16) % 30
        val neneweDay = if (rawDay == 0) 30 else rawDay

        val fasikaMonth = "ሚያዝያ"
        val fasikaDay = ((neneweDay + 20) % 30).coerceIn(1, 30)

        val lentMonth = "የካቲት"
        val lentDay = (neneweDay + 14) % 30

        val debreZeytDay = (neneweDay + 11) % 30
        val hosannaDay = (neneweDay + 2) % 30
        val goodFridayDay = (fasikaDay - 2).coerceAtLeast(1)
        val ergetAscensionDay = (fasikaDay + 18) % 30
        val pentecostDay = (fasikaDay + 28) % 30

        val solarCycle = (year % 28) + 1
        val lunarAge = (year * 11) % 30

        val lunarPhase = when {
            lunarAge < 4 -> "ጨረቃ ኅዳጥ (አዲስ ጨረቃ)"
            lunarAge < 10 -> "ጨረቃ ሠርቅ (እያደገች ያለች)"
            lunarAge < 18 -> "ጨረቃ ሙሉዕ (በድር - ሙሉ ጨረቃ)"
            else -> "ጨረቃ ጎደሎ (እየቀነሰች ያለች)"
        }

        val season = when (evangelistIndex) {
            0 -> "ዘመነ ማቴዎስ - የበረከትና የፀሐይ ብርሃን ዘመን"
            1 -> "ዘመነ ማርቆስ - የምድር ፍሬና የዝናብ ዘመን"
            2 -> "ዘመነ ሉቃስ - የተድላና የበጋ ወቅት"
            else -> "ዘመነ ዮሐንስ - የጥበብና የዘመን መለወጫ ማኅተም"
        }

        val note = """
            በአቡሻህር ጥንታዊ የቀመር መጽሐፍ መሠረት፤ የፀሐይ ዓመታዊ ጉዞ ፫፻፷፭ (365) ቀናት ከ፭ (5) ሰዓት ከ፵፰ (48) ደቂቃ ነው።
            በዚህ ቀመር መሠረት በየ ፬ ዓመቱ አንድ ቀን በመጨመር ጳጉሜን ፮ ትሆናለች።
            የፀሐይ ማዕረግ በ፲፪ቱ የሰማይ ደጆች ውስጥ በየ ፴ ቀኑ ከአንዱ ወደ አንዱ ይዞራል።
        """.trimIndent()

        return EthiopianDateResult(
            year = year,
            evangelist = evangelist,
            wenber = wenber,
            abaqte = abaqte,
            metqe = metqe,
            metqeMonth = metqeMonth,
            neneweDate = "$neneweMonth $neneweDay",
            fasikaDate = "$fasikaMonth $fasikaDay",
            lentDate = "$lentMonth $lentDay",
            debreZeytDate = "መጋቢት $debreZeytDay",
            hosannaDate = "መጋቢት/ሚያዝያ $hosannaDay",
            goodFridayDate = "ሚያዝያ $goodFridayDay",
            ergetAscensionDate = "ግንቦት $ergetAscensionDay",
            pentecostDate = "ሰኔ $pentecostDay",
            solarCycleYear = solarCycle,
            lunarPhase = lunarPhase,
            season = season,
            ancientSolarEquationNote = note
        )
    }
}
