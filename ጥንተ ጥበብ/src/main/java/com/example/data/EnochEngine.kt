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
            summary = "ሄኖክ በሰማይ ዙሪያ ያሉትን ስድስቱን የፀሐይና የጨረቃ መውጫና መግቢያ ደጆች በዑራኤል መሪነት የተመለከተበት ታላቅ የቀመር ምዕራፍ።",
            sacredManuscriptPassage = """
                «ወዝንቱ ውእቱ ሥርዓተ ብርሃናት ኩሎሙ ዘአርአየኒ ዑራኤል መልአክ ቅዱስ ዘውእቱ ዐቃቤ ብርሃናት።
                ወርኢኩ ስድስተ ደጆተ እንተ ቦን ይወጽእ ፀሐይ ወስድስተ ደጆተ እንተ ቦን ይዐርብ ፀሐይ።»
            """.trimIndent(),
            astronomicalWisdom = "በዚህ ቀመር መሠረት የፀሐይ ጉዞ ፫፻፷፬ ዕለታት ሲሆን ፬ቱን ወቅቶች ያካተተ ፍጹም የሰማይ ሰዓት ነው።",
            spiritualMeaning = "የተፈጥሮ ሕግጋትና የሰማይ ኃይላት ያለ ምንም ማወላወል በፈጣሪያቸው ሥርዓት እንደሚመላለሱ ያሳያል።"
        ),
        EnochChapter(
            id = "seven_heavens",
            titleGeez = "ራእየ ሄኖክ በሰባቱ ሰማያት",
            titleAmharic = "ሰባቱ ሰማያትና የመላእክት ሠራዊት",
            summary = "ሄኖክ ከመሬት ተነስቶ እስከ ታላቁ የእሳትና የበረዶ መንበር ድረስ ያደረገው ሰማያዊ ጉዞ።",
            sacredManuscriptPassage = """
                «ወአዕረጉኒ ውስተ ሰማይ ወርኢኩ መንበረ እሳት ወኪሩቤል ዘይኬልልዎ፤
                ወድምፀ መላእክት ከመ ድምፀ ማያት ብዙኅ።»
            """.trimIndent(),
            astronomicalWisdom = "ሰባቱ ሰማያት ከጠፈር ፕላኔቶችና ከከዋክብት ጋር ያላቸው ምስጢራዊ መስተጋብር ተቀምጧል።",
            spiritualMeaning = "ጽድቅና እውነት የሰው ልጅን እስከ ሰማያዊ ክብር እንደሚያደርሱት ያስተምራል።"
        ),
        EnochChapter(
            id = "twelve_winds",
            titleGeez = "፲፪ቱ አናቅጸ ነፋሳት (ምዕራፍ ፸፮)",
            titleAmharic = "አሥራ ሁለቱ የነፋስ ደጆች",
            summary = "በምድር ዙሪያ ያሉ ፲፪ ደጆች፤ አራቱ የምሕረትና የበረከት፣ ስምንቱ የመቅሰፍት ንፋሳት የሚወጡባቸው።",
            sacredManuscriptPassage = """
                «ወርኢኩ ፲፪ተ አናቅጸ ውስተ አጽናፈ ምድር እንተ እምኔሆን ይወጽኡ ነፋሳት ኩሎሙ፤
                አርባዕቱ እምኔሆሙ ነፋሳተ በረከት ወስምንቱ ነፋሳተ መቅሠፍት።»
            """.trimIndent(),
            astronomicalWisdom = "የአየር ንብረትና የዝናብ ወቅቶች የቀደምት ሳይንሳዊ መሠረት።",
            spiritualMeaning = "እግዚአብሔር ምድርን በልክና በሚዛን እንደፈጠራት ማስተዋል ነው።"
        ),
        EnochChapter(
            id = "archangels",
            titleGeez = "መላእክተ ኃይላት ወአስማቲሆሙ",
            titleAmharic = "ሊቃነ መላእክትና ኃላፊነታቸው",
            summary = "ዑራኤል (ብርሃናት)፣ ሚካኤል (ህዝብ ጠባቂ)፣ ሩፋኤል (ፈዋሽ)፣ ገብርኤል (ገነት)፣ ፋኑኤል (ተስፋ)።",
            sacredManuscriptPassage = """
                «ዑራኤል አሐዱ እምቅዱሳን መላእክት ዘዲበ ነጎድጓድ፤
                ሩፋኤል ዘዲበ ኩሉ ደዌ፤
                ሚካኤል ዘተወክፈ ዲበ ኂሩተ ሰብእ።»
            """.trimIndent(),
            astronomicalWisdom = "እያንዳንዱ መልአክ ከአጽናፈ ዓለም የተፈጥሮ ሕግና ንጥረ-ነገር ጋር ተያይዟል።",
            spiritualMeaning = "የሰው ልጅ በሰማያዊ ጥበቃና ረድኤት እንደተከበበ ማመን።"
        )
    )
}
