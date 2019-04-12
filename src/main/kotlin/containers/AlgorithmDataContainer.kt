package containers

import matrix.AbstractMatrix

class AlgorithmDataContainer(solution: AbstractMatrix, iteration: Int, epsilon: Double, val algName: String)
    : DataContainer(solution, iteration, epsilon) {

    override fun toString(): String {
        return super.toString().plus("\nалгоритм - $algName" )
    }
}