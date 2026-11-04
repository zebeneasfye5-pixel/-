package com.example.data

data class GlobalGrimoireItem(
    val id: String,
    val titleAmharic: String,
    val titleOriginal: String,
    val originEra: String,
    val originalLanguage: String,
    val dangerLevel: String,
    val primaryPurpose: String,
    val originalIncantation: String,
    val amharicTranslation: String,
    val ritualInstructions: List<String>,
    val protectiveWarning: String,
    val historicalContext: String,
    val sacredSeal: String
)

object GlobalGrimoiresEngine {
    val grimoires = listOf(
        GlobalGrimoireItem(
            id = "key_of_solomon",
            titleAmharic = "የሰሎሞን ታላቅ ቁልፍ (ክላቪኩላ ሰሎሞኒስ)",
            titleOriginal = "Clavicula Salomonis (Key of Solomon)",
            originEra = "14ኛው - 15ኛው መቶ ክፍለ ዘመን (የጥንት ዕብራይስጥና ላቲን ቅጂ)",
            originalLanguage = "ዕብራይስጥ (Hebrew) እና ላቲን (Latin)",
            dangerLevel = "እጅግ አደገኛና ከፍተኛ ጥንቃቄ የሚሻ (High Astral Potency)",
            primaryPurpose = "የፕላኔቶችን መንፈሳዊ ኃይላት ማዘዣ፣ የመከላከያ ክበብ (Magic Circle) እና ፔንታክል ማተሚያ",
            originalIncantation = "«Per Adonai, Elohim, Agla, Tetragrammaton; O vos omnes spiritus, per virtutem horum nominum sacrorum obedite mihi et venite in pace!»",
            amharicTranslation = "«በአዶናይ፣ በኤሎሂም፣ በአግላና በቴትራግራማቶን ቅዱሳን የማይነገሩ ስሞች፤ እናንተ መንፈሳውያን ኃይላት ሆይ፣ በእነዚህ ቅዱሳን ስሞች ኃይል ታዘዙኝ፤ በሰላምም ቅረቡ!»",
            ritualInstructions = listOf(
                "በድንግል ብራና ላይ በሮማን ጭማቂ ቀለም የሰሎሞንን ታላቅ ፔንታክል መሳል",
                "ከኖራ ወይም ከነጭ ጨው የተሠራ የ፱ ጫማ (9-foot) የመከላከያ ክበብ መሬት ላይ መዘርጋት",
                "የብር ወይም የመዳብ ቀለበት በግራ እጅ ጣት ማድረግ",
                "በዕለተ ዓርብ ማታ በቬነስ (ዙህራ) ሰዓት ማዕጠንት እያጨሱ ቃሉን ማሰላሰል"
            ),
            protectiveWarning = "ያለ መከላከያ ክበብ (Magic Circle) እና ያለ ንጽሕና ፈጽሞ እንዳይሞከር፤ አእምሮን ሊያናውጥ የሚችል ከፍተኛ የስነ-ልቦና እና የመንፈስ ጫና አለው።",
            historicalContext = "በመካከለኛው ዘመን አውሮፓና በመካከለኛው ምሥራቅ የአይሁድ የካባላህ ሊቃውንት የተጠቀሙበት፤ በንጉሥ ሰሎሞን ጥበብ ላይ የተመሠረተ ታላቁ የዓለማችን ግሪሞየር ነው።",
            sacredSeal = "✡ ⛤ CLAVICULA SALOMONIS ⛤ ✡"
        ),
        GlobalGrimoireItem(
            id = "picatrix",
            titleAmharic = "ጋያት አል-ሀኪም (ፒካትሪክስ)",
            titleOriginal = "Ghayat al-Hakim / Picatrix",
            originEra = "10ኛው መቶ ክፍለ ዘመን (አንዳሉሲያና ባግዳድ - እስላማዊ ወርቃማ ዘመን)",
            originalLanguage = "ዐረብኛ (Arabic) እና ጥንታዊ ላቲን (Medieval Latin)",
            dangerLevel = "ከፍተኛ የከዋክብትና የቁስ ውህደት አደጋ (Astrological Talismanic Magic)",
            primaryPurpose = "የከዋክብትንና የፕላኔቶችን መንፈሳዊ ኃይል ወደ ብረት፣ ድንጋይና ቴልሰም የማውረድ ጥበብ",
            originalIncantation = "«Bismillahi al-Hakim; Ya Zuhal, Ya Mushtari, Ya Marikh; As'aluka bi-quwwati al-falak wa harakat al-nujum an tajliba li al-quwwah wa al-nasr al-a'zam!»",
            amharicTranslation = "«በጥበበኛው አምላክ ስም፤ አንተ ዙሐል (ሳተርን)፣ ሙሽተሪ (ጁፒተር) እና መሪሕ (ማርስ) ሆይ፤ በሰማያት ኃይልና በከዋክብት ሩጫ ታላቁን ኃይልና ድል ታጎናጽፉኝ ዘንድ እጠይቃለሁ!»",
            ritualInstructions = listOf(
                "ፕላኔቶች በከፍታ ማማቸው (Exaltation) ላይ በሚገኙበት ትክክለኛ ሰዓት ማዘጋጀት",
                "እንደየፕላኔቱ ብረት (ለምሳሌ ለሳተርን እርሳስ፣ ለፀሐይ ወርቅ፣ ለጨረቃ ብር) ቴልሰም መቅረጽ",
                "የዕጣንና የከርቤ ቅልቅል እያጨሱ ምስራቅ ዞሮ ቃሉን ማዜም"
            ),
            protectiveWarning = "በተሳሳተ የኮከብ ሰዓት ከተሰራ ተቃራኒ መከራና የጤና መታወክ ያስከትላል፤ የኮከብ አቆጣጠርን ጠንቅቆ ማወቅ ግድ ይላል።",
            historicalContext = "በአረብኛ «ጋያት አል-ሀኪም» (የጠቢቡ ግብ) ተብሎ የተጻፈውና ወደ ላቲን «ፒካትሪክስ» ተብሎ የተተረጎመው ይህ መጽሐፍ የአውሮፓን የህዳሴ ጠቢባን (እንደ ፊቺኖና አግሪጳ) ያነሳሳ የታሪክ መዝገብ ነው።",
            sacredSeal = "☽ ☿ PICATRIX TALISMAN ♀ ☉"
        ),
        GlobalGrimoireItem(
            id = "book_of_the_dead",
            titleAmharic = "የግብፅ መጽሐፈ ሙታን (ፐርት ኤም ህሩ)",
            titleOriginal = "Book of the Dead (Pert Em Hru - Going Forth by Day)",
            originEra = "1550 ቅድመ ክርስቶስ (ጥንታዊቷ ግብፅ - 18ኛው ሥርወ መንግሥት)",
            originalLanguage = "የጥንት ግብፅ ሂሮግሊፊክስ (Hieroglyphic)",
            dangerLevel = "የከርሰ ምድርና የሌሊት ዓለም ምስጢር (Underworld Netherworld Gates)",
            primaryPurpose = "በድኅረ ሞት ዓለም ጨለማን፣ እባቦችንና አጋንንትን ማሸነፊያ፣ የልብን ሚዛን ማለፊያ ጋሻ",
            originalIncantation = "«Nuk pu User, Nuk pu Ra; An-nek em hetemet; Em shet-ti em ba-i em khet-i; Un-na em ma'a kheru!»",
            amharicTranslation = "«እኔ የብርሃኑ አምላክ ነኝ፤ እኔ ራ ነኝ፤ በጨለማና በፈተና አልጠፋም፤ ነፍሴና ልቤ በጽድቅ ሚዛን ጸንተዋል፤ በብርሃን ወደፊት እወጣለሁ!»",
            ritualInstructions = listOf(
                "አረንጓዴ የጃስፐር ወይም የላፒስ ላዙሊ ድንጋይ በልብ ቅርጽ ቀርፆ የአንበሳ ምልክት መቅረጽ",
                "በንጹሕ የግብፅ የሎተስና የከርቤ ዘይት አካልን ማሸት",
                "ቃሉን በንጋት ፀሐይ መውጫ ወቅት ወደ ምስራቅ ተመልክቶ ማድረስ"
            ),
            protectiveWarning = "ለክፉ ዓላማ ወይም ለሌሎች መጎጃነት ፈጽሞ አይሰራም፤ የልብን ንጽሕናና እውነትን ብቻ የሚጠይቅ የፈርዖኖች ቃል ነው።",
            historicalContext = "በጥንታዊቷ ግብፅ ፈርዖኖችና ካህናት በፓፒረስ ላይ በስዕለት ጽፈው በመቃብራቸው የሚያኖሩት ታላቁ የሰው ልጅ የነፍስ ነጻነት መዝገብ ነው።",
            sacredSeal = "𓋹 𓂀 PERT EM HRU 𓊹 𓏤"
        ),
        GlobalGrimoireItem(
            id = "greek_magical_papyri",
            titleAmharic = "የግሪክና የግብፅ አስማታዊ ፓፒረሶች (PGM)",
            titleOriginal = "Papyri Graecae Magicae (Greek Magical Papyri)",
            originEra = "2ኛው ቅድመ ክርስቶስ - 5ኛው መቶ ክፍለ ዘመን (የሄለናዊት ግብፅ)",
            originalLanguage = "ጥንታዊ ግሪክኛ (Koine Greek) እና ዴሞቲክ ግብፅኛ",
            dangerLevel = "ኃይለኛ የሄርሜቲክና የጥሪ አስማት (Hermetic Voice Magic)",
            primaryPurpose = "የሄርሜስ ትሪስመጊስተስ፣ የሄካቴና የአፖሎ ምስጢራትን መግለጥ፣ የታወኩ ነፍሳትን ማረጋጋት",
            originalIncantation = "«Io Hekate, Chthonia, Enodia, Dadouchos; Phos pherein, Akrouroboros, Abrasax, Sabaoth; Epakouson mou tes phones!»",
            amharicTranslation = "«የመንገድና የምድር ጠባቂዋ ሆይ፤ የብርሃን ችቦ ተሸካሚ፣ አብራሳክስ፣ ጸባኦት፤ ድምፄን ስሙ፤ የተሰወረውን እውነት ግለጡልኝ!»",
            ritualInstructions = listOf(
                "በጨለማ ሌሊት በሦስት መንገዶች መገናኛ (Crossroads) ላይ ማዕጠንት ማጠን",
                "የነጭ ሽንኩርትና የሎሚ እጣን በከሰል ላይ መጣል",
                "ቃሉን በዝግታና በጠለቀ ድምፅ ፯ ጊዜ ማንበብ"
            ),
            protectiveWarning = "በፍርሃት ወይም በጥርጣሬ ውስጥ ሆኖ ማከናወን አእምሯዊ ቅዠትን ሊፈጥር ስለሚችል ከፍተኛ የአእምሮ ዝግጅት ያስፈልገዋል።",
            historicalContext = "በግብፅ እስክንድርያና በቴብስ የተገኙ፤ የግሪክን፣ የግብፅንና የባቢሎንን ጥበብ ያዋሃዱ ጥንታዊ ብራናዎችና ፓፒረሶች ናቸው።",
            sacredSeal = "⚡ ☥ ABRASAX HERMETICA ☥ ⚡"
        ),
        GlobalGrimoireItem(
            id = "ars_goetia",
            titleAmharic = "ሌሜጌቶን (አርስ ጎኤሺያ)",
            titleOriginal = "The Lesser Key of Solomon (Ars Goetia)",
            originEra = "17ኛው መቶ ክፍለ ዘመን (ጥንታዊ የአውሮፓና የመካከለኛው ምሥራቅ ምንጮች)",
            originalLanguage = "ላቲን (Latin) እና እንግሊዝኛ (Early Modern English)",
            dangerLevel = "በጣም ከፍተኛ አደጋ - የተከለከሉ መንፈሳዊ ኃይላት (Prohibited Evocation)",
            primaryPurpose = "የ፸፪ቱ ጥንታውያን ነገሥታትና አለቆች ማሰሪያ፣ የመንፈሳዊ ፈተናዎች መቆጣጠሪያ",
            originalIncantation = "«Conjuro vos per Deum Vivum, per Deum Verum, per Deum Sanctum; per Alpha et Omega; ut appareatis statim sine laesione corporis vel animae!»",
            amharicTranslation = "«በሕያው አምላክ ስም፣ በእውነተኛው አምላክ ስም፣ በቅዱሱ አምላክ ስም፤ በአልፋና በዖሜጋ ስም፤ በሥጋም ሆነ በነፍስ ላይ ያለምንም ጉዳት ታዘዙኝ!»",
            ritualInstructions = listOf(
                "ከንጹሕ ብር ወይም ከወርቅ የተሠራ የሰሎሞን የድል ማኅተም (Secret Seal of Solomon) በደረት ላይ ማንጠልጠል",
                "የመከላከያ ባለ አራት ማዕዘን ጋሻና ክበብ መሬት ላይ መዘርጋት",
                "ማዕጠንተ ከርቤና ዕጣን ያለማቋረጥ ማጨስ"
            ),
            protectiveWarning = "በዓለማችን እጅግ አደገኛ ከሚባሉ መጻሕፍት አንዱ ተደርጎ ይቆጠራል፤ ያለ ሙሉ መንፈሳዊ ትጥቅና ጥበቃ ወደዚህ መቅረብ ለሰው ልጆች ፈጽሞ አይመከርም።",
            historicalContext = "የሰሎሞንን ታሪክ መሠረት አድርገው በመካከለኛው ዘመን አውሮፓ ካህናትና ፈላስፎች በስውር ሲያነቧቸው ከነበሩት ምስጢራዊ መጻሕፍት ዋነኛው ነው።",
            sacredSeal = "⚔️ ⛧ ARS GOETIA SEAL ⛧ ⚔️"
        )
    )
}
