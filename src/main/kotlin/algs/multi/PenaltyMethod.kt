package algs.multi

import algs.AbstractAlgorithm
import containers.AlgorithmDataContainer
import containers.DataContainer
import func.FuncService
import func.GlobalFunc
import matrix.AbstractMatrix
import matrix.Matrix
import java.lang.Error
import kotlin.math.abs
import kotlin.math.pow

class PenaltyMethod: AbstractAlgorithm() {
    override val algName: String get() = "Метод штрафных функций"
    override val requiredArgs: List<String> get() = listOf("u")

    lateinit var funcService: FuncService

    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)

        var u = args.getValue("u") as AbstractMatrix
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService

        val eps = GlobalFunc.epsilon
        var r = 1.0
        var C = 10



        for (iterations in 0 until maxIterations) {
            var method = FastDownMethod()
            var anotherFunc = object : GlobalFunc() {
                override fun JDiff_U1(U: AbstractMatrix): Double {
                    return super.JDiff_U1(U) - 1.0 / U[0, 0].pow(2)
                }

                override fun JDiff_U2(U: AbstractMatrix): Double {
                    return super.JDiff_U2(U) - 1.0 / U[0, 1].pow(2)
                }

                override fun J(U: AbstractMatrix): Double {
                    return F(U, r)
                }
            }
            var u1 = method.apply(
                mapOf(
                    "u" to u,
                    "func" to anotherFunc
                )
            ).solution

            if (r <= eps) {
                return AlgorithmDataContainer(
                    solution = u,
                    epsilon = eps,
                    algName = algName,
                    iteration = iterations
                )
            }
            r /= C
            u = u1
        }
        throw Error(iterationErrorMsg)
    }

    private fun F(U: AbstractMatrix, r: Double): Double {
        return funcService.J(U) + P(U, r)
    }

    private fun P(U: AbstractMatrix, r: Double): Double {
        val u1 = U[0, 0]
        val u2 = U[0, 1]


        return r * abs(1.0 / u1 + 1.0 / u2)
    }
}