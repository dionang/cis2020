package codeit.student.kotlintemplate.models.slsm

data class Snake(val square: Int, val to: Int): Jump {
    override fun square(): Int {
        return square
    }

    override fun to(): Int? {
        return to
    }
}