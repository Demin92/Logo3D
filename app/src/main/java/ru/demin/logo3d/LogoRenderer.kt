package ru.demin.logo3d

import android.opengl.GLES32
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import ru.demin.logo3d.characters.CharA
import ru.demin.logo3d.characters.CharE
import ru.demin.logo3d.characters.CharI
import ru.demin.logo3d.characters.CharL
import ru.demin.logo3d.characters.CharM
import ru.demin.logo3d.characters.CharP
import ru.demin.logo3d.characters.CharR
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class LogoRenderer : GLSurfaceView.Renderer {
    val rotation = Rotation(0f, 0f, 0f)

    private val mVPMatrix = FloatArray(16)//model view projection matrix
    private val projectionMatrix = FloatArray(16)//projection mastrix
    private val viewMatrix = FloatArray(16)//view matrix
    private val mVMatrix = FloatArray(16)//model view matrix
    private val modelMatrix = FloatArray(16)//model  matrix
    private val rotateMatrixX = FloatArray(16)//rotate  matrix
    private val rotateMatrixY = FloatArray(16)//rotate  matrix
    private val rotateMatrixZ = FloatArray(16)//rotate  matrix
    private lateinit var charR: CharR
    private lateinit var charM: CharM
    private lateinit var sphere1: Sphere
    private lateinit var sphere2: Sphere

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        GLES32.glClearColor(0f, 0f, 0f, 1f)
        charR = CharR(KOEF / 2)
        charM = CharM(KOEF / 2)
        sphere1 = Sphere(KOEF, 0f)
        sphere2 = Sphere(KOEF, 1f)
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        GLES32.glViewport(0, 0, width, height)
        val ratio = width.toFloat() / height
        val left = -ratio
        Matrix.frustumM(projectionMatrix, 0, left, ratio, -1.0f, 1.0f, 1.0f, 8.0f)
    }

    override fun onDrawFrame(gl: GL10?) {
        drawBackground()
        setupMatrix()
        translate(-2f)
        charR.draw(mVPMatrix)
        translate(1.3f)
        sphere1.draw(mVPMatrix)
        translate(1.3f)
        sphere2.draw(mVPMatrix)
        translate(1.4f)
        charM.draw(mVPMatrix)
    }

    private fun translate(distance: Float) {
        Matrix.translateM(modelMatrix, 0, distance, 0f, 0f)
        Matrix.multiplyMM(mVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.multiplyMM(mVPMatrix, 0, projectionMatrix, 0, mVMatrix, 0)
    }

    private fun setupMatrix() {
        Matrix.setIdentityM(mVPMatrix, 0)//set the model view projection matrix to an identity matrix
        Matrix.setIdentityM(mVMatrix, 0)//set the model view  matrix to an identity matrix
        Matrix.setIdentityM(modelMatrix, 0)//set the model matrix to an identity matrix
        // Set the camera position (View matrix)
        Matrix.setLookAtM(
            viewMatrix, 0,
            0.0f, 0f, 1.0f, //camera is at (0,0,1)
            0f, 0f, 0f, //looks at the origin
            0f, 1f, 0.0f
        )//head is down (set to (0,1,0) to look from the top)
        Matrix.translateM(modelMatrix, 0, 0.0f, 0.0f, -5f)//move backward for 5 units

        //rotation
        Matrix.setRotateM(rotateMatrixX, 0, rotation.xAngle, 1f, 0f, 0f)
        Matrix.setRotateM(rotateMatrixY, 0, rotation.yAngle, 0f, 1f, 0f)
        Matrix.setRotateM(rotateMatrixZ, 0, rotation.zAngle, 0f, 0f, 1f)
        Matrix.multiplyMM(modelMatrix, 0, modelMatrix, 0, rotateMatrixX, 0)
        Matrix.multiplyMM(modelMatrix, 0, modelMatrix, 0, rotateMatrixY, 0)
        Matrix.multiplyMM(modelMatrix, 0, modelMatrix, 0, rotateMatrixZ, 0)

        // Calculate the projection and view transformation
        //calculate the model view matrix
        Matrix.multiplyMM(mVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.multiplyMM(mVPMatrix, 0, projectionMatrix, 0, mVMatrix, 0)
    }

    private fun drawBackground() {
        GLES32.glClear(GLES32.GL_COLOR_BUFFER_BIT or GLES32.GL_DEPTH_BUFFER_BIT)
        GLES32.glClearDepthf(1.0f)//set up the depth buffer
        GLES32.glEnable(GLES32.GL_DEPTH_TEST)//enable depth test (so, it will not look through the surfaces)
        GLES32.glDepthFunc(GLES32.GL_LEQUAL)//indicate what type of depth test
    }

    data class Rotation(
        var xAngle: Float,
        var yAngle: Float,
        var zAngle: Float
    ) {
        private var currentAxis = 0

        fun randStep() {
            val rand = Math.random().toFloat() * RANDOM_DISTANCE
            val axis = Math.random().toFloat() * CHANGE_AXIS_PROBABILITY
            if (axis > CHANGE_AXIS_PROBABILITY - 1) currentAxis++

            when (currentAxis % 3) {
                0 -> xAngle += rand
                1 -> yAngle += rand
                2 -> zAngle += rand
            }
        }

        companion object {
            private const val RANDOM_DISTANCE = 5f
            private const val CHANGE_AXIS_PROBABILITY = 50
        }
    }

    companion object {
        private const val KOEF = 0.3f
    }
}