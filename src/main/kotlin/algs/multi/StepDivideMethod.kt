package algs.multi

import algs.AbstractAlgorithm
import containers.AlgorithmDataContainer
import containers.DataContainer
import extensions.minus
import extensions.times
import func.FuncService
import func.GlobalFunc
import matrix.AbstractMatrix


class StepDivideMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод дробления шага пополам"
    override val requiredArgs: List<String> get() = listOf("a", "u")


    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var a = args.getValue("a") as Double
        var u0 = args.getValue("u") as AbstractMatrix
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService


        for (iterations in 0 until maxIterations) {
            val grad = funcService.gradient(u0)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u0,
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
            }

            var u1 = u0 - grad * a
            while (funcService.J(u1) >= funcService.J(u0)) {
                a /= 2
                u1 = u0 - grad * a
            }
            u0 = u1
        }
        throw Error(iterationErrorMsg)
    }
}



