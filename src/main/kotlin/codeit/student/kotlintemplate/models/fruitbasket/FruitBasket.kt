package codeit.student.kotlintemplate.models.fruitbasket

data class FruitBasket (
    val maApple: Int = 0,
    val maAvocado: Int = 0,
    val maPineapple: Int = 0,
    val maPomegranate: Int = 0,
    val maRamubutan: Int = 0,
    val maWatermelon: Int = 0,

    val maAppleWeight: Int = 70,
    val maAvocadoWeight: Int = 45,
    val maPineappleWeight: Int = 50,
    val maPomegranateWeight: Int = 12,
    val maRamubutanWeight: Int = 25,
    val maWatermelonWeight: Int = 55
) {
    fun getWeight(): Int {
        return maApple * maAppleWeight + maAvocado * maAvocadoWeight + maPineapple * maPineappleWeight +
               maPomegranate * maPomegranateWeight + maRamubutan * maRamubutanWeight + maWatermelon * maWatermelonWeight
    }
}
