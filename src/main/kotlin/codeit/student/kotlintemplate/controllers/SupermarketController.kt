package codeit.student.kotlintemplate.controllers

import codeit.student.kotlintemplate.models.supermarket.Node
import codeit.student.kotlintemplate.models.supermarket.SupermarketRequest
import codeit.student.kotlintemplate.models.supermarket.SupermarketResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class SupermarketController {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/supermarket")
    fun getWays(@RequestBody request: SupermarketRequest): SupermarketResponse {
        logger.info("Request received $request")

        val response = SupermarketResponse(
                request.tests.map { (testId, test) ->
                    testId to getShortestPath(test.maze, Pair(test.start.first(), test.start.last()), Pair(test.end.first(), test.end.last()))
                }.toMap()
        )

        logger.info("Returning result $response")
        return response
    }

    companion object {
        fun getShortestPath(maze: List<List<Int>>, start: Pair<Int, Int>,  end: Pair<Int, Int>): Int {
            val validMoves = listOf(Pair(0,-1), Pair(0,1), Pair(-1,0), Pair(1,0))
            val startNode = Node(start,null)
            val visited = mutableSetOf(start)
            val queue   = LinkedList<Node>()
            queue.add(startNode)

            while (queue.size > 0) {
                val currNode = queue.pollFirst()
                validMoves.forEach { (dx, dy) ->
                    val newX = currNode.curr.first + dx
                    val newY = currNode.curr.second + dy

                    if (newX >= 0 && newX < maze[0].size && newY >= 0 && newY < maze.size) {
                        // goal reached
                        if (newX == end.first && newY == end.second) {
                            var moves = 1
                            var prevNode: Node? = currNode
                            while(prevNode != null) {
                                prevNode = prevNode.prev
                                moves += 1
                            }

                            return moves
                        }

                        val newSquare = Pair(newX, newY)

                        // otherwise, add new square to queue
                        if (maze[newY][newX] == 0 && !visited.contains(newSquare)) {
                            queue.addLast(Node(newSquare, currNode))
                            visited.add(newSquare)
                        }
                    }
                }

            }

            // no solution found
            return -1
        }
    }
}