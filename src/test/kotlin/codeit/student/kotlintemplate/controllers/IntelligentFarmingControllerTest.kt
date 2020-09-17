package codeit.student.kotlintemplate.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IntelligentFarmingControllerTest {
    @Test
    fun `can get correct number of ACGT and CC when remaining C is even`() {
        val expected = mapOf(
            "ACGT" to 2,
            "CC"   to 1,
            "A" to 1,
            "G" to 2,
            "T" to 0
        )

        val actual = IntelligentFarmingController.getSequenceComponents("AGCTAGCTCCAGG")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `can get correct number of ACGT and CC when remaining C is odd`() {
        val expected = mapOf(
            "ACGT" to 1,
            "CC"   to 1,
            "A" to 2,
            "G" to 3,
            "T" to 1
        )

        val actual = IntelligentFarmingController.getSequenceComponents("AGCTAGCTCAGG")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `build correct string when # of ACGT greater than # of A`() {
        val expected = "AACGT" + "AACGT" + "ACGT" + "CC" + "GGGT"

        val actual = IntelligentFarmingController.buildGeneSequence(
            mutableMapOf(
                "ACGT" to 3,
                "CC"   to 1,
                "A" to 2,
                "G" to 3,
                "T" to 1
            )
        )

        println("Actual score: ${IntelligentFarmingController.countScore(actual)}")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `build correct string when # of A greater than # of ACGT, but sufficient other tokens to avoid 3 consecutive A`() {
        val expected = "AACGT" + "AACGT" + "AA" + "CC" + "AA" + "G" + "AA" + "GGT"

        val actual = IntelligentFarmingController.buildGeneSequence(
            mutableMapOf(
                "ACGT" to 2,
                "CC"   to 1,
                "A" to 8,
                "G" to 3,
                "T" to 1
            )
        )

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `build correct string when # of A greater than # of ACGT, but insufficient other tokens to avoid 3 consecutive A`() {
        val expected = "AACGT" + "AACGT" + "AA" + "CC" + "AA" + "G" + "AAA"

        val actual = IntelligentFarmingController.buildGeneSequence(
            mutableMapOf(
                "ACGT" to 2,
                "CC"   to 1,
                "A" to 9,
                "G" to 1,
                "T" to 0
            )
        )

        println("Actual score: ${IntelligentFarmingController.countScore(actual)}")
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `check threshold 1`() {
        val expected = 9600
        val geneSequence = IntelligentFarmingController.getMaxDRIGeneSequence2(
            listOf(
                "A".repeat(700),
                "C".repeat(640),
                "G".repeat(640),
                "T".repeat(840)
            ).joinToString(separator = "")
        )
        val actual = IntelligentFarmingController.countScore(geneSequence)
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `check threshold 2`() {
        val expected = 11810
        val geneSequence = IntelligentFarmingController.getMaxDRIGeneSequence2(
            listOf(
                "A".repeat(495),
                "C".repeat(846),
                "G".repeat(684),
                "T".repeat(630)
            ).joinToString(separator = "")
        )

        println(geneSequence)
        val actual = IntelligentFarmingController.countScore(geneSequence)
        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun `check threshold 3`() {
        val expected = 11200
        val geneSequence = IntelligentFarmingController.getMaxDRIGeneSequence2(
            listOf(
                "A".repeat(870),
                "C".repeat(750),
                "G".repeat(940),
                "T".repeat(730)
            ).joinToString(separator = "")
        )
        val actual = IntelligentFarmingController.countScore(geneSequence)
        assertThat(actual).isEqualTo(expected)

    }
    @Test
    fun `check threshold 4`() {
        val expected = 45
        val geneSequence = IntelligentFarmingController.getMaxDRIGeneSequence(
            listOf(
                "A".repeat(11),
                "C".repeat(4),
                "G".repeat(3),
                "T".repeat(2)
            ).joinToString(separator = "")
        )
        val actual = IntelligentFarmingController.countScore(geneSequence)
        assertThat(actual).isEqualTo(expected)

    }
}