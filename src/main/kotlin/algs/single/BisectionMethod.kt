package algs.single

import algs.AbstractAlgorithm
import extensions.times
import containers.AlgorithmDataContainer
import extensions.minus
import func.GlobalFunc
import matrix.AbstractMatrix
import matrix.Matrix
import java.lang.Math.abs

class BisectionMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод деления пополам"
    override val requiredArgs get() = listOf("a", "b", "u0")

    private val sigma = 0.0001


    override fun apply(args: Map<String, Any>): AlgorithmDataContainer {
        checkArgs(args)

        var a = args.getValue("a") as Double
        var b = args.getValue("b") as Double
        val eps = GlobalFunc.epsilon
        val u1 = args.getValue("u0") as AbstractMatrix

        for(iterations in 1 until maxIterations) {
            val a1 = (b + a - sigma) / 2
            val a2 = (b + a + sigma) / 2
            val f1 = GlobalFunc.J(u1 - GlobalFunc.gradient(u1) * a1)
            val f2 = GlobalFunc.J(u1 - GlobalFunc.gradient(u1) * a2)

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
                    solution = Matrix(arrayOf(
                        doubleArrayOf((b + a) / 2))
                    ),
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
        }
        throw Error(iterationErrorMsg)
    }
}
