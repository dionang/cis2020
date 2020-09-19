package codeit.student.kotlintemplate.models.xerox

data class XeroxRequest (
    val num_of_a3_copiers: Int,
    val num_of_a4_copiers: Int,
    val documents: Map<String, Document>
)
