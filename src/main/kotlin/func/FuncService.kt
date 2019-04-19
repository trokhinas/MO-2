package func

import matrix.AbstractMatrix
import matrix.Matrix


interface FuncService {
    fun J(U: AbstractMatrix) :Double

    fun JDiff_U1(U: AbstractMatrix): Double

    fun JDiff_U2(U: AbstractMatrix): Double

    fun gradient(U: AbstractMatrix): AbstractMatrix {
        val coeffs = arrayOf(
            doubleArrayOf(JDiff_U1(U), JDiff_U2(U))
        )
        return Matrix(coeffs)
    }

    fun gradValue(U: AbstractMatrix): Double {
        val gradient = gradient(U)
        return GlobalFunc.module(gradient)
    }

    fun JDiff_U1_U2(U: AbstractMatrix): Double

    fun JDiff_U1_U1(U: AbstractMatrix): Double

    fun JDiff_U2_U1(U: AbstractMatrix): Double

    fun JDiff_U2_U2(U: AbstractMatrix): Double

    fun getH(U: AbstractMatrix): AbstractMatrix {
        val array = arrayOf(
            doubleArrayOf(JDiff_U1_U1(U), JDiff_U2_U1(U)),
            doubleArrayOf(JDiff_U1_U2(U), JDiff_U2_U2(U))
        )
        return Matrix(array)
    }


}