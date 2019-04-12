package extensions

import matrix.AbstractMatrix
import java.util.*

fun AbstractMatrix.toVector(): String {
    val sj = StringJoiner(",")
    for (i in 0 until rowsNum) {
        val row = getRow(i)
        row.forEach { sj.add(it.toString()) }
    }
    return "($sj)"
}

operator fun AbstractMatrix.times(a: Double): AbstractMatrix {
    return this.mul(a)
}

operator fun AbstractMatrix.minus(matrix: AbstractMatrix): AbstractMatrix {
    if (rowsNum == matrix.rowsNum && colsNum == matrix.colsNum)
        return this.subMatrix(matrix)
    throw Error("Вычитание матрицы разной размерности")
}

