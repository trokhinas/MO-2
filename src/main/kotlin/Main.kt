import algs.AlgorithmI
import algs.multi.FastDownMethod
import algs.multi.NewtonsMethod
import algs.multi.StepDivideMethod
import containers.DataContainer
import func.Vector


fun main() {
    val algs = listOf(StepDivideMethod(), FastDownMethod(), NewtonsMethod())
    val bannedAlgs = emptyList<AlgorithmI>()

    val args = mapOf("a" to 0.1, "u" to Vector(listOf(1.0, 1.0)))

    var data = ArrayList<DataContainer>()
    for (alg in algs) {
        if (bannedAlgs.contains(alg)) continue

        val container = alg.apply(args)
        data.add(container)
    }

    data.forEach { println("$it\n") }
}

