package algs

//import algorithm.containers.DataContainer
import containers.DataContainer

interface AlgorithmI {
    fun apply(args: HashMap<String, Any>) : DataContainer
}