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
        "nav_home" to mapOf("am" to "ዋና ገጽ", "en" to "Home"),
        "nav_zodiac" to mapOf("am" to "ኮከብ ቆጠራ", "en" to "Zodiac"),
        "nav_bahire_hasab" to mapOf("am" to "አቡሻህር / ፀሐይ", "en" to "Calendar"),
        "nav_love" to mapOf("am" to "መስተፋቅር / ስምምነት", "en" to "Love & Harmony"),
        "nav_healing" to mapOf("am" to "የፈውስ ዕፀዋት", "en" to "Herbal Healing"),
        "nav_oracle" to mapOf("am" to "የጥበብ ጠያቂ", "en" to "Oracle"),
        "nav_enoch" to mapOf("am" to "መጽሐፈ ሄኖክ", "en" to "Book of Enoch"),
        "nav_payment" to mapOf("am" to "ክፍያና ባንክ", "en" to "Payment"),
        "register_title" to mapOf("am" to "ወደ ጥንተ ጥበብ መግቢያ ምዝገባ", "en" to "Register to Enter Tinte Tibeb"),
        "full_name" to mapOf("am" to "ሙሉ ስም", "en" to "Full Name"),
        "phone_number" to mapOf("am" to "ስልክ ቁጥር", "en" to "Phone Number"),
        "gender" to mapOf("am" to "ፆታ", "en" to "Gender"),
        "password" to mapOf("am" to "የይለፍ ቃል (Password)", "en" to "Password"),
        "confirm" to mapOf("am" to "አረጋግጥና ግባ", "en" to "Confirm & Enter")
    )
}
