package algs.multi

import algs.AbstractAlgorithm
import algs.single.BisectionMethod
import algs.single.GoldenSection
import containers.AlgorithmDataContainer
import containers.DataContainer
import extensions.minus
import matrix.AbstractMatrix
import extensions.times
import func.FuncService
import func.GlobalFunc

class FastDownMethod: AbstractAlgorithm() {
    override val algName: String get() = "Алгоритм наискорейшего спуска"
    override val requiredArgs: List<String> get() = listOf("u")


    override fun apply(args: Map<   String, Any>): DataContainer {
        checkArgs(args)

        var u0 = args.getValue("u") as AbstractMatrix
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService

        for (iteration in 0 until maxIterations) {
            val grad = funcService.gradient(u0)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u0,
                    iteration = iteration,
                    epsilon = eps,
                    algName = algName
                )
            }

            val method = BisectionMethod()
            val singleFuncService = object : GlobalFunc() {
                override fun J(U: AbstractMatrix): Double {
                    return funcService.J(u0 - grad * U[0, 0])
                }
            }
            val a = method.apply(mapOf(
                "a" to 0.0,
                "b" to 1.0,
                "func" to singleFuncService)
            )
            u0 -= grad * a.solution[0, 0]
        }
        throw Error(iterationErrorMsg)
    }
}