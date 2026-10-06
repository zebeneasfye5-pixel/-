package com.example.data

enum class AppLanguage(val code: String, val displayName: String, val script: String) {
    AMHARIC("am", "አማርኛ", "ግእዝ/አማርኛ"),
    GEEZ("ge", "ግዕዝ", "ልሳነ ግዕዝ"),
    ENGLISH("en", "English", "Latin"),
    OROMO("om", "Afaan Oromoo", "Qubee"),
    TIGRINYA("ti", "ትግርኛ", "ትግርኛ")
}

object LanguageManager {
    fun getString(key: String, lang: String): String {
        return translations[key]?.get(lang)
            ?: translations[key]?.get("am")
            ?: key
    }

    private val translations = mapOf(
        "app_title" to mapOf(
            "am" to "ጥንተ ጥበብ",
            "ge" to "ጥንተ ጥበብ ዘቀደምት",
            "en" to "Tinte Tibeb",
            "om" to "Ogummaa Durii",
            "ti" to "ጥንተ ጥበብ"
        ),
        "app_subtitle" to mapOf(
            "am" to "የአባቶቻችን ጥንታዊ ጥበባትና የቀመር ማውጫ",
            "ge" to "ጥበበ አበዊነ ወሐሳበ አቡሻህር",
            "en" to "Ancient Ethiopian Wisdom & Astronomical Oracle",
            "om" to "Ogummaa Abbootii fi Herrega Urjii Durii",
            "ti" to "ጥንታዊ ጥበብ ኣቦታትናን ሓሳበ አቡሻህርን"
        ),
        "link_name" to mapOf(
            "am" to "የአባቶቻችን እውቀት",
            "ge" to "አእምሮተ አበው",
            "en" to "Ancestral Knowledge",
            "om" to "Beekumsa Abbootii Keenyaa",
            "ti" to "ፍልጠት ኣቦታትና"
        ),
        "nav_home" to mapOf(
            "am" to "ዋና ገጽ",
            "ge" to "ገጽ",
            "en" to "Home",
            "om" to "Fuula Duraa",
            "ti" to "ቀንዲ ገጽ"
        ),
        "nav_zodiac" to mapOf(
            "am" to "ኮከብ ቆጠራ",
            "ge" to "ሐሳበ ከዋክብት",
            "en" to "Zodiac & Stars",
            "om" to "Lakkoofsa Urjii",
            "ti" to "ቁጽሪ ከዋክብቲ"
        ),
        "nav_bahire_hasab" to mapOf(
            "am" to "አቡሻህር / ፀሐይ",
            "ge" to "አቡሻህር ወባሕረ ሐሳብ",
            "en" to "Abushahir Calendar",
            "om" to "Abushahir fi Aduu",
            "ti" to "አቡሻህርን ባሕረ ሓሳብን"
        ),
        "nav_love" to mapOf(
            "am" to "መስተፋቅር / ስምምነት",
            "ge" to "ጥበበ ፍቅር ወተፋቅሮ",
            "en" to "Love & Harmony",
            "om" to "Jaalala fi Araara",
            "ti" to "መስተፋቅርን ፍቕርን"
        ),
        "nav_healing" to mapOf(
            "am" to "የፈውስ ዕፀዋት",
            "ge" to "መጽሐፈ ፈውስ ወዕፀዋት",
            "en" to "Herbal Healing",
            "om" to "Qoricha Biqiltootaa",
            "ti" to "ፈውሲ ኣትክልቲ"
        ),
        "nav_oracle" to mapOf(
            "am" to "የጥበብ ጠያቂ",
            "ge" to "ተስፋ መፍትሔ ሥራይ",
            "en" to "Manuscript Oracle",
            "om" to "Gaaffii Ogummaa",
            "ti" to "ሓታቲ ጥበብ"
        ),
        "nav_enoch" to mapOf(
            "am" to "መጽሐፈ ሄኖክ",
            "ge" to "መጽሐፈ ሄኖክ ነቢይ",
            "en" to "Book of Enoch",
            "om" to "Kitaaba Heenook",
            "ti" to "መጽሓፈ ሄኖክ"
        ),
        "nav_payment" to mapOf(
            "am" to "ክፍያና ባንክ",
            "ge" to "ውስተ ባንክ",
            "en" to "Payment & Banks",
            "om" to "Kaffaltii fi Baankii",
            "ti" to "ክፍሊትን ባንክን"
        ),
        "register_title" to mapOf(
            "am" to "ወደ ጥንተ ጥበብ መግቢያ ምዝገባ",
            "ge" to "መጽሐፈ መዝገብ",
            "en" to "Register to Enter Tinte Tibeb",
            "om" to "Galmee Seensa Ogummaa Durii",
            "ti" to "ምዝገባ መእተዊ ጥንተ ጥበብ"
        ),
        "full_name" to mapOf(
            "am" to "ሙሉ ስም",
            "ge" to "ስመ ሙሉዕ",
            "en" to "Full Name",
            "om" to "Maqaa Guutuu",
            "ti" to "ምሉእ ስም"
        ),
        "phone_number" to mapOf(
            "am" to "ስልክ ቁጥር",
            "ge" to "ኈልቈ ስልክ",
            "en" to "Phone Number",
            "om" to "Lakkoofsa Bilbilaa",
            "ti" to "ቁጽሪ ተሌፎን"
        ),
        "gender" to mapOf(
            "am" to "ፆታ",
            "ge" to "ፆታ (ተባዕት/አንስት)",
            "en" to "Gender",
            "om" to "Saala",
            "ti" to "ፆታ"
        ),
        "password" to mapOf(
            "am" to "የይለፍ ቃል (Password)",
            "ge" to "ቃለ ማዕቀብ",
            "en" to "Password",
            "om" to "Jecha Icchitii",
            "ti" to "ናይ ሕልፊ ቃል"
        ),
        "confirm" to mapOf(
            "am" to "አረጋግጥና ግባ",
            "ge" to "አጽንዕ ወበዋእ",
            "en" to "Confirm & Enter",
            "om" to "Mirkaneessi fi Seeni",
            "ti" to "ኣረጋግጽን እቶን"
        ),
        "mother_name" to mapOf(
            "am" to "የእናት ስም (ለቀመር አስፈላጊ)",
            "ge" to "ስመ እም",
            "en" to "Mother's Name (Required for Gematria)",
            "om" to "Maqaa Haadhaa",
            "ti" to "ስም ኣደ"
        ),
        "speak" to mapOf(
            "am" to "አንብብልኝ (ድምፅ)",
            "ge" to "ስምዖ በቃል",
            "en" to "Listen (Voice)",
            "om" to "Sagaleen Dhaggeeffadhu",
            "ti" to "ብድምጺ ስምዖ"
        ),
        "stop_speaking" to mapOf(
            "am" to "ድምፅ አቁም",
            "ge" to "አዕርፍ ቃለ",
            "en" to "Stop Audio",
            "om" to "Dhaabi",
            "ti" to "ድምጺ ደው ኣብል"
        )
    )
}
