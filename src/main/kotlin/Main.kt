import func.Vector


fun main() {
    val v1 = Vector(listOf(0.0, 2.0))
    val v2 = Vector(listOf(1.0, 2.0))

    val v3:Vector = v1 * 5.0
    val v4 = v2 - v1
    val v5 = v1 - v4
    println(v1)
    println(v2)
    println(v3)
    println(v4)
    println(v5)
}

public operator fun Double.times(grad: Vector): Vector {
    return grad.times(this)
}
