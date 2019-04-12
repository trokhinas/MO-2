package func

import matrix.AbstractMatrix


interface FuncService {
    fun J(U: AbstractMatrix) :Double

    fun JDiff_U1(U: AbstractMatrix): Double

    fun JDiff_U2(U: AbstractMatrix): Double

    fun gradient(U: AbstractMatrix): AbstractMatrix

    fun gradValue(U: AbstractMatrix): Double

    fun JDiff_U1_U2(U: AbstractMatrix): Double

    fun JDiff_U1_U1(U: AbstractMatrix): Double

    fun JDiff_U2_U1(U: AbstractMatrix): Double

    fun JDiff_U2_U2(U: AbstractMatrix): Double

    fun getH(U: AbstractMatrix): AbstractMatrix


}