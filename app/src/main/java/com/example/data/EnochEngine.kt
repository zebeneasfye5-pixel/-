package com.example.data

data class EnochChapter(
    val id: String,
    val titleGeez: String,
    val titleAmharic: String,
    val summary: String,
    val sacredManuscriptPassage: String,
    val astronomicalWisdom: String,
    val spiritualMeaning: String
)

object EnochEngine {
    val chapters = listOf(
        EnochChapter(
            id = "luminaries",
            titleGeez = "መጽሐፈ ብርሃናት ዘሰማይ (፸፪ - ፹፪)",
            titleAmharic = "የሰማይ ብርሃናት ዑደትና ፮ቱ የፀሐይ ደጆች",
            summary = "ሄኖክ በሰማይ ዙሪያ ያሉትን ስድስቱን የፀሐይና የጨረቃ መውጫና መግቢያ ደጆች (Gates) በዑራኤል መሪነት የተመለከተበት ታላቅ የቀመር ምዕራፍ።",
            sacredManuscriptPassage = """
                «ወዝንቱ ውእቱ ሥርዓተ ብርሃናት ኩሎሙ ዘአርአየኒ ዑራኤል መልአክ ቅዱስ ዘውእቱ ዐቃቤ ብርሃናት።
                ወርኢኩ ስድስተ ደጆተ እንተ ቦን ይወጽእ ፀሐይ ወስድስተ ደጆተ እንተ ቦን ይዐርብ ፀሐይ፤
                ወጨረቃሂ ትወጽእ ወትዐርብ በእሊአሆን ደጆች።»
            """.trimIndent(),
            astronomicalWisdom = "በዚህ ቀመር መሠረት የፀሐይ ጉዞ ፫፻፷፬ (364) ዕለታት ሲሆን ፬ቱን ወቅቶች (መፀው፣ በጋ፣ ፀደይ፣ ክረምት) ያካተተ ፍጹም የሰማይ ሰዓት ነው።",
            spiritualMeaning = "የተፈጥሮ ሕግጋትና የሰማይ ኃይላት ያለ ምንም ማወላወል በፈጣሪያቸው ሥርዓት እንደሚመላለሱ ያሳያል።"
        ),
        EnochChapter(
            id = "seven_heavens",
            titleGeez = "ራእየ ሄኖክ በሰባቱ ሰማያት",
            titleAmharic = "ሰባቱ ሰማያትና የመላእክት ሠራዊት",
            summary = "ሄኖክ ከመሬት ተነስቶ እስከ ታላቁ የእሳትና የበረዶ መንበር ድረስ ያደረገው ሰማያዊ ጉዞ።",
            sacredManuscriptPassage = """
                «ወአዕረጉኒ ውስተ ሰማይ ወርኢኩ መንበረ እሳት ወኪሩቤል ዘይኬልልዎ፤
                ወድምፀ መላእክት ከመ ድምፀ ማያት ብዙኅ። ወይቤለኒ እግዚአብሔር፡
                ኢትፍራህ ሄኖክ ብእሴ ጽድቅ ወጸሐፌ ጽድቅ፤ ቅረብ ኀቤየ ወስምዕ ቃለየ።»
            """.trimIndent(),
            astronomicalWisdom = "ሰባቱ ሰማያት ከጠፈር ፕላኔቶችና ከከዋክብት ኅብረ-ኮከብ ጋር ያላቸው ምስጢራዊ መስተጋብር ተቀምጧል።",
            spiritualMeaning = "ጽድቅና እውነት የሰው ልጅን ከመሬት አፈር አንስቶ እስከ ሰማያዊ ክብር እንደሚያደርሱት ያስተምራል።"
        ),
        EnochChapter(
            id = "twelve_winds",
            titleGeez = "፲፪ቱ አናቅጸ ነፋሳት (ምዕራፍ ፸፮)",
            titleAmharic = "አሥራ ሁለቱ የነፋስ ደጆች",
            summary = "በምድር ዙሪያ ያሉ ፲፪ ደጆች፤ አራቱ የምሕረትና የዝናብ፣ ስምንቱ ደግሞ የመቅሰፍትና የሙቀት ንፋሳት የሚወጡባቸው።",
            sacredManuscriptPassage = """
                «ወርኢኩ ፲፪ተ አናቅጸ ውስተ አጽናፈ ምድር እንተ እምኔሆን ይወጽኡ ነፋሳት ኩሎሙ፤
                ሠለስቱ እምጽባሕ፣ ሠለስቱ እምዓረብ፣ ሠለስቱ እምመስዕ፣ ወሠለስቱ እምደቡብ።
                አርባዕቱ እምኔሆሙ ነፋሳተ በረከት ወስምንቱ ነፋሳተ መቅሠፍት።»
            """.trimIndent(),
            astronomicalWisdom = "የአየር ንብረት፣ የዝናብ ወቅቶች፣ እና የከባቢ አየር እንቅስቃሴዎች የቀደምት ሳይንሳዊ መሠረት።",
            spiritualMeaning = "እግዚአብሔር ምድርን በልክና በሚዛን እንደፈጠራት ማስተዋል ነው።"
        ),
        EnochChapter(
            id = "archangels",
            titleGeez = "መላእክተ ኃይላት ወአስማቲሆሙ",
            titleAmharic = "ሊቃነ መላእክትና ኃላፊነታቸው",
            summary = "ዑራኤል (የብርሃናት መሪ)፣ ሚካኤል (የህዝብ ጠባቂ)፣ ሩፋኤል (ፈዋሽ)፣ ገብርኤል (የገነት ጠባቂ)፣ ፋኑኤል (ተስፋና ንስሐ)።",
            sacredManuscriptPassage = """
                «ዑራኤል አሐዱ እምቅዱሳን መላእክት ዘዲበ ነጎድጓድ ወድልቅልቅ፤
                ሩፋኤል ዘዲበ ኩሉ ደዌ ወዲበ ኩሉ ቁስለ ውሉደ ሰብእ፤
                ገብርኤል ዘዲበ ገነት ወዲበ አርዌ ወዲበ ኪሩቤል፤
                ሚካኤል ዘተወክፈ ዲበ ኂሩተ ሰብእ ወዲበ ህዝብ።»
            """.trimIndent(),
            astronomicalWisdom = "እያንዳንዱ መልአክ ከአጽናፈ ዓለም የተፈጥሮ ሕግና ንጥረ-ነገር (እሳት፣ ውሃ፣ ነፋስ፣ መሬት) ጋር ተያይዟል።",
            spiritualMeaning = "የሰው ልጅ በብቸኝነት ሳይሆን በሰማያዊ ጥበቃና ረድኤት እንደተከበበ ማመን።"
        )
    )
}
