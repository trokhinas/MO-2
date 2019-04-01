package func

import java.util.*
import kotlin.collections.ArrayList


class Vector(private val coefficients: List<Double>) : Iterable<Double> {


    override fun iterator(): Iterator<Double> {
        return coefficients.iterator()
    }

    operator fun get(i: Int): Double {
        return coefficients[i]
    }

    operator fun minus(vector: Vector): Vector {
        var list = ArrayList<Double>(coefficients.size)

        for ((i, item) in coefficients.withIndex()) {
            list.add(item - vector[i])
        }
        return Vector(list)
    }

    operator fun times(coef: Double): Vector {
        var list = ArrayList<Double>(coefficients.size)

        for (item in coefficients) {
            list.add(item * coef)
        }
        return Vector(list)
    }

    override fun toString(): String {
        val sj = StringJoiner(", ")
        for (item in coefficients)
            sj.add(item.toString())
        return "($sj)"
    }

}



