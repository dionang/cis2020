package codeit.student.kotlintemplate.models.slsm

data class Mirror(val square: Int): Jump {
    override fun square(): Int {
        return square
    }

    override fun to(): Int? {
        return null
    }
}