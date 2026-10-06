package com.example

import com.example.data.*
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testBahireHasabCalculations() {
        val result2017 = BahireHasabEngine.calculate(2017)
        assertEquals(2017, result2017.year)
        assertNotNull(result2017.evangelist)
        assertTrue(result2017.wenber in 0..18)
        assertTrue(result2017.metqe in 1..30)
        assertTrue(result2017.fasikaDate.isNotBlank())
        assertTrue(result2017.neneweDate.isNotBlank())
    }

    @Test
    fun testZodiacGematriaCalculation() {
        val scoreSolomon = ZodiacEngine.calculateGematria("ሰሎሞን")
        assertTrue(scoreSolomon > 0)

        val zodiacResult = ZodiacEngine.calculateZodiac("ሰሎሞን", "ወለተ ጊዮርጊስ")
        assertNotNull(zodiacResult.sign)
        assertTrue(zodiacResult.remainderMod12 in 1..12)
        assertEquals(zodiacResult.sign.id, zodiacResult.remainderMod12)
        assertTrue(zodiacResult.sign.ancientPrayer.isNotBlank())
    }

    @Test
    fun testLoveHarmonyEngine() {
        val input = LoveHarmonyInput(
            seekerName = "ዳዊት",
            seekerMotherName = "ወለተ ማርያም",
            targetName = "ማርታ",
            targetMotherName = "ወለተ ዮሐንስ",
            intentionType = "የትዳር ሰላምና ፍቅር",
            birthDay = "እሑድ"
        )
        val result = LoveHarmonyEngine.generateHarmony(input)
        assertTrue(result.compatibilityPercentage in 50..100)
        assertTrue(result.geezFormulaText.contains("በስመ አብ"))
        assertTrue(result.naturalMaterialsNeeded.isNotEmpty())
    }

    @Test
    fun testHealingEngineSearch() {
        val damakeseSearch = HealingEngine.searchHerbs("ዳማከሴ")
        assertEquals(1, damakeseSearch.size)
        assertEquals("ዳማከሴ", damakeseSearch[0].nameAmharic)

        val stomachAilmentSearch = HealingEngine.searchHerbs("ሆድ")
        assertTrue(stomachAilmentSearch.isNotEmpty())
    }
}
