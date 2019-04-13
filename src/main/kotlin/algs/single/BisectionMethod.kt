package algs.single

import algs.AbstractAlgorithm
import extensions.times
import containers.AlgorithmDataContainer
import containers.Point
import extensions.minus
import func.FuncService
import func.GlobalFunc
import matrix.AbstractMatrix
import matrix.Matrix
import java.lang.Math.abs

class BisectionMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод деления пополам"
    override val requiredArgs get() = listOf("a", "b")

    private val sigma = 0.0001

    override fun apply(args: Map<String, Any>): AlgorithmDataContainer {
        checkArgs(args)

        var a = args.getValue("a") as Double
        var b = args.getValue("b") as Double
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService

        for(iterations in 1 until maxIterations) {
            val a1 = (b + a - sigma) / 2
            val a2 = (b + a + sigma) / 2
            val f1 = funcService.J(Point(a1))
            val f2 = funcService.J(Point(a2))

            when {
                f1 < f2 -> b = a2
                f1 > f2 -> a = a1
                else -> {
                    a = a1
                    b = a2
                }
            }

            if(abs(b - a) < eps)
                return AlgorithmDataContainer(
                    solution = Point((b + a) / 2),
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
        }
        throw Error(iterationErrorMsg)
    }
}
