package algs.multi

import algs.AbstractAlgorithm
import containers.AlgorithmDataContainer
import containers.DataContainer
import extensions.minus
import extensions.times
import func.GlobalFunc
import matrix.AbstractMatrix


class StepDivideMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод дробления шага пополам"
    override val requiredArgs: List<String> get() = listOf("a", "u")


    private val eps = GlobalFunc.epsilon

    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var a = args.getValue("a") as Double
        var u0 = args.getValue("u") as AbstractMatrix

        for (iterations in 1 until maxIterations) {
            val grad = GlobalFunc.gradient(u0)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u0,
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
            }

            var u1 = u0 - grad * a
            while (GlobalFunc.J(u1) >= GlobalFunc.J(u0)) {
                a /= 2
                u1 = u0 - grad * a
            }
            u0 = u1
        }
        throw Error(iterationErrorMsg)
    }
}



