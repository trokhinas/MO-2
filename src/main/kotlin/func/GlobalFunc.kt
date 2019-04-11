package func

import matrix.AbstractMatrix
import matrix.Matrix
import kotlin.math.pow
import kotlin.math.sqrt


class GlobalFunc {

    companion object {
        const val epsilon: Double = 0.001

        fun J(U: Vector) :Double {
            val u1 = U[0]
            val u2 = U[1]

//            return (u1 - u2).pow(2) + u1 - 5 * u2
//            return (u1 - 5).pow(2) + (u2 + 3).pow(2)
            return -(u1 + u2).pow(2) + 9 * u1 * u2
            //(u1 - u2)^2 + u1 - 5u2
        }


        fun JDiff_U1(U: Vector): Double {
            val u1 = U[0]
            val u2 = U[1]

//            return 2 * u1 - 2 * u2 + 1
//            return 2 * (u1 - 5)
            return 7 * u2 - 2 * u1
            //2u1 - 2u2 + 1
        }

        fun JDiff_U2(U: Vector): Double {
            val u1 = U[0]
            val u2 = U[1]

//            return 2 * u2 - 2 * u1 - 5
//            return 2 * (u2 + 3)
            return 7 * u1 - 2 * u2
            //-2u1 + 2u2 - 5
        }

        fun JDiff_U1_U2(U: Vector): Double {
//            return -2.0
//            return 0.0
            return 7.0
        }

        fun JDiff_U1_U1(U: Vector): Double {
//            return 2.0
//            return 2.0
            return -2.0
        }

        fun JDiff_U2_U1(U: Vector): Double {
//            return -2.0
//            return 0.0
            return 7.0
        }

        fun JDiff_U2_U2(U: Vector): Double {
//            return 2.0
//            return 2.0
            return -2.0
        }

        fun getH(U: Vector): AbstractMatrix {
            var array = arrayOf(
                doubleArrayOf(JDiff_U1_U1(U), JDiff_U2_U1(U)),
                doubleArrayOf(JDiff_U1_U2(U), JDiff_U2_U2(U))
            )
            return Matrix(array)
        }

        fun gradient(U: Vector): Vector {
            val coeffs = listOf(JDiff_U1(U), JDiff_U2(U))
            return Vector(coeffs)
        }

        fun gradValue(U: Vector): Double {
            val gradient = gradient(U)
            return module(gradient)
        }

        fun module(U: Vector): Double {
            var value = 0.0

            for (point in U) {
                value += point.pow(2)
            }
            return sqrt(value)
        }
    }
}