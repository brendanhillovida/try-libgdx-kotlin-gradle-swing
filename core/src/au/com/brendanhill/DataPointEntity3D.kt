package au.com.brendanhill

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.Material
import com.badlogic.gdx.graphics.g3d.Model
import com.badlogic.gdx.graphics.g3d.ModelInstance
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder


class DataPointEntity3D(
    val x: Float,
    val y: Float,
    val z: Float,
    val color: Color,
    val size: Float
) {

    var model: Model? = null

    var modelInstance: ModelInstance? = null

    fun buildMe() {
        val modelBuilder = ModelBuilder()

        model = modelBuilder.createBox(
            size, size, size, Material(ColorAttribute.createDiffuse(color)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong())

        modelInstance = ModelInstance(model, x, y, z)

    }
}