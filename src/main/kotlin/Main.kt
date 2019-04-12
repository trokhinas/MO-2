import algs.AlgorithmI
import algs.multi.FastDownMethod
import algs.multi.NewtonsMethod
import algs.multi.PenaltyMethod
import algs.multi.StepDivideMethod
import containers.DataContainer
import func.GlobalFunc
import matrix.AbstractMatrix
import matrix.Matrix
import org.mariuszgromada.math.mxparser.Function
import java.util.*

fun main() {
    val algs = listOf(StepDivideMethod(), FastDownMethod(), NewtonsMethod(), PenaltyMethod())
    val bannedAlgs = listOf(StepDivideMethod(), FastDownMethod(), NewtonsMethod())
    val u = Matrix(arrayOf(
        doubleArrayOf(3.0, 2.0))
    )

    val args = mapOf("a" to 0.1, "u" to u)

    var data = ArrayList<DataContainer>()
    for (alg in algs) {
        if (bannedAlgs.contains(alg)) continue

        val container = alg.apply(args)
        data.add(container)
    }

    data.forEach { println("$it\n") }

//    var funcService = GlobalFunc("J(u1,u2) = (u1 - u2) * (u1 - u2) + u1 - 5 * u2")
//    println(funcService.J(u))
//    println(funcService.JDiff_U1(u))
}



