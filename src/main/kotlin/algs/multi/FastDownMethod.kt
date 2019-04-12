package algs.multi

import algs.AbstractAlgorithm
import algs.single.BisectionMethod
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
    override val maxIterations: Int get() = 5

    lateinit var funcService: FuncService


    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var u = args.getValue("u") as AbstractMatrix
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService
        val eps = GlobalFunc.epsilon

        for (iteration in 1 until maxIterations) {
            val grad = funcService.gradient(u)

            if (GlobalFunc.module(grad) < eps) {
                return AlgorithmDataContainer(
                    solution = u,
                    iteration = iteration,
                    epsilon = eps,
                    algName = algName
                )
            }

            val method = BisectionMethod()
            val a = method.apply(mapOf(
                "a" to 0.0,
                "b" to 1.0,
                "u0" to u,
                "func" to funcService)
            )
            u -= grad * a.solution[0, 0]
        }
        throw Error(iterationErrorMsg)
    }
}