package codeit.student.kotlintemplate.models.salad

data class SaladSpreeRequest(
    val number_of_salads: Int,
    val salad_prices_street_map: List<List<String>>
)
