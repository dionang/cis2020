package codeit.student.kotlintemplate.models.salad

data class SaladSpreeRequest(
    val numberOfSalads: Int,
    val saladPricesStreetMap: List<List<String>>
)
