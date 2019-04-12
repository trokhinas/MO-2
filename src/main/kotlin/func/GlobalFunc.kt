package func

import matrix.AbstractMatrix
import matrix.Matrix
import kotlin.math.pow
import kotlin.math.sqrt


open class GlobalFunc: FuncService {

    companion object {
        const val epsilon: Double = 0.001

        fun module(U: AbstractMatrix): Double {
            var value = 0.0

            for (point in U.getRow(0)) {
                value += point.pow(2)
            }
            return sqrt(value)
        }
    }

    override fun J(U: AbstractMatrix) :Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]

//            return (u1 - u2).pow(2) + u1 - 5 * u2
        return (u1 - 5).pow(2) + (u2 + 3).pow(2)
//            return -(u1 + u2).pow(2) + 9 * u1 * u2
        //(u1 - u2)^2 + u1 - 5u2
    }


    override fun JDiff_U1(U: AbstractMatrix): Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]

//            return 2 * u1 - 2 * u2 + 1
        return 2 * (u1 - 5)
//            return 7 * u2 - 2 * u1
        //2u1 - 2u2 + 1
    }

    override fun JDiff_U2(U: AbstractMatrix): Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]

//            return 2 * u2 - 2 * u1 - 5
        return 2 * (u2 + 3)
//            return 7 * u1 - 2 * u2
        //-2u1 + 2u2 - 5
    }

    override fun JDiff_U1_U2(U: AbstractMatrix): Double {
//            return -2.0
        return 0.0
//            return 7.0
    }

    override fun JDiff_U1_U1(U: AbstractMatrix): Double {
//            return 2.0
        return 2.0
//            return -2.0
    }

    override fun JDiff_U2_U1(U: AbstractMatrix): Double {
//            return -2.0
        return 0.0
//            return 7.0
    }

    override fun JDiff_U2_U2(U: AbstractMatrix): Double {
//            return 2.0
        return 2.0
//            return -2.0
    }

    override fun getH(U: AbstractMatrix): AbstractMatrix {
        var array = arrayOf(
            doubleArrayOf(JDiff_U1_U1(U), JDiff_U2_U1(U)),
            doubleArrayOf(JDiff_U1_U2(U), JDiff_U2_U2(U))
        )
        return Matrix(array)
    }

    override fun gradient(U: AbstractMatrix): AbstractMatrix {
        val coeffs = arrayOf(
            doubleArrayOf(JDiff_U1(U), JDiff_U2(U))
        )
        return Matrix(coeffs)
    }

    override fun gradValue(U: AbstractMatrix): Double {
        val gradient = gradient(U)
        return module(gradient)
    }
}