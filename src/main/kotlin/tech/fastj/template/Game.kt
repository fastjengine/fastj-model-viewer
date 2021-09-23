package tech.fastj.template

import tech.fastj.engine.FastJEngine
import tech.fastj.graphics.display.Display
import tech.fastj.graphics.display.RenderSettings
import tech.fastj.graphics.game.Text2D
import tech.fastj.systems.control.SimpleManager

fun main() {
    FastJEngine.init("FastJ Kotlin Template", Game())
    FastJEngine.run()
}

class Game : SimpleManager() {
    override fun init(display: Display?) {
        /* Some crispy anti-aliasing for the road. */
        display?.modifyRenderSettings(RenderSettings.Antialiasing.Enable)

        /* A very simple Text2D object, welcoming you to FastJ! */
        val helloFastJText = Text2D.fromText("Hello, FastJ 1.5.0!")
        helloFastJText.translate(display?.screenCenter)
        drawableManager.addGameObject(helloFastJText)
    }

    override fun update(display: Display?) {
    }
}

