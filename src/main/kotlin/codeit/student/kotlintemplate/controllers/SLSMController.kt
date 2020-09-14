package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.slsm.Jump
import codeit.student.kotlintemplate.models.slsm.Mirror
import codeit.student.kotlintemplate.models.slsm.SLSMRequest
import codeit.student.kotlintemplate.models.slsm.Smoke
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SLSMController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/slsm")
    fun getDiceRolls(@RequestBody request: SLSMRequest): List<Int> {
        logger.info("Request received $request")

        val response = getOptimalDiceRolls(request.boardSize, request.players, request.jumps)
//        val response = listOf<Int>()
//        tracePositions(request.boardSize, request.players, request.jumps, listOf(5,1,6,6,2,2,1,5,5,3,3,4,2,1,1,5,1,6,2,5,2,1,5,4,5,5,5,6,1 ))
        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getOptimalDiceRolls(boardSize: Int, players: Int, jumps: List<Jump>): List<Int> {
            val jumpsMap = jumps.map { it.square() to it }.toMap()
            val jumpToMap = jumps.map { it.square() to it.to() }.toMap()
            val playerPosition = (1..players).map { it to 1 }.toMap(mutableMapOf())
            val rolls = mutableListOf<Int>()
            var currPlayer = 1

            while (true) {
                val currPosition = playerPosition[currPlayer]!!
                if (currPlayer == players) { // find best move for last player
                    val remaining = boardSize - currPosition

                    // end condition
                    if (remaining <= 6) {
                        rolls.add(remaining)
                        return rolls
                    }

                    val availableMoves = (1..6).mapNotNull { roll ->
                        val newSquare  = currPosition + roll

                        if (jumpsMap[newSquare] !is Smoke) {
                            roll to (jumpToMap[newSquare] ?: newSquare)
                        } else {
                            null
                        }
                    }

                    val bestMove = availableMoves.maxBy { it.second }!!.first
                    val newSquare = currPosition + bestMove
                    val bestSquare = jumpToMap[newSquare] ?: newSquare

                    playerPosition[currPlayer] = bestSquare
                    rolls.add(bestMove)

                    if (jumpsMap[newSquare] !is Mirror) {
                        currPlayer = 1
                    }
                } else { // find worst move for other players that do not result in reroll
                    val availableMoves = (1..6).mapNotNull { roll ->
                        val newSquare  = currPosition + roll
                        if (jumpsMap[newSquare] == null && newSquare != boardSize) {
                            roll
                        } else {
                            null
                        }
                    }

                    val worstMove = availableMoves.min()!!
                    playerPosition[currPlayer] = currPosition + worstMove
                    rolls.add(worstMove)
                    currPlayer += 1
                }
            }
        }

        fun tracePositions(boardSize: Int, players: Int, jumps: List<Jump>, rolls: List<Int>) {
            val jumpsMap = jumps.map { it.square() to it }.toMap()
            val jumpToMap = jumps.map { it.square() to it.to() }.toMap()
            val playerPosition = (1..players).map { it to 1 }.toMap(mutableMapOf())
            var smokeRoll = false
            var currPlayer = 1

            for (roll in rolls) {
                val currPosition = playerPosition[currPlayer]!!
                var newSquare  = currPosition + if (smokeRoll) -roll else roll
                if (newSquare > boardSize) {
                    newSquare = boardSize - (newSquare - boardSize)
                }

                val jumpSquare = jumpToMap[newSquare]?: newSquare

                playerPosition[currPlayer] = jumpSquare

                val reroll = jumpsMap[newSquare].let{
                    if (it is Smoke) {
                        smokeRoll = true
                        true
                    } else {
                        smokeRoll = false
                        it is Mirror
                    }
                }

                if (!reroll) {
                    currPlayer = if (currPlayer < players) (currPlayer + 1) else 1
                }
            }
        }
    }
}