package containers

import extensions.minus
import extensions.times
import matrix.AbstractMatrix
import java.util.*
import java.util.stream.Collectors

class Point(private val dimension: Int) : AbstractMatrix() {
    private var points: DoubleArray

    init {
        rowsNum = 1
        colsNum = dimension
        points = DoubleArray(dimension) {0.0}
    }

    constructor(vararg elements: Double) : this(elements.size) {
        for (i in 0 until elements.size) {
            points[i] = elements[i]
        }
    }

    constructor(array: Array<Double>) : this(array.size) {
        for (i in 0 until array.size) {
            points[i] = array[i]
        }
    }

    override fun toString(): String {
        val sj = StringJoiner(", ")
        for (point in points) {
            sj.add(point.toString())
        }
        return "($sj)"
    }

    override fun mulMatrix(p0: AbstractMatrix?): AbstractMatrix {
        if (p0 == null || !isMulable(this, p0))
            throw Error("Не перемножаемые матрицы")
        var array = Array(p0.colsNum) {0.0}

        for (i in 0 until p0.colsNum) {
            var num = 0.0
            for (j in 0 until dimension) {
                num += points[j] * p0[i, j]
            }
            array[i] = num
        }
        return Point(array)
    }

    override fun getRow(p0: Int): DoubleArray {
        return points
    }

    override fun getCol(p0: Int): DoubleArray {
        return doubleArrayOf(points[p0])
    }


    override fun get(p0: Int, p1: Int): Double {
        return points[p1]
    }

    override fun subMatrix(p0: AbstractMatrix?): AbstractMatrix {
        if (p0 == null || !isAddable(this, p0))
            throw Error("Не вычитаемые матрицы")

        var array = Array(p0.colsNum) {0.0}

        for (i in 0 until p0.colsNum) {
            array[i] = points[i] - p0[0, i]
        }
        return Point(array)
    }

    override fun getCells(): Array<DoubleArray> {
        return arrayOf(points)
    }

    override fun mul(p0: Double): AbstractMatrix {
        var array = Array(points.size) {0.0}
        var i = 0
        for (point in points)
            array[i++] = point * p0

        return Point(array)
    }

    override fun addMatrix(p0: AbstractMatrix?): AbstractMatrix {
        if (p0 == null || !isAddable(this, p0))
            throw Error("Не вычитаемые матрицы")

        var array = Array(p0.colsNum) {0.0}

        for (i in 0 until p0.colsNum) {
            array[i] = points[i] + p0[0, i]
        }
        return Point(array)
    }




    override fun swapRows(p0: Int, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun mulRow(p0: Int, p1: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addRow(p0: Int, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addRow(p0: Int, p1: Int, p2: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun swapCols(p0: Int, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun calculateMinor(p0: Int, p1: Int): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun calculateDeterminant(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun transposition(): AbstractMatrix {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getInverse(): AbstractMatrix {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}