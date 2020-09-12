package codeit.student.kotlintemplate.models.supermarket

data class Node(
    val curr: Pair<Int, Int>,
    val prev: Node?
)