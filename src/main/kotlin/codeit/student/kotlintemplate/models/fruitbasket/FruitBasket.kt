package codeit.student.kotlintemplate.models.fruitbasket

data class FruitBasket (
    val maApple: Int = 0,
    val maAvocado: Int = 0,
    val maPineapple: Int = 0,
    val maPomegranate: Int = 0,
    val maRamubutan: Int = 0,
    val maWatermelon: Int = 0,

    val maAppleWeight: Int = 23,
    val maAvocadoWeight: Int = 8,
    val maPineappleWeight: Int = 82,
    val maPomegranateWeight: Int = 33,
    val maRamubutanWeight: Int = 35,
    val maWatermelonWeight: Int = 59
) {
    fun getWeight(): Int {
        return maApple * maAppleWeight + maAvocado * maAvocadoWeight + maPineapple * maPineappleWeight +
               maPomegranate * maPomegranateWeight + maRamubutan * maRamubutanWeight + maWatermelon * maWatermelonWeight
    }
}