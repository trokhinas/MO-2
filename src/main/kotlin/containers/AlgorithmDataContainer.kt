package containers

import func.Vector

class AlgorithmDataContainer(solution: Vector, iteration: Int, epsilon: Double, val algName: String)
    : DataContainer(solution, iteration, epsilon) {

    override fun toString(): String {
        return super.toString().plus("\nалгоритм - $algName" )
    }
}