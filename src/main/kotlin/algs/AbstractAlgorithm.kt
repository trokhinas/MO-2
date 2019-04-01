package algs


abstract class AbstractAlgorithm: AlgorithmI {
    protected val iterationErrorMsg = "$algName превысил предел в $maxIterations итераций!"
    protected fun checkArgs(args: Map<String, Any>) {
        for(arg in requiredArgs) {
            if(!args.keys.contains(arg))
                throw IllegalArgumentException("Не передан аргумент \"$arg\", для работы $algName")
        }
        this.args = args
    }
    protected lateinit var args:Map<String, Any>

    open val algName: String get() = this.javaClass.name
    open val requiredArgs: List<String> get() = listOf()
    open val maxIterations: Int get() = 100

    override fun equals(other: Any?): Boolean {
        if(this === other) return true

        if(other is AbstractAlgorithm) {
            return other.algName == algName
        }
        return false
    }

    override fun hashCode(): Int {
        return iterationErrorMsg.hashCode()
    }

}