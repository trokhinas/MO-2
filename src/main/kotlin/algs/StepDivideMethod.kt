package algs

import containers.AlgorithmDataContainer
import containers.DataContainer
import func.GlobalFunc
import func.Vector


class StepDivideMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод дробления шага пополам"
    override val requiredArgs: List<String> get() = listOf("a", "u")

    private val eps = GlobalFunc.epsilon

    override fun apply(args: HashMap<String, Any>): DataContainer {
        checkArgs(args)

        var a = args["a"]!! as Double
        var u = args["u"]!! as Vector

        for (iterations in 1 until maxIterations) {
            val grad = GlobalFunc.gradient(u)

            if (GlobalFunc.gradValue(u) < eps) {
                return AlgorithmDataContainer(
                    solution = u,
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
            }

            u -= a * grad
        }
        throw Error(iterationErrorMsg)
    }
}

public operator fun Double.times(grad: Vector): Vector {
    return grad.times(this)
}
