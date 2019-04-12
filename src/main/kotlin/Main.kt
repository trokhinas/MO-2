import algs.AlgorithmI
import algs.multi.FastDownMethod
import algs.multi.NewtonsMethod
import algs.multi.StepDivideMethod
import containers.DataContainer
import matrix.AbstractMatrix
import matrix.Matrix
import java.util.*

fun main() {
    val algs = listOf(StepDivideMethod(), FastDownMethod(), NewtonsMethod())
    val bannedAlgs = emptyList<AlgorithmI>()
    val u = Matrix(arrayOf(
        doubleArrayOf(1.0, 1.0))
    )

    val args = mapOf("a" to 0.1, "u" to u)

    var data = ArrayList<DataContainer>()
    for (alg in algs) {
        if (bannedAlgs.contains(alg)) continue

        val container = alg.apply(args)
        data.add(container)
    }

    data.forEach { println("$it\n") }
}



