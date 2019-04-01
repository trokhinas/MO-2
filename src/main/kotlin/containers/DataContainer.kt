package containers

import func.Vector

abstract class DataContainer(var solution: Vector, var iteration: Int, var epsilon: Double) {
    override fun toString(): String {
        return "Решение = $solution,\nитераций = $iteration,\neps = $epsilon"
    }
}