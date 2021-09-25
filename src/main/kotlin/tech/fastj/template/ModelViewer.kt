package tech.fastj.template

import tech.fastj.graphics.display.Display
import tech.fastj.graphics.display.RenderSettings
import tech.fastj.graphics.game.Model2D
import tech.fastj.resources.models.ModelUtil
import tech.fastj.systems.control.SimpleManager
import java.nio.file.Path

class ModelViewer(modelFiles: Array<String>) : SimpleManager() {

    private val modelPath: Path = Path.of(modelFiles[0])

    override fun init(display: Display?) {
        display?.modifyRenderSettings(RenderSettings.Antialiasing.Enable)

        val model = Model2D.fromPolygons(ModelUtil.loadModel(modelPath))
        drawableManager.addGameObject(model)
    }

    override fun update(display: Display?) {
    }
}
