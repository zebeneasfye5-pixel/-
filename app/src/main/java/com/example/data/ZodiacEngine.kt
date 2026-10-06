package com.example.data

data class ZodiacSign(
    val id: Int,
    val nameGeez: String,
    val nameAmharic: String,
    val element: String, // እሳት, መሬት, ነፋስ, ውሃ
    val planet: String,
    val angel: String,
    val gemStone: String,
    val luckyDay: String,
    val luckyColor: String,
    val personality: String,
    val destinyAndWealth: String,
    val healthAdvice: String,
    val loveCompatibility: String,
    val ancientPrayer: String
)

data class ZodiacCalculationResult(
    val seekerName: String,
    val motherName: String,
    val seekerScore: Int,
    val motherScore: Int,
    val totalScore: Int,
    val sign: ZodiacSign,
    val remainderMod12: Int
)

object ZodiacEngine {
    // Ancient Abushahir Gematria letter values (አበግደሀ የፊደል ቀመር)
    private val letterValues = mapOf(
        'ሀ' to 1, 'ሁ' to 1, 'ሂ' to 1, 'ሃ' to 1, 'ሄ' to 1, 'ህ' to 1, 'ሆ' to 1,
        'ለ' to 2, 'ሉ' to 2, 'ሊ' to 2, 'ላ' to 2, 'ሌ' to 2, 'ል' to 2, 'ሎ' to 2,
        'ሐ' to 3, 'ሑ' to 3, 'ሒ' to 3, 'ሓ' to 3, 'ሔ' to 3, 'ሕ' to 3, 'ሖ' to 3,
        'መ' to 4, 'ሙ' to 4, 'ሚ' to 4, 'ማ' to 4, 'ሜ' to 4, 'ም' to 4, 'ሞ' to 4,
        'ሠ' to 5, 'ሡ' to 5, 'ሢ' to 5, 'ሣ' to 5, 'ሤ' to 5, 'ሥ' to 5, 'ሦ' to 5,
        'ረ' to 6, 'ሩ' to 6, 'ሪ' to 6, 'ራ' to 6, 'ሬ' to 6, 'ር' to 6, 'ሮ' to 6,
        'ሰ' to 7, 'ሱ' to 7, 'ሲ' to 7, 'ሳ' to 7, 'ሴ' to 7, 'ስ' to 7, 'ሶ' to 7,
        'ቀ' to 8, 'ቁ' to 8, 'ቂ' to 8, 'ቃ' to 8, 'ቄ' to 8, 'ቅ' to 8, 'ቆ' to 8,
        'በ' to 9, 'ቡ' to 9, 'ቢ' to 9, 'ባ' to 9, 'ቤ' to 9, 'ብ' to 9, 'ቦ' to 9,
        'ተ' to 10, 'ቱ' to 10, 'ቲ' to 10, 'ታ' to 10, 'ቴ' to 10, 'ት' to 10, 'ቶ' to 10,
        'ኀ' to 20, 'ኁ' to 20, 'ኂ' to 20, 'ኃ' to 20, 'ኄ' to 20, 'ኅ' to 20, 'ኆ' to 20,
        'ነ' to 30, 'ኑ' to 30, 'ኒ' to 30, 'ና' to 30, 'ኔ' to 30, 'ን' to 30, 'ኖ' to 30,
        'አ' to 40, 'ኡ' to 40, 'ኢ' to 40, 'ኣ' to 40, 'ኤ' to 40, 'እ' to 40, 'ኦ' to 40,
        'ከ' to 50, 'ኩ' to 50, 'ኪ' to 50, 'ካ' to 50, 'ኬ' to 50, 'ክ' to 50, 'ኮ' to 50,
        'ወ' to 60, 'ዉ' to 60, 'ዊ' to 60, 'ዋ' to 60, 'ዌ' to 60, 'ው' to 60, 'ዎ' to 60,
        'ዐ' to 70, 'ዑ' to 70, 'ዒ' to 70, 'ዓ' to 70, 'ዔ' to 70, 'ዕ' to 70, 'ዖ' to 70,
        'ዘ' to 80, 'ዙ' to 80, 'ዚ' to 80, 'ዛ' to 80, 'ዜ' to 80, 'ዝ' to 80, 'ዞ' to 80,
        'የ' to 90, 'ዩ' to 90, 'ዪ' to 90, 'ያ' to 90, 'ዬ' to 90, 'ይ' to 90, 'ዮ' to 90,
        'ደ' to 100, 'ዱ' to 100, 'ዲ' to 100, 'ዳ' to 100, 'ዴ' to 100, 'ድ' to 100, 'ዶ' to 100,
        'ገ' to 200, 'ጉ' to 200, 'ጊ' to 200, 'ጋ' to 200, 'ጌ' to 200, 'ግ' to 200, 'ጎ' to 200,
        'ጠ' to 300, 'ጡ' to 300, 'ጢ' to 300, 'ጣ' to 300, 'ጤ' to 300, 'ጥ' to 300, 'ጦ' to 300,
        'ጰ' to 400, 'ጱ' to 400, 'ጲ' to 400, 'ጳ' to 400, 'ጴ' to 400, 'ጵ' to 400, 'ጶ' to 400,
        'ጸ' to 500, 'ጹ' to 500, 'ጺ' to 500, 'ጻ' to 500, 'ጼ' to 500, 'ጽ' to 500, 'ጾ' to 500,
        'ፀ' to 600, 'ፁ' to 600, 'ፂ' to 600, 'ፃ' to 600, 'ፄ' to 600, 'ፅ' to 600, 'ፆ' to 600,
        'ፈ' to 700, 'ፉ' to 700, 'ፊ' to 700, 'ፋ' to 700, 'ፌ' to 700, 'ፍ' to 700, 'ፎ' to 700,
        'ፐ' to 800, 'ፑ' to 800, 'ፒ' to 800, 'ፓ' to 800, 'ፔ' to 800, 'ፕ' to 800, 'ፖ' to 800
    )

    fun calculateGematria(name: String): Int {
        var sum = 0
        val clean = name.trim()
        for (char in clean) {
            val value = letterValues[char]
            if (value != null) {
                sum += value
            } else if (char.isLetter()) {
                sum += (char.code % 50) + 1
            }
        }
        return if (sum == 0) 12 else sum
    }

    val signs = listOf(
        ZodiacSign(
            id = 1,
            nameGeez = "ሐመል (Hamal)",
            nameAmharic = "በግ (Aries)",
            element = "እሳት (Fire)",
            planet = "መሪሕ (Mars)",
            angel = "ቅዱስ ሚካኤል (St. Michael)",
            gemStone = "ቀይ ሩቢ (Ruby)",
            luckyDay = "ማክሰኞ (Tuesday)",
            luckyColor = "ቀይ እና ወርቃማ (Crimson & Gold)",
            personality = "ደፋር፣ አመራር ወዳድ፣ ኃይለኛ መንፈስ ያለው፣ ቶሎ የሚቆጣ ግን ቶሎ የሚበርድ፣ ቅን ልብ ያለው እና ለጓደኛ ታማኝ።",
            destinyAndWealth = "በወጣትነቱ ብዙ ፈተና ገጥሞት በዕድሜ ማለዳ ላይ ከፍተኛ ክብርና ሀብት ያገኛል። በንግድና በአመራር ዘርፍ ትልቅ ስኬት ይኖረዋል።",
            healthAdvice = "የራስ ምታትና የዓይን ድካም ሊያጋጥመው ይችላል። ቀዝቃዛ ውሃ መጠጣትና በዳማከሴ መታጠን ይስማማዋል።",
            loveCompatibility = "ከቀውስ (Sagittarius) እና ከአሰድ (Leo) ጋር እጅግ ይስማማል። ከሰርጣን (Cancer) ጋር ጥንቃቄ ያስፈልገዋል።",
            ancientPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ሚካኤል መልአክ አዕርጋ ለጸሎትየ፤ አድኅነኒ እምእሳት ወእምኩሉ ጸላኢ።"
        ),
        ZodiacSign(
            id = 2,
            nameGeez = "ሰውር (Sawur)",
            nameAmharic = "በሬ (Taurus)",
            element = "መሬት (Earth)",
            planet = "ዙህራ (Venus)",
            angel = "ቅዱስ ገብርኤል (St. Gabriel)",
            gemStone = "መረግድ (Emerald)",
            luckyDay = "ዓርብ (Friday)",
            luckyColor = "አረንጓዴ እና ቡናማ (Green & Earth Brown)",
            personality = "ረጋ ያለ፣ ጽኑ፣ ታታሪ፣ ጥበባዊ ፍቅር ያለው፣ ሀብት አጠራቃሚ፣ በቀላሉ ውሳኔ የማይቀይር ታማኝ ሰው።",
            destinyAndWealth = "በእርሻ፣ በሕንፃ፣ በንግድና በንብረት ግዥ ትልቅ ባለጸጋ ይሆናል። ድካሙ ፈጽሞ ከንቱ አይቀርም።",
            healthAdvice = "የጉሮሮና የአንገት ሕመም ሊያጋጥመው ስለሚችል ማርና ዝንጅብል መመገብ ይመከራል።",
            loveCompatibility = "ከሰንቡላ (Virgo) እና ከጃዲ (Capricorn) ጋር እጅግ የጸና ትዳር ይመሰርታል።",
            ancientPrayer = "ገብርኤል አብሳሬ ሰላም፤ አብሥረኒ በሰላም ወበበረከት፤ አንትሙ መላእክተ ምድር ዐቅቡኒ።"
        ),
        ZodiacSign(
            id = 3,
            nameGeez = "ጀውዛ (Jewza)",
            nameAmharic = "መንታ (Gemini)",
            element = "ነፋስ (Air)",
            planet = "አጣርድ (Mercury)",
            angel = "ቅዱስ ሩፋኤል (St. Raphael)",
            gemStone = "ቶጳዝዮን (Topaz)",
            luckyDay = "ረቡዕ (Wednesday)",
            luckyColor = "ቢጫ እና ሰማያዊ (Yellow & Sky Blue)",
            personality = "አንደበተ ርቱዕ፣ ፈጣን አሳቢ፣ ተወዳጅ፣ ጥበብና እውቀት ፈላጊ፣ ተለዋዋጭ ግን ማራኪ ስብዕና ያለው።",
            destinyAndWealth = "በጽሕፈት፣ በመገናኛ ብዙኃን፣ በንግግርና በጥናት ዘርፍ ስሙ ይታወቃል። የሀብት ምንጩ ልዩ ልዩ ነው።",
            healthAdvice = "የሳንባና የነርቭ ውጥረት እንዳይበዛበት የጠዋት ንጹሕ አየር መተንፈስና ጦስኝ ሻይ መጠጣት መልካም ነው።",
            loveCompatibility = "ከሚዛን (Libra) እና ከደለው (Aquarius) ጋር በፍቅር ይስማማል።",
            ancientPrayer = "ሩፋኤል ፈዋሴ ዱያን፤ ፈውሰኒ እምሕማመ ልብ ወእምሕማመ ሥጋ፤ ወአብርህ አዕይንትየ።"
        ),
        ZodiacSign(
            id = 4,
            nameGeez = "ሰርጣን (Sertan)",
            nameAmharic = "ሸርጣን (Cancer)",
            element = "ውሃ (Water)",
            planet = "ቀመር (Moon)",
            angel = "ቅዱስ ዑራኤል (St. Uriel)",
            gemStone = "ሉል (Pearl)",
            luckyDay = "ሰኞ (Monday)",
            luckyColor = "ብርማ እና ነጭ (Silver & White)",
            personality = "ሩኅሩኅ፣ የቤተሰብ ወዳድ፣ ምስጢር ጠባቂ፣ ጥልቅ ስሜት ያለው፣ ታዛቢና መንፈሳዊ ጥበብን የሚያስተውል።",
            destinyAndWealth = "ከውጭ ሀገር ጉዞና ከውሃ/ባህር ጋር የተያያዘ ሥራ በረከት ያመጣለታል። በእድሜው አጋማሽ ሰፊ ርስት ይይዛል።",
            healthAdvice = "የጨጓራና የሆድ መነፋት እንዳይኖረው ጤናአዳም ማፍላትና መረጋጋት ይገባዋል።",
            loveCompatibility = "ከአቅራብ (Scorpio) እና ከሑት (Pisces) ጋር የተባረከ ፍቅር ይኖረዋል።",
            ancientPrayer = "ዑራኤል መላከ ብርሃን፤ በጽዋዐ እሳት ዘአስተይኮ ለዕዝራ፤ አጽግበኒ ጥበበ ወማዕምረ።"
        ),
        ZodiacSign(
            id = 5,
            nameGeez = "አሰድ (Asad)",
            nameAmharic = "አንበሳ (Leo)",
            element = "እሳት (Fire)",
            planet = "ፀሐይ (Sun)",
            angel = "ቅዱስ ፋኑኤል (St. Phanuel)",
            gemStone = "አልማዝ (Diamond)",
            luckyDay = "እሑድ (Sunday)",
            luckyColor = "ወርቃማ እና ብርቱካናማ (Gold & Orange)",
            personality = "ግርማ ሞገስ ያለው፣ ቸር፣ ኩሩ፣ መሪ፣ ተከባሪ፣ የተቸገረን የሚረዳ ግን ክብሩን የማይነካኩበት።",
            destinyAndWealth = "በህዝብ ፊት መሪና ባለሥልጣን ይሆናል። ሀብቱ በክብርና በታማኝነት የሚመጣ ነው።",
            healthAdvice = "የልብ ጤንነትና የደም ዝውውርን መጠበቅ አለበት። ከመጠን ያለፈ ቁጣን ማስወገድ ይገባዋል።",
            loveCompatibility = "ከሐመል (Aries) እና ከቀውስ (Sagittarius) ጋር ፍጹም ስምምነት አለው።",
            ancientPrayer = "ፋኑኤል ተቃዋሜ ሰይጣናት፤ ዐቅበኒ እምኩሉ ጸላዒ፤ ወጸግወኒ ግርማ ወሞገስ በቅድመ ሰብእ።"
        ),
        ZodiacSign(
            id = 6,
            nameGeez = "ሰንቡላ (Senbula)",
            nameAmharic = "እሸት/ድንግል (Virgo)",
            element = "መሬት (Earth)",
            planet = "አጣርድ (Mercury)",
            angel = "ቅዱስ ሳቁኤል (St. Saquiel)",
            gemStone = "ሰንፔር (Sapphire)",
            luckyDay = "ረቡዕ (Wednesday)",
            luckyColor = "ባህር ሰማያዊ እና የወይራ አረንጓዴ (Navy & Olive)",
            personality = "ጥንቁቅ፣ ንጹሕ፣ ሂሳባዊ አእምሮ ያለው፣ ስራ ወዳድ፣ አገልጋይ፣ ጥቃቅን ነገሮችን የሚያስተውል።",
            destinyAndWealth = "በሂሳብ፣ በሕክምና፣ በአስተዳደርና በምርምር ከፍተኛ ደረጃ ይደርሳል። ንብረቱ በተደራጀ መንገድ ያድጋል።",
            healthAdvice = "የአንጀትና የምግብ መፈጨት ሥርዓቱን ለመጠበቅ እሬትና ከሙን መውሰድ ይስማማዋል።",
            loveCompatibility = "ከሰውር (Taurus) እና ከጃዲ (Capricorn) ጋር እጅግ የተጣጣመ ነው።",
            ancientPrayer = "ሳቁኤል መልአክ ሰዳዴ ደዌ፤ ፈውሰኒ በምሕረትከ፤ ወዕቀበኒ በጽድቅከ ወበዕለተ መከራ።"
        ),
        ZodiacSign(
            id = 7,
            nameGeez = "ሚዛን (Mizan)",
            nameAmharic = "ሚዛን (Libra)",
            element = "ነፋስ (Air)",
            planet = "ዙህራ (Venus)",
            angel = "ቅዱስ ሰዲቅኤል (St. Sedikiel)",
            gemStone = "ኦፓል (Opal)",
            luckyDay = "ዓርብ (Friday)",
            luckyColor = "ሮዝ እና ሰማያዊ (Rose & Pastel Blue)",
            personality = "ፍትሐዊ፣ አስታራቂ፣ የውበት አድናቂ፣ ጨዋ፣ የሰውን ስሜት የሚረዳ፣ ከጭቅጭቅ የሚሸሽ የሰላም ሰው።",
            destinyAndWealth = "በዳኝነት፣ በሽምግልና፣ በኪነ-ጥበብና በንግድ አጋርነት ይበለጽጋል። የሰዎች ፍቅር ያገኘዋል።",
            healthAdvice = "የኩላሊትና የወገብ ጤንነትን መጠበቅ አለበት። ንጹሕ ውኃ አብዝቶ መጠጣት ይገባዋል።",
            loveCompatibility = "ከጀውዛ (Gemini) እና ከደለው (Aquarius) ጋር ታላቅ ፍቅር ይገጥመዋል።",
            ancientPrayer = "ሰዲቅኤል መላከ ጽድቅ ወፍትሕ፤ አቅንዕ ፍኖትየ ወፍትሕ ሊተ በጽድቅከ፤ ወአሰስል እምኔየ እኩየ።"
        ),
        ZodiacSign(
            id = 8,
            nameGeez = "አቅራብ (Aqrab)",
            nameAmharic = "ጊንጥ (Scorpio)",
            element = "ውሃ (Water)",
            planet = "መሪሕ (Mars)",
            angel = "ቅዱስ አናንኤል (St. Ananiel)",
            gemStone = "አሜቴስጢኖስ (Amethyst)",
            luckyDay = "ማክሰኞ (Tuesday)",
            luckyColor = "ጥቁር ቀይ እና ጥቁር (Dark Crimson & Black)",
            personality = "ምስጢራዊ፣ ታጋይ፣ ኃይለኛ ተጽዕኖ ፈጣሪ፣ በውስጡ ጥልቅ እሳት ያለው፣ ተስፋ የማይቆርጥ ጽኑ ልብ።",
            destinyAndWealth = "በፈተናዎች አልፎ ታላቅ ኃይልና ባለሀብት ይሆናል። ድብቅ ምስጢራትንና ሀብቶችን ፈልጎ ያገኛል።",
            healthAdvice = "የመራቢያ አካላትና የደም ጤንነትን መንከባከብ። በከርቤ መታጠን መንፈሱን ያረጋጋዋል።",
            loveCompatibility = "ከሰርጣን (Cancer) እና ከሑት (Pisces) ጋር የነፍስ ግንኙነት ይፈጥራል።",
            ancientPrayer = "አናንኤል መልአከ ኃይል፤ ሰብር ኃይሎሙ ለጸላዕትየ፤ ወአርኅቅ እምኔየ መንፈሰ ጽልመት።"
        ),
        ZodiacSign(
            id = 9,
            nameGeez = "ቀውስ (Qaws)",
            nameAmharic = "ቀስት (Sagittarius)",
            element = "እሳት (Fire)",
            planet = "ሙሽተሪ (Jupiter)",
            angel = "ቅዱስ ባርክኤል (St. Barkiel)",
            gemStone = "ቱርኮይዝ (Turquoise)",
            luckyDay = "ሐሙስ (Thursday)",
            luckyColor = "ወይን ጠጅ እና ወርቃማ (Purple & Gold)",
            personality = "ተስፈኛ፣ ተጓዥ፣ እውነተኛ፣ ፈላስፋ፣ ለነጻነቱ የሚሳሳ፣ ለጋስ እና ሰዎችን የሚያበረታታ።",
            destinyAndWealth = "በባህር ማዶ ጉዞ፣ በትምህርት፣ በሕግና በውጭ ንግድ የበለጸገ ሕይወት ይመራል።",
            healthAdvice = "የጭንና የጉበት ጤንነትን መጠበቅ። ዘይተ-ወይራና የተፈጥሮ ዕፅዋት ይስማሙታል።",
            loveCompatibility = "ከሐመል (Aries) እና ከአሰድ (Leo) ጋር የደመቀ የፍቅር ሕይወት ይኖረዋል።",
            ancientPrayer = "ባርክኤል መላከ በረከት፤ ባርክ ቤትየ ወንዋይየ፤ ወአብጽሐኒ ውስተ ፍኖተ ሰላም ዘእንበለ ዕንቅፋት።"
        ),
        ZodiacSign(
            id = 10,
            nameGeez = "ጃዲ (Jadi)",
            nameAmharic = "ፍየል (Capricorn)",
            element = "መሬት (Earth)",
            planet = "ዙሐል (Saturn)",
            angel = "ቅዱስ ሱርኤል (St. Suriel)",
            gemStone = "ጋርኔት (Garnet)",
            luckyDay = "ቀዳሜ (Saturday)",
            luckyColor = "ጥቁር እና ጠቆር ያለ ሰማያዊ (Black & Charcoal)",
            personality = "ትዕግሥተኛ፣ ታታሪ፣ አርቆ አሳቢ፣ ኃላፊነት የሚሰማው፣ ተራራ ወጪ፣ ሥርዓት አክባሪ።",
            destinyAndWealth = "እርምጃው ቀስ ብሎ ቢሆንም መጨረሻው ከፍተኛ ሥልጣንና ዘላቂ ሀብት ማፍራት ነው።",
            healthAdvice = "የአጥንት፣ የጥርስና የመገጣጠሚያ ሕመምን ለመከላከል በፀሐይ መሞቅና ወተት/ካልሲየም መውሰድ።",
            loveCompatibility = "ከሰውር (Taurus) እና ከሰንቡላ (Virgo) ጋር የተረጋጋ የዕድሜ ልክ ትዳር ይኖረዋል።",
            ancientPrayer = "ሱርኤል መልአክ ዐቃቤ ሕይወት፤ አፅንዓኒ ውስተ ተስፋየ፤ ወአድኅነኒ እምነፋሰ ሞት ወሕማም።"
        ),
        ZodiacSign(
            id = 11,
            nameGeez = "ደለው (Dalew)",
            nameAmharic = "ጋን (Aquarius)",
            element = "ነፋስ (Air)",
            planet = "ዙሐል (Saturn)",
            angel = "ቅዱስ ራጉኤል (St. Raguel)",
            gemStone = "አኳማሪን (Aquamarine)",
            luckyDay = "ቀዳሜ (Saturday)",
            luckyColor = "ኤሌክትሪክ ሰማያዊ (Electric Blue)",
            personality = "አዲስ አሳቢ፣ የሰብዓዊ መብት ወዳድ፣ ፈጣሪ፣ ራሱን የቻለ፣ ወዳጅ አፍቃሪ ግን የራሱ ዓለም ያለው።",
            destinyAndWealth = "በሳይንስ፣ በቴክኖሎጂ፣ በማኅበራዊ ሥራና በአዳዲስ ፈጠራዎች ታላቅ ስም ያተርፋል።",
            healthAdvice = "የደም ዝውውርንና የቁርጭምጭሚት አካባቢን መጠበቅ። ዘወትር እንቅስቃሴ ማድረግ።",
            loveCompatibility = "ከጀውዛ (Gemini) እና ከሚዛን (Libra) ጋር ከፍተኛ የአእምሮና የፍቅር ስምምነት አለው።",
            ancientPrayer = "ራጉኤል መልአከ ብርሃን ወሰላም፤ ተበቀል ሊተ እምጸላዕትየ፤ ወአብጽሐኒ ውስተ ብርሃንከ ዘዘለዓለም።"
        ),
        ZodiacSign(
            id = 12,
            nameGeez = "ሑት (Hut)",
            nameAmharic = "ዓሣ (Pisces)",
            element = "ውሃ (Water)",
            planet = "ሙሽተሪ (Jupiter)",
            angel = "ቅዱስ ረሙኤል (St. Remuel)",
            gemStone = "ጄድ እና ፔሪዶት (Jade)",
            luckyDay = "ሐሙስ (Thursday)",
            luckyColor = "የባህር አረንጓዴ እና ወይን ጠጅ (Sea Green & Violet)",
            personality = "መንፈሳዊ፣ ሕልመኛ፣ ሩኅሩኅ፣ የሰውን ስሜት ተካፋይ፣ ኪነ-ጥበባዊ፣ የዋህ ግን ጥልቅ አስተዋይ።",
            destinyAndWealth = "በመንፈሳዊ አገልግሎት፣ በሙዚቃ፣ በስዕልና በምጽዋት እጁ የተባረከ ይሆናል።",
            healthAdvice = "የእግርና የሰውነት መከላከያ ኃይልን መጠበቅ። ዝንጅብልና ሎሚ በሞቀ ውሃ መጠጣት።",
            loveCompatibility = "ከሰርጣን (Cancer) እና ከአቅራብ (Scorpio) ጋር የተዋበ የነፍስ ጓደኝነት ይመሰርታል።",
            ancientPrayer = "ረሙኤል መላከ ትንሣኤ፤ አብርህ ውስተ ጽልመትየ፤ ወአዕርግ ጸሎትየ ውስተ መንበረ ጸጋ።"
        )
    )

    fun calculateZodiac(seekerName: String, motherName: String): ZodiacCalculationResult {
        val sScore = calculateGematria(seekerName)
        val mScore = calculateGematria(motherName)
        val total = sScore + mScore
        // Remainder mod 12: 1..12
        val remainder = if (total % 12 == 0) 12 else (total % 12)
        val sign = signs.firstOrNull { it.id == remainder } ?: signs[0]

        return ZodiacCalculationResult(
            seekerName = seekerName,
            motherName = motherName,
            seekerScore = sScore,
            motherScore = mScore,
            totalScore = total,
            sign = sign,
            remainderMod12 = remainder
        )
    }
}
