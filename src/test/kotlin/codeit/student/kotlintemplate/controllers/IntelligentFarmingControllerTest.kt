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

        assertThat(actual).isEqualTo(expected)
    }
}