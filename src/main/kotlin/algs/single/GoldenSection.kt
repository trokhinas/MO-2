package algs.single

import algs.AbstractAlgorithm
import containers.AlgorithmDataContainer
import containers.DataContainer
import containers.Point
import extensions.minus
import extensions.plus
import extensions.times
import func.FuncService
import func.GlobalFunc
import matrix.AbstractMatrix
import kotlin.math.abs
import kotlin.math.sqrt

class GoldenSection: AbstractAlgorithm() {
    override val algName: String get() = "Метод золотого сечения"
    override val requiredArgs: List<String> get() = listOf("a", "b")

    private val alpha = (sqrt(5.0) - 1) / 2
    private val alpha1 = (3 - sqrt(5.0)) / 2

    override fun apply(args: Map<String, Any>): DataContainer {
        checkArgs(args)
        var iterations = 0
        var a = Point(args.getValue("a") as Double)
        var b = Point(args.getValue("b") as Double)
        funcService = args.getOrDefault("func", GlobalFunc()) as FuncService

        var u1 = a[0,0] + alpha1 * (b[0,0] - a[0,0])
        var u2 = a[0,0] + alpha * (b[0,0] - a[0,0])

        for (i in 0 until maxIterations) {
            iterations++

            val f1 = funcService.J(Point(u1))
            val f2 = funcService.J(Point(u2))

            when {
                f1 < f2 -> { b = Point(u2); u2 = u1; u1 = (a + alpha1 * (b - a))[0, 0] }
                f1 > f2 -> { a = Point(u1); u1 = u2; u2 = (a + alpha * (b - a))[0, 0] }
                else -> {
                    b = Point(u2)
                    a = Point(u1)
                    u1 = (a + alpha1 * (b - a))[0, 0]
                    u2 = (a + alpha * (b - a))[0, 0]
                }
            }
            if(abs((b - a)[0, 0]) < eps){
                return AlgorithmDataContainer(
                    solution = (b + a) * 0.5,
                    iteration = iterations,
                    epsilon = eps,
                    algName = algName
                )
            }
        }
        throw Error(iterationErrorMsg)
    }
}

private operator fun Double.times(abstractMatrix: AbstractMatrix): AbstractMatrix {
    return abstractMatrix * this
}
