package codeit.student.kotlintemplate.controllers

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ClusterController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/cluster")
    fun getWays(@RequestBody request: List<List<String>>): Map<String, Int> {
        logger.info("Request received $request")

        val response = mapOf("answer" to countClusters(request))

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun countClusters(area: List<List<String>>): Int {
            val validMoves = listOf(Pair(-1,-1), Pair(0,-1), Pair(1,-1), Pair(1,0), Pair(1,1), Pair(0,1), Pair(-1,1), Pair(-1,0))
            var clusters = 0
            val visited = mutableSetOf<Pair<Int,Int>>()

            getInfected(area).forEach { infected ->
                if (!visited.contains(infected)) {
                    visited.add(infected)
                    clusters += 1

                    val queue = LinkedList<Pair<Int, Int>>()
                    queue.add(infected)

                    // infect all possible squares, and add this squares to visited
                    while (queue.size > 0) {
                        val curr = queue.pollFirst()
                        validMoves.forEach { (dx, dy) ->
                            val newX = curr.first + dx
                            val newY = curr.second + dy

                            if (newX >= 0 && newX < area[0].size && newY >= 0 && newY < area.size) {
                                val newSquare = Pair(newX, newY)

                                // if square is not a space, "transmit" the virus to them
                                if (area[newY][newX] != "*" && !visited.contains(newSquare)) {
                                    queue.addLast(newSquare)
                                    visited.add(newSquare)
                                }
                            }
                        }

                    }
                }
            }

            return clusters
        }

        fun getInfected(area: List<List<String>>): List<Pair<Int, Int>> {
            return area
                .mapIndexed { y, row ->
                    row.mapIndexedNotNull { x, square ->
                        if (square == "1") Pair(x,y) else null
                    }
                }.flatten()
        }
    }
}