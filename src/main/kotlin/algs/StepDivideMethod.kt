package algs

import containers.AlgorithmDataContainer
import containers.DataContainer
import func.GlobalFunc
import func.Vector


public class StepDivideMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод дробления шага пополам"
    override val requiredArgs: List<String> get() = listOf("a", "u")


    private val eps = GlobalFunc.epsilon

    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var a = args.getValue("a") as Double
        var u = args.getValue("u") as Vector

        for (iterations in 1 until maxIterations) {
            val grad = GlobalFunc.gradient(u)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u,
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
            }

            var u1 = u - a * grad
            var JU = GlobalFunc.J(u)
            var JU1 = GlobalFunc.J(u1)
            while (GlobalFunc.J(u1) >= GlobalFunc.J(u)) {
                a /= 2
                u1 = u - a * grad
            }
            u = u1
        }
        throw Error(iterationErrorMsg)
    }
}

public operator fun Double.times(grad: Vector): Vector {
    return grad.times(this)
}
