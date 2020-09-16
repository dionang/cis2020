package codeit.student.kotlintemplate.models.babylon

data class BabylonRequest (
    val numberOfBooks: Int,
    val numberOfDays: Int,
    val books: List<Int>,
    val days: ArrayList<Int>
)
