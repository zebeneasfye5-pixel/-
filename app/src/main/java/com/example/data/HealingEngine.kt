package com.example.data

data class HealingHerb(
    val id: String,
    val nameAmharic: String,
    val nameGeez: String,
    val traditionalFolkName: String,
    val scientificName: String,
    val geographicHabitat: String,
    val ailmentsTreated: List<String>,
    val harvestingRitual: String,
    val preparationMethod: String,
    val applicationMethod: String,
    val referenceCitation: String,
    val ancientManuscriptPrayer: String,
    val amharicPrayerTranslation: String,
    val safetyAdvisory: String
)

object HealingEngine {
    val herbs = listOf(
        HealingHerb(
            id = "etse_sabi",
            nameAmharic = "ዕፀ ሳቤዕ (ዕፀ ሳቤቅ)",
            nameGeez = "ዕፀ ሳቤቅ ዘይስሐቅ",
            traditionalFolkName = "«ሐረገ ሳቤቅ»፣ «የይስሐቅ በግ ሐረግ»፣ «የብረት አቅላጭ» ወይም «የስለትና ጥይት መከላከያ ሐረግ»",
            scientificName = "Traditional Sacred Cliff Vine (ዘፍጥረት ፳፪፥፲፫ / መጽሐፈ ገብረ ሕይወት)",
            geographicHabitat = "በከፍተኛ ተራራማ ገደሎችና በቋጥኝ አፋፍ ላይ፤ በተለይም በሰሜን ዋልድባ ገደል፣ በደብረ ዳሞ አፋፍ ቋጥኞች፣ በደብረ ሊባኖስ ገደላማ ዋሻዎች፣ በራስ ደጀንና በሰሜን ተራሮች ላይ በድንጋይና በቋጥኝ ተጣብቆ ይገኛል።",
            ailmentsTreated = listOf("የጥይትና የአረር መከላከያ (Bullet & Shrapnel Protection)", "የስለትና የሰይፍ መከላከያ (Blade Protection)", "ለብረት ማለስለሻና ማቅለጫ", "ለታላቅ ክብርና ሞገስ (Charisma)"),
            harvestingRitual = "በዕለተ ሐሙስ ሌሊት 9:00 ሰዓት ወይም እሑድ ፀሐይ ሳትወጣ፤ ሰውነቱን በንጽሕና ታጥቦ፤ ነጭ ልብስ ለብሶ፤ በ፯ የነጭ ዕጣን ፍሬ ወይም በንጹሕ የብር ሳንቲም ለመሬቱ ዋጋ ከፍሎ፤ «በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎተ ሳቤቅ ዘይስሐቅ...» የሚለውን ጸሎት ፯ ጊዜ ደግሞ፤ ወደ ምሥራቅ ዞሮ በግራ እጁ በስለት ሳይሆን በእንጨት ወይም በጥፍር ቆንጥጦ መቁረጥ (ብረት እንዳይነካው ይደረጋል፤ ብረት ካገኘው ኃይሉ ይቀንሳል)።",
            preparationMethod = "ሐረጉን በጥላ አድርቆ በብር ወይም በቀይ ቆዳ ክታብ ቋጥሮ በግራ ክንድ ወይም በአንገት ማሰር፤ ወይም በንጹሕ የወይራ ዘይት ውስጥ አንተክትኮ ዘይቱን በመላ አካል መቀባት።",
            applicationMethod = "ለጥይትና ስለት መከላከያ ክታቡን በግራ ክንድ ማሰር፤ ዘይቱን በጦርነትና በአደጋ ጊዜ ሰውነትን መቀባት፤ በኪስ መያዝ ለክብርና ለሞገስ ይሆናል።",
            referenceCitation = "መጽሐፈ ገብረ ሕይወት ወዕፀ ሳቤቅ ገጽ ፵፰ (ብራና ቁጥር ፻፲፭)፣ የደብረ ሊባኖስ ገዳም ቤተ መዛግብት",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ ሳቤቅ፤ በከመ አድኀንኮ ለይስሐቅ እምሰይፈ አቡሁ አብርሃም በበግዕ ዘተአሥረ በዕፀ ሳቤቅ፤ ከማሁ አድኅነኒ ሊተ ለገብርከ እምኩሉ ኲናት፣ እምሰይፍ፣ እምነዳደ እሳት ወእምአረር ዘይነቱግ፤ ዐቅበኒ በኃይለ መስቀልከ፤ በስመ ያህ፣ ኤልሻዳይ፣ አማኑኤል፤ አሜን።",
            amharicPrayerTranslation = "አንድ አምላክ በሚሆን በአብ በወልድ በመንፈስ ቅዱስ ስም፤ ስለ ዕፀ ሳቤዕ የተነገረ ጸሎት፤ ይስሐቅን በአባቱ በአብርሃም ሰይፍ ከመታረድ በዕፀ ሳቤቅ በተያዘው በግ እንዳዳንከው፤ እንዲሁ እኔን ከጦር፣ ከሰይፍ፣ ከሚነድ እሳትና ከሚተኮስ ጥይት (አረር) ሁሉ አድነኝ፤ በመስቀልህ ኃይል ጠብቀኝ፤ በአማኑኤልና በኤልሻዳይ ስም፤ አሜን።",
            safetyAdvisory = "ዕፁ በሚቆረጥበት ጊዜ ብረት እንዳይነካው በጥብቅ መጠንቀቅ፤ ለጽድቅና ከጥቃት ለመጠበቂያ ብቻ እንጂ ለክፉ አድማ ፈጽሞ አይሰራም።"
        ),
        HealingHerb(
            id = "damakese",
            nameAmharic = "ዳማከሴ",
            nameGeez = "ዕፀ ፈውስ ዳማከሴ",
            traditionalFolkName = "«ዳማከሴ»፤ በአንዳንድ ክፍለ ሀገራት «የራስ መድኃኒት» ወይም «የእንፋሎት ቅጠል» ይባላል።",
            scientificName = "Ocimum lamiifolium",
            geographicHabitat = "በኢትዮጵያ ደጋና ወይናደጋ ክፍለ ሀገራት (ሸዋ፣ ጎንደር፣ ወሎ፣ ጎጃም፣ ደብረ ሊባኖስ፣ ባሌ፣ ሲዳማና ሐረርጌ)፤ በቤት አጥር፣ በጓሮና በወንዝ ዳርቻዎች በብዛት ይገኛል።",
            ailmentsTreated = listOf("ብርድና ጉንፋን (Flu & Common Cold)", "የራስ ምታት (Migraine & Tension Headache)", "የአፍንጫ መታፈንና የሳይነስ ሕመም", "የደረት ውጋትና ትኩሳት"),
            harvestingRitual = "በዕለተ ሰኞ ወይም ሐሙስ ማለዳ ፀሐይ ሳትወጣ በንጽሕና ሆኖ፤ አቡነ ዘበሰማያት ጸልዮ ቅጠሉን በንጹሕ እጅ መቅጠፍ።",
            preparationMethod = "ትኩስ የዳማከሴ ቅጠሎችን በእጅ አሽቶ ጭማቂውን ማውጣት፤ ወይም በሙቅ ውኃ ውስጥ ዘፍዝፎ ማፍላት።",
            applicationMethod = "ጭማቂውን በአፍንጫ ማሽተት፣ ግንባርንና አናት ማሸት፣ እንፋሎቱን መታጠንና በጥቂቱ ከንጹሕ ማር ጋር ማለዳ በባዶ ሆድ መውሰድ።",
            referenceCitation = "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት ገጽ ፸፬ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ፈውሰ ርእስ ወጉንፋን፤ ፈዋሴ ዱያን ቅዱስ ሩፋኤል ሊቀ መላእክት፤ በዛቲ ዕፅ ዳማከሴ ፈውስ ሕማመ ርእስ፣ ጉንፋን ወኩሎ ፃዕረ ሥጋ ዘገብርከ፤ በከመ ፈወስኮ ለጦቢት እምደዌሁ ከማሁ ፈውስ ሕሙማነ በኃይለ መስቀሉ ለክርስቶስ፤ አሜን።",
            amharicPrayerTranslation = "በአብ በወልድ በመንፈስ ቅዱስ አንድ አምላክ ስም፤ የራስ ሕመም እና የጉንፋን መፈወሻ ጸሎት፤ ሕሙማንን የምትፈውስ የፈውስ መልአክ ቅዱስ ሩፋኤል ሆይ፤ በዚህ በዳማከሴ ዕፅ የራስ ምታትን፣ ጉንፋንንና የስጋ ጭንቀትን ሁሉ ከባሪያህ ላይ አርቅ፤ ጦቢትን ከዓይኑ ደዌ እንደፈወስከው እንዲሁ በክርስቶስ መስቀል ኃይል ፈውስ፤ አሜን።",
            safetyAdvisory = "ለአይን በቀጥታ እንዳይገባ መጠንቀቅ፤ እንፋሎቱ በሚታጠንበት ወቅት ራስን በልብስ መሸፈን።"
        ),
        HealingHerb(
            id = "patos",
            nameAmharic = "ዕፀ ጳጦስ",
            nameGeez = "ዕፀ ጳጦስ ዘደብረ ሲና",
            traditionalFolkName = "በባህላዊ አጠራር «ዕፀ ጳጦስ»፤ በደቡብና በደቡብ ምዕራብ «ሞአታ»፣ «መርቆ»፣ «ላንኩሶ»፤ በሸዋና ጎጃም «ቾዌይ» ወይም «የገዳም አጋም» በመባል ይታወቃል።",
            scientificName = "Dracaena steudneri (Schweinf. ex Engl.)",
            geographicHabitat = "በቆላማና ወይናደጋ ገደላማ ሥፍራዎች፣ በተከዜ ሸለቆ፣ በዋልድባ ገዳማት ጠረፍ፣ በአባይ በርሃና በሸዋ ገደሎች ውስጥ ይገኛል።",
            ailmentsTreated = listOf("የክፉ መንፈስና የዛር ቁራኛ ማባረር", "የዓይነ ጥላና የድግምት መከላከያ", "የእጅና የእግር ውጋት", "የክፉ ሕልም መሻሪያ"),
            harvestingRitual = "በዕለተ ሐሙስ ሌሊት 10:00 ሰዓት ገመናውን በጨርቅ ሸፍኖ፤ ጸሎተ ሰሎሞንን ደግሞ፤ ጥቁር ዕጣን አጭሶና በብር ሳንቲም ዋጋ ሰጥቶ በስመ ሥላሴ በስለት መቁረጥ።",
            preparationMethod = "ሥሩንና ቅርፊቱን በደረቁ በጥላ ሥር አድርቆ በመውቀጥ በደቃቁ ማድቀቅ፤ ከወይራ ዘይት ወይም ከእርጎ ጋር ማዋሃድ።",
            applicationMethod = "ለዓይነ ጥላና ለመስተፋቅር በክንድ ወይም በአንገት በክታብ ማሰር፤ ወይም በጥቂቱ በከሰል ላይ በማጨስ መታጠን።",
            referenceCitation = "መጽሐፈ ጳጦስ ወአስማተ አጋንንት ገጽ ፶፩ (ብራና ቁጥር ፻፲፪)፣ የዋልድባ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ ጳጦስ፤ በስመ ያህ፣ ኤልሻዳይ፣ ጸባኦት፤ በከመ አርአይኮ ለሙሴ በደብረ ሲና፤ ከማሁ አርእየኒ ኃይለከ በዛቲ ዕፅ፤ ይርሐቁ አጋንንት ወኩሎሙ መናፍስተ ጽልመት እምላዕለ ዝንቱ ስም፤ በኃይለ ዝንቱ ቃል ይትፈታሕ ኩሉ መሠሪያት፤ አሜን።",
            amharicPrayerTranslation = "አንድ አምላክ በሚሆን በአብ በወልድ በመንፈስ ቅዱስ ስም፤ ስለ ዕፀ ጳጦስ የተነገረ ጸሎት፤ በደብረ ሲና ለሙሴ ኃይልህን እንዳሳየህ እንዲሁ በዚህ ዕፅ ኃይልህን ግለጥ፤ አጋንንትና የጨለማ መናፍስት ሁሉ ከዚህ ሰው ላይ ይራቁ፤ በቃሉ ኃይል ድግምት ሁሉ ይፈታ፤ አሜን።",
            safetyAdvisory = "በአፍ ወደ ሆድ እንዳይገባ በጥብቅ ይጠንቀቁ፤ ለመታጠንና በክታብ ለመያዝ ብቻ እንዲውል ይመከራል።"
        ),
        HealingHerb(
            id = "debdabe",
            nameAmharic = "ዕፀ ደብዳቤ",
            nameGeez = "ዕፀ ደብዳቤ ወመክስተ ኃይል",
            traditionalFolkName = "በባህላዊ አጠራር «ዶግ»፣ «ሐረግ ምድር» ወይም «የምድር ሙጃ» ይባላል።",
            scientificName = "Stephania abyssinica",
            geographicHabitat = "በደጋና ወይናደጋ ተራራማ ሥፍራዎች (ሰሜን ተራሮች፣ ደብረ ታቦር፣ መንዝ፣ ዋሸራና የደብረ ሊባኖስ ጥቅጥቅ ደኖች)።",
            ailmentsTreated = listOf("የጥይትና የስለት መከላከያ ጋሻ", "የዓይነ ጥላና የመተት መፍትሔ", "ድንገተኛ ፍርሃትና ጭንቀት ማጥፋት"),
            harvestingRitual = "በዕለተ ረቡዕ ማታ ወይም ሐሙስ ማለዳ ንጹሕ ነጭ ልብስ ለብሶ፤ በ፯ የዕጣን ፍሬ አክብሮ፤ ጸሎተ መፍትሔ ሥራይን ፫ ጊዜ ደግሞ በንጽሕና ሥሩን መቆፈር።",
            preparationMethod = "የዕፀ ደብዳቤን ሥር በደንብ አጥቦ ማድረቅ፤ በትንሹ ደቁሶ በንጹሕ ውኃ ማጥለል ወይም በቆዳ ሰፍቶ ማዘጋጀት።",
            applicationMethod = "ጥቂት ጠብታ በውኃ አጥልሎ ጠዋት በባዶ ሆድ መቅመስ፤ ለጥበቃ ደግሞ በቀይ ቆዳ ተሰፍቶ በክንድ ወይም በአንገት ይታሰራል።",
            referenceCitation = "መጽሐፈ መፍትሔ ሥራይ ወዕፀ ደብዳቤ ገጽ ፴፱ (ብራና ቁጥር ፷፯)፣ የጣና ቂርቆስ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ጸሎተ ዕፀ ደብዳቤ፤ በስመ አልፋ ወዖሜጋ፤ ይትከደን ሰላም ወይቁም ጋሻ ዲበ ነፍስየ ወሥጋየ፤ ከመ ኢይቅረበኒ አረር፣ ሰይፍ ወኩናት፤ በስመ ቅዱስ ሚካኤል ወገብርኤል ይሠረር ጋሻ መለኮት፤ አሜን።",
            amharicPrayerTranslation = "በአብ በወልድ በመንፈስ ቅዱስ ስም፤ የዕፀ ደብዳቤ ጸሎት፤ ጥይት፣ ሰይፍና ጦር ወደ እኔ እንዳይቀርብ በነፍሴና በሥጋዬ ላይ መንፈሳዊ ጋሻ ይቁም፤ በቅዱስ ሚካኤልና ገብርኤል ስም የመለኮት ጥበቃ ይጽና፤ አሜን።",
            safetyAdvisory = "በመጠን ብቻ መጠቀም፤ ንጽሕናን መጠበቅ ግዴታ ነው።"
        ),
        HealingHerb(
            id = "mesewer",
            nameAmharic = "ዕፀ መሰውር",
            nameGeez = "ዕፀ መሰውር ዘሄኖክ ወኤልያስ",
            traditionalFolkName = "በባህላዊ አጠራር «ምስርች»፣ «እንጭብር» ወይም «የአጋም ዘመድ» ተብሎ ይጠራል።",
            scientificName = "Clutia lanceolata / Ceropegia",
            geographicHabitat = "በጥንታዊ ገዳማት ደኖች (ዋልድባ፣ ጣና ቂርቆስ፣ ማኅበረ ሥላሴ፣ ባሌና ሰሜን ሸለቆዎች) ውስጥ ይገኛል።",
            ailmentsTreated = listOf("ከጠላትና ከአደጋ መሰወሪያ", "ከክፉ ዓይንና ከተንኮል መጠበቂያ", "በችግር ጊዜ መንገድ ማቅናት"),
            harvestingRitual = "በዕለተ ዓርብ ማታ በ፯:00 ሰዓት ወይም እሑድ ፀሐይ ሳትወጣ፤ ፯ ጊዜ «ከመ ሄኖክ ወኤልያስ ከውለኒ እምዓይነ ፀር» እያሉ በብር ሳንቲም ዋጋ ሰጥቶ መቁረጥ።",
            preparationMethod = "ሥሩንና ቅጠሉን በንጽሕና ወቅጦ ከጥቁር ሰሊጥ ዘይትና ከንጹሕ የወይራ ዘይት ጋር ማዋሃድ።",
            applicationMethod = "ግንባርንና ዓይንን በጥቂቱ መቀባት፤ እንዲሁም በኪስ ወይም በአንገት በክታብ መያዝ።",
            referenceCitation = "መጽሐፈ ዕፀ መሰውር ወኅቡዓት ገጽ ፳፯ (ብራና ቁጥር ፹፪)፣ የጣና ቂርቆስ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ መሰውር፤ በከመ ከወንኮሙ ለሄኖክ ወለኤልያስ እምዓይነ ኃጥአን፤ ከማሁ ሰውረኒ እምዓይነ ፀር ወጸላኢ፤ አዖ፣ ፈዖ፣ ኤልሻዳይ፤ ዕፅም ዓይኖሙ ወዕበስ አእምሮቶሙ ከመ ኢይርአዩኒ፤ አሜን።",
            amharicPrayerTranslation = "አንድ አምላክ በሚሆን በአብ በወልድ በመንፈስ ቅዱስ ስም፤ የመሰወሪያ ጸሎት፤ ሄኖክንና ኤልያስን ከዓመፀኞች ዓይን እንደሰወርካቸው እንዲሁ እኔን ከጠላት ዓይን ሰውረኝ፤ እንዳያዩኝና እንዳያስተውሉኝ ዓይናቸውን ጋርድ፤ አሜን።",
            safetyAdvisory = "ለክፉ ነገር ወይም ለስርቆት ፈጽሞ መጠቀም የተከለከለ ነው፤ ከጠላት አደጋ ለመዳን ብቻ የታዘዘ ነው።"
        ),
        HealingHerb(
            id = "libanos",
            nameAmharic = "ዕፀ ሊባኖስ (ነጭ ዕጣን ዛፍ)",
            nameGeez = "ዕፀ ሊባኖስ ወዕጣነ ሳባ",
            traditionalFolkName = "በባህላዊ አጠራር «የነጭ ዕጣን ዛፍ» ወይም «ጥንጅት» ይባላል።",
            scientificName = "Boswellia papyrifera",
            geographicHabitat = "በተከዜ ሸለቆ፣ በወጋግ፣ በመተማ፣ በደቡብ ኦሮሚያና በሰሜን ድንጋያማ ሸለቆዎች።",
            ailmentsTreated = listOf("የመንፈስ ጭንቀትና ፍርሃት", "የመተንፈሻ አካላት ማጽዳት", "የቤትና የሰውነት በረከት መሳብ"),
            harvestingRitual = "በዕለተ ሐሙስ ጠዋት ፀሐይ ስትወጣ፤ ጸሎተ ባርክኤልን ደግሞ፤ የዛፉን ሙጫና ቅርፊት በንጹሕ ቢላዋ መግፈፍ።",
            preparationMethod = "ሙጫውን ከነጭ ከርቤ ጋር በማደባለቅ ማድረቅና በንጹሕ እሳት ላይ ማጨስ።",
            applicationMethod = "ጭሱን በክፍል ውስጥ ማሰራጨትና ማለዳ ማታ በመጠኑ መታጠን።",
            referenceCitation = "መጽሐፈ ሊባኖስ ወመዓዛ ሠናይ ገጽ ፵፫ (ብራና ቁጥር ፺፬)፣ የደብረ ዳሞ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ይዕርግ ጸሎትየ ከመ ዕጣን በቅድሜከ፤ ወይርሐቅ እምቤቴ ኩሉ መንፈሰ ጽልመት ወደዌ፤ በስመ ዑራኤል መልአክ አብርህ ውስተ ልብየ፤ አሜን።",
            amharicPrayerTranslation = "በአብ በወልድ በመንፈስ ቅዱስ ስም፤ ጸሎቴ በፊትህ እንደ ዕጣን መዓዛ ትድረስ፤ የጨለማና የበሽታ መንፈስ ሁሉ ከቤቴ ይራቅ፤ በቅዱስ ዑራኤል ስም በልቤ ውስጥ ብርሃን ይብራ፤ አሜን።",
            safetyAdvisory = "አስም ያለባቸው ሰዎች ከጭሱ ርቀው እንዲቀመጡ ማድረግ።"
        ),
        HealingHerb(
            id = "yodit",
            nameAmharic = "ዕፀ ዮዲት (ቀጠጥና)",
            nameGeez = "ዕፀ ዮዲት ወመፍትሔ መርዝ",
            traditionalFolkName = "በባህላዊ አጠራር «ቀጠጥና»፣ «አቱች» ወይም «የሆድ መድኃኒት» ይባላል።",
            scientificName = "Echinops ellenbeckii",
            geographicHabitat = "በጎጃም፣ ጎንደር፣ ወሎና ሸዋ እርጥበታማ ወንዝ ዳርቻዎችና ገደላማ ሜዳዎች።",
            ailmentsTreated = listOf("የሆድ መርዝና የተበላ ነገር ማርከሻ", "የሆድ ቁርጠትና ድርቀት", "የመርዘኛ እባብና ጊንጥ ንክሻ ማዳኛ"),
            harvestingRitual = "በዕለተ ሰኞ ማለዳ፤ ፱ ጊዜ አቡነ ዘበሰማያት ጸሎት አድርሶ በንጹሕ ቢላ መቆፈር።",
            preparationMethod = "ሥሩን በደንብ አጥቦ በሙቀጫ መውቀጥ፤ በወተት ወይም በጥቂት ማር ማፍላት።",
            applicationMethod = "ጠዋት በባዶ ሆድ አንድ ሲኒ መጠጣት፤ ለንክሻ ከሆነ ደግሞ በቁስሉ ላይ ማሰር።",
            referenceCitation = "መጽሐፈ ፈውስ ወቀጠጥና ገጽ ፷፪ (ብራና ቁጥር ፶፭)፣ የጎንደር ባዕታ ቤተ መዛግብት",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ በስመ እግዚአብሔር ፈዋሲ፤ አውፅእ ኩሎ መርዘ ከይሲ ወሕማመ ከርሥ፤ ይኩን ፈውስ ለነፍስ ወለሥጋ፤ አሜን።",
            amharicPrayerTranslation = "በአብ በወልድ በመንፈስ ቅዱስ ስም፤ ፈዋሽ በሆነው በእግዚአብሔር ስም፤ የእባቡን መርዝና የሆዱን ሕመም ሁሉ አስወግድ፤ ለነፍስና ለሥጋ ፈውስ ይሁን፤ አሜን።",
            safetyAdvisory = "መጠኑ እንዳይበዛ በአንድ ሲኒ ብቻ መገደብ።"
        ),
        HealingHerb(
            id = "tena_adam",
            nameAmharic = "ዕፀ ሕይወት (ጤናአዳም)",
            nameGeez = "ዕፀ ሕይወት ዘአዳም",
            traditionalFolkName = "ጤናአዳም (የአዳም ጤና)",
            scientificName = "Ruta chalepensis",
            geographicHabitat = "በኢትዮጵያ ደጋና ወይናደጋ ክፍለ ሀገራት በሙሉ በጓሮ የሚበቅል ተወዳጅ ዕፅ።",
            ailmentsTreated = listOf("የሆድ ቁርጠት (Stomach ache)", "የክፉ ዓይን መከላከያ (Evil eye)", "የምግብ አለመፈጨትና የሆድ ንፋት"),
            harvestingRitual = "በዕለተ እሑድ ወይም ረቡዕ ማለዳ በንጹሕ እጅ መቅጠፍ።",
            preparationMethod = "የጤናአዳም ፍሬውንና ቅጠሉን በሻይ ወይም በቡና ውስጥ መክተት፤ ወይም በንጹሕ ውኃ በጥብጦ ማጣራት።",
            applicationMethod = "ጥቂት ጠብታ በሞቀ መጠጥ ውስጥ ጠጥቶ ማሳለፍ፤ ፍሬውን በኪስ መያዝ።",
            referenceCitation = "መጽሐፈ ፈውስ ገጽ ፸፭ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
            ancientManuscriptPrayer = "አስማተ ፈውስ ዘአቡሻህር፤ ኤሎሄ፣ ኤሎሄ፣ ኤልሻዳይ፤ አድኅኖ ለገብርከ እምደዌ ከርሥ ወእምዓይነ ባላ ወእምኩሉ ፍላጻ ፀር፤ አሜን።",
            amharicPrayerTranslation = "አምላክ ሆይ፣ ሁሉን ቻይ አምላክ ሆይ፤ ባሪያህን ከሆድ በሽታ፣ ከሰው ክፉ ዓይንና ከጠላት ፍላጻ ጠብቀው ፈውሰውም፤ አሜን።",
            safetyAdvisory = "ነፍሰ ጡር እናቶች በከፍተኛ መጠን እንዳይወስዱት በጥብቅ ይመከራል።"
        ),
        HealingHerb(
            id = "girawa",
            nameAmharic = "ዕፀ መራር (ግራዋ)",
            nameGeez = "ዕፀ መራር ወፈውሰ ከርሥ",
            traditionalFolkName = "ግራዋ",
            scientificName = "Vernonia amygdalina",
            geographicHabitat = "በሁሉም የኢትዮጵያ ክልሎች በወይናደጋና ቆላማ መስኮች በብዛት ይገኛል።",
            ailmentsTreated = listOf("የሆድ ትሎች (Intestinal parasites)", "የወባ ንዳድ (Malaria)", "የጉበትና የሆድ ጽዳት", "የቆዳ ቁስል"),
            harvestingRitual = "በማንኛውም ቀን ጠዋት በንጹሕ እጅ መቅጠፍ።",
            preparationMethod = "የግራዋን ቅጠል በሙቀጫ መውቀጥ፤ ጭማቂውን በማጣራት ከማር ጋር ማዋሃድ፤ ለቁስል ቅጠሉን ማሸት።",
            applicationMethod = "ጠዋት በባዶ ሆድ በትንሽ ማንኪያ መውሰድ፤ ለቆዳ ቁስል ማሰር።",
            referenceCitation = "መጽሐፈ ፈውስ ገጽ ፸፮ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
            ancientManuscriptPrayer = "በስመ እግዚአብሔር አምላከ ጽድቅ፤ በዛቲ ዕፅ መራር አሰስል ኩሎ መርዐተ ደዌ ወፃዕረ ሥጋ፤ ወአውፅእ ኩሎ ሕማመ ከርሥ፤ አሜን።",
            amharicPrayerTranslation = "በእውነተኛው አምላክ ስም፤ በዚህ መራር ዕፅ የደዌን ሥርና የስጋን ጭንቀት ሁሉ አስወግድ፤ የሆድንም ሕመም አውጣ፤ አሜን።",
            safetyAdvisory = "መራርነቱ ከፍተኛ ስለሆነ በልክ ብቻ መጠቀም።"
        ),
        HealingHerb(
            id = "tosign",
            nameAmharic = "ዕፀ ዕፍረት (ጦስኝ)",
            nameGeez = "ዕፀ ዕፍረት ወሰናይት",
            traditionalFolkName = "ጦስኝ",
            scientificName = "Thymus serrulatus",
            geographicHabitat = "በሰሜንና ደቡብ ወሎ፣ ጎንደር፣ ሰሜን ሸዋ ከፍተኛ ደጋማ ተራሮች (ደብረ ሲና፣ ጉና፣ መንዝ)።",
            ailmentsTreated = listOf("የደረቅ ሳልና የጉሮሮ ሕመም (Cough)", "የደረት ውጋት (Chest tightness)", "የደም ግፊት ማረጋጋት"),
            harvestingRitual = "በጸደይና በበጋ ወራት በንጽሕና ተሰብስቦ በጥላ ሥር ማድረቅ።",
            preparationMethod = "የደረቀውን ወይም ጥሬውን ጦስኝ በፈላ ውሃ ውስጥ አንተክትኮ ከንጹሕ ማር ጋር ማዋሃድ።",
            applicationMethod = "እንደ ሻይ በሞቀ ሁኔታ ጠዋትና ማታ መጠጣት።",
            referenceCitation = "መጽሐፈ ፈውስ ገጽ ፸፯ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
            ancientManuscriptPrayer = "እግዚአብሔር ዘፈጠረ ዕፀዋተ ለፈውስ፤ አጽንዕ ሥጋየ ወአስተንፍስ ሕይወት ውስተ ጉሮሮየ፤ ወአብርድ እሳተ ደዌ፤ አሜን።",
            amharicPrayerTranslation = "ዕፅዋትን ለፈውስ የፈጠርክ ጌታ ሆይ፤ ሥጋዬን አበርታ፣ ጉሮሮዬንም አረጋጋ፤ የደዌንም እሳት አቀዝቅዝ፤ አሜን።",
            safetyAdvisory = "ለአብዛኛው ሰው እጅግ ተስማሚና የጎንዮሽ ጉዳት የሌለው ድንቅ የደጋ ዕፅ ነው።"
        ),
        HealingHerb(
            id = "eret",
            nameAmharic = "ዕፀ ሰበር (እሬት)",
            nameGeez = "ዕፀ ሰበር ወጽጌ",
            traditionalFolkName = "እሬት (የደጋና የቆላ እሬት)",
            scientificName = "Aloe debrana / abyssinica",
            geographicHabitat = "በቆላና ወይናደጋ ድንጋያማ ቦታዎች፣ በሸለቆዎችና በረሃማ ሥፍራዎች።",
            ailmentsTreated = listOf("የእሳት ቃጠሎ (Burns)", "የቆዳ ድርቀትና ብጉር", "የፀጉር መነቀልና ድካም", "የቆዳ ቁስል"),
            harvestingRitual = "በቢላዋ የሥጋ ቅጠሉን በጥንቃቄ ቆርጦ የውስጡን ጄል ማውጣት።",
            preparationMethod = "የእሬቱን ወላጅ ቆዳ ልጦ የውስጡን ጥርት ያለ ጄል መውሰድ፤ ከማር ጋር ማዋሃድ።",
            applicationMethod = "በተጎዳው ቆዳ ወይም በጭንቅላት ቆዳ ላይ መቀባት።",
            referenceCitation = "መጽሐፈ ፈውስ ገጽ ፸፰ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
            ancientManuscriptPrayer = "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ከመ ጽጌረዳ ወከመ ፅድ አሐድስ ወራዙትየ ወፈውሰኒ እምነዳደ እሳት ወእምደዌ ቈላ፤ አሜን።",
            amharicPrayerTranslation = "በአብ በወልድ በመንፈስ ቅዱስ ስም፤ እንደ ጽጌሬዳና እንደ ፅድ ጎልማሳነቴን አድስ፤ ከቃጠሎና ከቆዳ ደዌም ፈውሰኝ፤ አሜን።",
            safetyAdvisory = "ጥርት ያለውን ጄል ብቻ መጠቀም፤ ቢጫውን መራር ፈሳሽ ማስወገድ።"
        )
    )

    fun searchHerbs(query: String): List<HealingHerb> {
        if (query.isBlank()) return herbs
        return herbs.filter {
            it.nameAmharic.contains(query, ignoreCase = true) ||
            it.nameGeez.contains(query, ignoreCase = true) ||
            it.traditionalFolkName.contains(query, ignoreCase = true) ||
            it.geographicHabitat.contains(query, ignoreCase = true) ||
            it.ailmentsTreated.any { ailment -> ailment.contains(query, ignoreCase = true) }
        }
    }
}
