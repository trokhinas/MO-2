package containers

import extensions.toVector
import matrix.AbstractMatrix

abstract class DataContainer(var solution: AbstractMatrix, var iteration: Int, var epsilon: Double) {
    override fun toString(): String {
        return "Решение = ${solution.toVector()}\nитераций = $iteration\neps = $epsilon"
    }
}