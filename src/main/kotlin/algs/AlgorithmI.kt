package algs

//import algorithm.containers.DataContainer
import containers.DataContainer

interface AlgorithmI {
    fun apply(args: Map<String, Any>) : DataContainer
}