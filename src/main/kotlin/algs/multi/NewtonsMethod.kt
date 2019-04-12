package algs.multi

import algs.AbstractAlgorithm
import containers.AlgorithmDataContainer
import containers.DataContainer
import extensions.minus
import func.GlobalFunc
import matrix.AbstractMatrix

class NewtonsMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод Ньютона"
    override val requiredArgs: List<String> get() = listOf("u")

    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var u0 = args.getValue("u") as AbstractMatrix
        val eps = GlobalFunc.epsilon

        for (iterations in 1 until maxIterations) {
            var grad = GlobalFunc.gradient(u0)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u0,
                    epsilon = eps,
                    iteration = iterations,
                    algName = algName
                )
            }

            u0 -= (grad * GlobalFunc.getH(u0).inverse)
        }
        throw Error(iterationErrorMsg)
    }
}

private operator fun AbstractMatrix.times(inverse: AbstractMatrix): AbstractMatrix {
    return this.mulMatrix(inverse)
}

