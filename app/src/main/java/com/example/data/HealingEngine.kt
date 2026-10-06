package com.example.data

data class HealingHerb(
    val id: String,
    val nameAmharic: String,
    val nameGeez: String,
    val scientificName: String,
    val ailmentsTreated: List<String>,
    val preparationMethod: String,
    val applicationMethod: String, // መጠጣት, መታጠን, ማሸት, መታጠብ
    val ancientManuscriptPrayer: String,
    val amharicPrayerTranslation: String,
    val safetyAdvisory: String
)

object HealingEngine {
    val herbs = listOf(
        HealingHerb(
            id = "damakese",
            nameAmharic = "ዳማከሴ",
            nameGeez = "ዕፀ ሳቤቅ (ሳቤቅ)",
            scientificName = "Ocimum lamiifolium",
            ailmentsTreated = listOf("ብርድና ጉንፋን (Flu)", "የራስ ምታት (Migraine)", "የዓይነ ጥላ ድካም (Heavy Aura)"),
            preparationMethod = "ትኩስ የዳማከሴ ቅጠሎችን በእጅ በማሸት ጭማቂውን ማውጣት ወይም በሙቅ ውሃ ውስጥ ዘፍዝፎ መታጠን።",
            applicationMethod = "ጭማቂውን በአፍንጫ ማሽተት፣ ግንባርን ማሸት እና እንፋሎቱን መታጠን።",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ፈዋሴ ዱያን ሩፋኤል፤ በዝንቱ ዕፅ ፈውስ ሕማመ ርእስ ወዓይነ ጥላ ዘሰብእ።",
            amharicPrayerTranslation = "ሕሙማንን የምትፈውስ ቅዱስ ሩፋኤል ሆይ፤ በዚህ ዕፅ አማካኝነት የራስ ሕመምንና የጨለማን ድካም ፈውስ።",
            safetyAdvisory = "ለአይን በቀጥታ እንዳይነካ መጠንቀቅ። የደም ግፊት ያለባቸው ሰዎች ከመጠን በላይ ማሽተት የለባቸውም።"
        ),
        HealingHerb(
            id = "tena_adam",
            nameAmharic = "ጤናአዳም",
            nameGeez = "ዕፀ ሕይወት ዘአዳም",
            scientificName = "Ruta chalepensis",
            ailmentsTreated = listOf("የሆድ ቁርጠት (Stomach ache)", "የክፉ ዓይን መከላከያ (Evil eye)", "የምግብ አለመፈጨት"),
            preparationMethod = "የጤናአዳም ፍሬውንና ቅጠሉን በሻይ ወይም በቡና ውስጥ መክተት፣ ወይም በንጹሕ ውኃ በጥብጦ ማጣራት።",
            applicationMethod = "ጥቂት ጠብታ በሞቀ መጠጥ ውስጥ ጠጥቶ ማሳለፍ።",
            ancientManuscriptPrayer = "አስማተ ፈውስ ዘአቡሻህር፤ ኤሎሄ፣ ኤሎሄ፤ አድኅኖ ለገብርከ እምደዌ ከርሥ ወእምዓይነ ባላ።",
            amharicPrayerTranslation = "አምላክ ሆይ፤ ባሪያህን ከሆድ በሽታና ከሰው ክፉ ዓይን ጠብቀው ፈውሰውም።",
            safetyAdvisory = "ነፍሰ ጡር እናቶች በከፍተኛ መጠን እንዳይወስዱት በጥብቅ ይመከራል።"
        ),
        HealingHerb(
            id = "girawa",
            nameAmharic = "ግራዋ",
            nameGeez = "ዕፀ መራር",
            scientificName = "Vernonia amygdalina",
            ailmentsTreated = listOf("የሆድ ጥገኛ ትሎች (Intestinal parasites)", "የወባ ንዳድ (Malaria)", "የቆዳ ቁስል"),
            preparationMethod = "ቅጠሉን በደንብ አጥቦ በመውቀጥ ፈሳሹን አጥልሎ በጥቂቱ መውሰድ፤ ለቆዳ ደግሞ ቅጠሉን ማሸት።",
            applicationMethod = "በጠዋት በባዶ ሆድ ጥቂት ማንኪያ መጠጣት ወይም ቁስል ላይ ማሰር።",
            ancientManuscriptPrayer = "በስመ እግዚአብሔር አምላከ ጽድቅ፤ በዛቲ ዕፅ መራር አሰስል ኩሎ መርዐተ ደዌ ወጻዕረ ሥጋ።",
            amharicPrayerTranslation = "በእውነተኛው አምላክ ስም፤ በዚህ መራር ዕፅ የደዌን ሥርና የስጋን ጭንቀት ሁሉ አስወግድ።",
            safetyAdvisory = "መራርነቱ ከፍተኛ ስለሆነ በልክ ብቻ መጠቀምና ለህፃናት አለመስጠት።"
        ),
        HealingHerb(
            id = "tosign",
            nameAmharic = "ጦስኝ",
            nameGeez = "ዕፀ ዕፍረት",
            scientificName = "Thymus serrulatus",
            ailmentsTreated = listOf("የደረቅ ሳልና የጉሮሮ ሕመም (Cough)", "የደረት ውጋት (Chest tightness)", "የደም ግፊት ቁጥጥር"),
            preparationMethod = "የደረቀውን ወይም ጥሬውን ጦስኝ በፈላ ውሃ ውስጥ አንተክትኮ ከንጹሕ ማር ጋር ማዋሃድ።",
            applicationMethod = "እንደ ሻይ በሞቀ ሁኔታ ጠዋትና ማታ መጠጣት።",
            ancientManuscriptPrayer = "እግዚአብሔር ዘፈጠረ ዕፀዋተ ለፈውስ፤ አጽንዕ ሥጋየ ወአስተንፍስ ሕይወት ውስተ ጉሮሮየ።",
            amharicPrayerTranslation = "ዕፅዋትን ለፈውስ የፈጠርክ ጌታ ሆይ፤ ሥጋዬን አበርታ፣ ጉሮሮዬንም አረጋጋ።",
            safetyAdvisory = "ለአብዛኛው ሰው እጅግ ተስማሚና የጎንዮሽ ጉዳት የሌለው ድንቅ የደጋ ዕፅ ነው።"
        ),
        HealingHerb(
            id = "eret",
            nameAmharic = "እሬት",
            nameGeez = "ዕፀ ሰበር",
            scientificName = "Aloe abyssinica",
            ailmentsTreated = listOf("የእሳት ቃጠሎ (Burns)", "የቆዳ ድርቀትና ብጉር", "የፀጉር መነቀልና ድካም"),
            preparationMethod = "የእሬቱን ወላጅ ቆዳ ልጦ የውስጡን ጥርት ያለ ጄል መውሰድ።",
            applicationMethod = "በተጎዳው ቆዳ ወይም በጭንቅላት ቆዳ ላይ በቀስታ መቀባት።",
            ancientManuscriptPrayer = "በስመ አብ፤ ከመ ጽጌረዳ ወከመ ፅድ አሐድስ ወራዙትየ ወፈውሰኒ እምነዳደ እሳት።",
            amharicPrayerTranslation = "እንደ ጽጌሬዳና እንደ ፅድ ጎልማሳነቴን አድስ፤ ከቃጠሎም ፈውሰኝ።",
            safetyAdvisory = "የእሬቱ ቢጫ ፈሳሽ (Latex) ቆዳ እንዳያሳክክ ጥርት ያለውን ጄል ብቻ መጠቀም።"
        ),
        HealingHerb(
            id = "kerbe",
            nameAmharic = "ከርቤና ነጭ ዕጣን",
            nameGeez = "ከርቤ ወዕጣነ ሳባ",
            scientificName = "Commiphora myrrha & Boswellia",
            ailmentsTreated = listOf("የመንፈስ ጭንቀትና ፍርሃት (Anxiety)", "የመተንፈሻ ቱቦ ማጽዳት", "የክፉ መንፈስ አየር ማባረር"),
            preparationMethod = "በንጹሕ የከሰል እሳት ላይ ከርቤውንና ዕጣኑን በመጣል ማጨስ።",
            applicationMethod = "ጭሱን በክፍል ውስጥ ማሰራጨትና በመጠኑ መታጠን።",
            ancientManuscriptPrayer = "ይዕርግ ጸሎትየ ከመ ዕጣን በቅድሜከ፤ ወይርሐቅ እምቤቴ ኩሉ መንፈሰ ጽልመት ወደዌ።",
            amharicPrayerTranslation = "ጸሎቴ በፊትህ እንደ ዕጣን ትድረስ፤ የጨለማና የበሽታ መንፈስ ሁሉ ከቤቴ ይራቅ።",
            safetyAdvisory = "አስም ያለባቸው ሰዎች ከጭሱ ርቀው እንዲቀመጡ ማድረግ።"
        )
    )

    fun searchHerbs(query: String): List<HealingHerb> {
        if (query.isBlank()) return herbs
        return herbs.filter {
            it.nameAmharic.contains(query, ignoreCase = true) ||
            it.nameGeez.contains(query, ignoreCase = true) ||
            it.ailmentsTreated.any { ailment -> ailment.contains(query, ignoreCase = true) }
        }
    }
}
