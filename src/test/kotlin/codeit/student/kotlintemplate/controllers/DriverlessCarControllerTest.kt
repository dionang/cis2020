package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.driverless.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DriverlessCarControllerTest {

    @Test
    fun `sample test case`() {
        val response = DriverlessCarController.evaluate(
            DriverlessCarRequest(
                gameId = "G495690bb",
                roads = listOf(
                    Road(
                        name = "S1",
                        type = "street",
                        from = mapOf(
                            "x" to 0.0,
                            "y" to 100.0
                        ),
                        to = mapOf(
                            "x" to 5000.0,
                            "y" to 100.0
                        ),
                        maxSpeed = 20.0,
                        zones = listOf()
                    )
                ),
                start = mapOf(
                    "x" to 0.0,
                    "y" to 97.5
                ),
                finish = mapOf(
                    "x" to 4800.0,
                    "y" to 97.5
                ),
                vehicle = Vehicle(
                    maxAcceleration = 10.0,
                    minAcceleration = 15.0,
                    topSpeed = 25.0
                )
            )
        )

        val expectedResponse = DriverlessCarResponse(
            gameId =  "G495690bb",
            instructions = listOf(
                Instruction(
                    acceleration = 10.0,
                    distance = 20.0,
                    direction = "straight"
                ),
                Instruction(
                    acceleration = 0.0,
                    distance = 4780.0,
                    direction = "straight"
                )
            )
        )

        assertThat(response).isEqualTo(expectedResponse)
    }
}
