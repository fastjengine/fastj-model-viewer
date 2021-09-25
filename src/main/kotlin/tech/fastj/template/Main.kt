package tech.fastj.template

import tech.fastj.engine.FastJEngine

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        FastJEngine.log("A model file must be specified.")
        return
    }

    FastJEngine.init("Model ${args[0]}", ModelViewer(args))
    FastJEngine.setShouldThrowExceptions(true)

    try {
        FastJEngine.run()
    } catch (e: Exception) {
        e.printStackTrace()
        FastJEngine.forceCloseGame()
    }
}

