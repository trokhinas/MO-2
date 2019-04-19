package func

import matrix.AbstractMatrix
import matrix.Matrix
import kotlin.math.pow
import kotlin.math.sqrt


open class GlobalFunc: FuncService {

    companion object {
        const val epsilon: Double = 0.01

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

        return (u1 - 5).pow(2) + (u2 + 3).pow(2)
        //(u1 - 5)^2 + (u2 + 3)^2
    }


    override fun JDiff_U1(U: AbstractMatrix): Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]

        return 2 * (u1 - 5)
        //2 * (u1 - 5)
    }

    override fun JDiff_U2(U: AbstractMatrix): Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]

        return 2 * (u2 + 3)
        //2 * (u2 + 3)
    }

    override fun JDiff_U1_U2(U: AbstractMatrix): Double {
        return 0.0
    }

    override fun JDiff_U1_U1(U: AbstractMatrix): Double {
        return 2.0
    }

    override fun JDiff_U2_U1(U: AbstractMatrix): Double {
        return 0.0
    }

    override fun JDiff_U2_U2(U: AbstractMatrix): Double {
        return 2.0
    }

}