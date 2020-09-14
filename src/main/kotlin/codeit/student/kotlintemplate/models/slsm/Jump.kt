package codeit.student.kotlintemplate.models.slsm

import com.fasterxml.jackson.annotation.JsonCreator

interface Jump {
    fun square(): Int
    fun to(): Int?

    companion object {
        @JvmStatic
        @JsonCreator
        fun create(value: String): Jump? {
            val (from, to) = value.split(":").map { it.toInt() }
            return if (from == 0)   Mirror(to)
            else if (to == 0)       Smoke(from)
            else if (from > to)     Snake(from, to)
            else                    Ladder(from, to)
        }
    }
}