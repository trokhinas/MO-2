import algs.AlgorithmI
import algs.FastDownMethod
import algs.StepDivideMethod
import containers.DataContainer
import func.Vector


fun main() {
    val algs = listOf(StepDivideMethod(), FastDownMethod())
    val bannedAlgs = emptyList<AlgorithmI>()

    val args = mapOf("a" to 5.0, "u" to Vector(listOf(-0.3611, 1.8056)))

    var data = ArrayList<DataContainer>()
    for (alg in algs) {
        if (bannedAlgs.contains(alg)) continue

        val container = alg.apply(args)
        data.add(container)
    }

    data.forEach { println("$it\n") }
}

public operator fun Double.times(grad: Vector): Vector {
    return grad.times(this)
}
