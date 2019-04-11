package func

import matrix.AbstractMatrix
import matrix.Matrix
import java.util.*
import kotlin.collections.ArrayList


class Vector(private val coefficients: List<Double>) : Iterable<Double> {
    private val count = 4
    var format = "%.${count}f"

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

    @JvmName("timeByDouble")
    operator fun times(coef: Double): Vector {
        var list = ArrayList<Double>(coefficients.size)

        for (item in coefficients) {
            list.add(item * coef)
        }
        return Vector(list)
    }

    @JvmName("timeByMatrix")
    operator fun times(matrix: AbstractMatrix): Vector {
        var temp = this.toMatrix()
        var result = temp * matrix

        return toVector(result)
    }

    override fun toString(): String {
        val sj = StringJoiner(", ")
        for (item in coefficients) {
            val str = String.format(format, item)
            sj.add(str)
        }
        return "($sj)"
    }

    fun toMatrix(): AbstractMatrix {
        return Matrix(
            arrayOf(
                doubleArrayOf(this[0], this[1])
            )
        )
    }

    companion object {
        fun toVector(matrix: AbstractMatrix): Vector {
            var list = ArrayList<Double>()

            if (matrix.rowsNum == 1) {
                for (elem in matrix.getRow(0)) {
                    list.add(elem)
                }
            }
            return Vector(list)
        }
    }



}

private operator fun AbstractMatrix.times(matrix: AbstractMatrix): AbstractMatrix {
    return this.mulMatrix(matrix)
}



