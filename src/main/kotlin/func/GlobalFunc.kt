package func

import kotlin.math.pow
import kotlin.math.sqrt


class GlobalFunc {

    companion object {
        const val epsilon: Double = 0.001

        fun J(U: Vector) :Double {
            val u1 = U[0]
            val u2 = U[1]

            return (u1 - u2).pow(2) + u1 - 5 * u2
            //(u1 - u2)^2 + u1 - 5u2
        }

        fun JDiff_U1(U: Vector): Double {
            val u1 = U[0]
            val u2 = U[1]

            return 2 * u1 - 2 * u2 + 1
            //2u1 - 2u2 + 1
        }

        fun JDiff_U2(U: Vector): Double {
            val u1 = U[0]
            val u2 = U[1]

            return  2 * u2 - 2 * u1 -5
            //-2u1 + 2u2 - 5
        }

        fun gradient(U: Vector): Vector {
            val coeffs = listOf(JDiff_U1(U), JDiff_U2(U))
            return Vector(coeffs)
        }

        fun gradValue(U: Vector): Double {
            var gradValue = 0.0

            for (diff in gradient(U)) {
                gradValue += diff.pow(2)
            }
            return sqrt(gradValue)
        }

    }
}