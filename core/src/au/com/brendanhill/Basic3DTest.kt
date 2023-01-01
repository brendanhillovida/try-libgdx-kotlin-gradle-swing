package au.com.brendanhill

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Cursor.SystemCursor
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.VertexAttributes
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import kotlin.random.Random


// Inspiration from https://xoppa.github.io/blog/basic-3d-using-libgdx/

class Basic3DTest : ApplicationListener {

    private lateinit var cam: PerspectiveCamera

    private lateinit var model: Model

    private lateinit var instance: ModelInstance

    private lateinit var modelBatch: ModelBatch

    private lateinit var environment: Environment

    private lateinit var camController: CameraInputController

    private val points = mutableListOf<DataPointEntity3D>()

    override fun create() {

        environment = Environment()
        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f))

        modelBatch = ModelBatch()

        cam = PerspectiveCamera(67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        cam.position.set(10f, 10f, 10f)
        cam.lookAt(0F, 0F, 0F)
        cam.near = 1f
        cam.far = 300f
        cam.update()

        val modelBuilder = ModelBuilder()
        model = modelBuilder.createBox(
            5f, 5f, 5f,
            Material(ColorAttribute.createDiffuse(Color.GREEN)),
            (VertexAttributes.Usage.Position or VertexAttributes.Usage.Normal).toLong()
        )
        instance = ModelInstance(model)

        camController = CameraInputController(cam);
        camController.rotateLeftKey = 'q'.toInt()
        camController.rotateRightKey = 'e'.toInt()
        Gdx.input.setInputProcessor(camController);

    }

    var lastMs = System.currentTimeMillis()
    override fun render() {

        //System.out.println((1000.0 / (System.currentTimeMillis() - lastMs)).toInt().toString() + " fps" )
        //lastMs = System.currentTimeMillis()
//https://xoppa.github.io/blog/interacting-with-3d-objects/

        camController.update()



        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        modelBatch.begin(cam)
//        modelBatch.render(instance, environment)

        points.forEach {
            if (it.modelInstance == null) {
                it.buildMe()
            }
            modelBatch.render(it.modelInstance, environment)
        }
        modelBatch.end()
    }
    override fun dispose() {
        modelBatch.dispose()
        model.dispose();
    }
    override fun resume() {}
    override fun resize(width: Int, height: Int) {}
    override fun pause() {}


    val r = Random(1)
    fun addRandomPoint() {
        val newDataPoint = DataPointEntity3D(
            (r.nextFloat() - 0.5F) * 20.0F ,
            (r.nextFloat() - 0.5F) * 20.0F ,
            (r.nextFloat() - 0.5F) * 20.0F ,
            Color(r.nextFloat()/2.0F + 0.5F,r.nextFloat()/2.0F + 0.5F,r.nextFloat()/2.0F + 0.5F, 1.0F),
            0.1F // r.nextFloat() * 0.2F
        )

        this.points.add(newDataPoint)
    }


}