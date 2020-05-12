package ru.demin.logo3d.characters

class CharP : BaseChar() {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()

        val innerControlPoint = listOf(
            1f, 1f,
            1.8f, 1.66f,
            1.8f, 2.33f,
            1f, 3f
        )
        val innerCurve = createBezierCurve(innerControlPoint, z)

        val outerControlPoints = listOf(
            2f, 0f,
            3.66f, 1f,
            3.66f, 3f,
            2f, 4f
        )
        val outerCurve = createBezierCurve(outerControlPoints, z)

        vertex.run {
            addAll(
                listOf(
                    0f, 0f, z,
                    0f, -4f, z,
                    -2f, -4f, z,
                    -2f, 4f, z,
                    0f, 3f, z,
                    0f, 1f, z
                )
            )
            addAll(outerCurve)
            addAll(innerCurve)
        }
        return vertex
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        val count = BEZIER_CURVE_STEPS_COUNT + 1
        for (i in 0 until BEZIER_CURVE_STEPS_COUNT) {
            index.addAll(addSurface(i + MAIN_VERTEX_COUNT, i + MAIN_VERTEX_COUNT + 1, count))
        }

        index.addAll(
            listOf(
                0,
                MAIN_VERTEX_COUNT, count + MAIN_VERTEX_COUNT,
                3, MAIN_VERTEX_COUNT + BEZIER_CURVE_STEPS_COUNT, MAIN_VERTEX_COUNT + count + BEZIER_CURVE_STEPS_COUNT,
                3, 4, MAIN_VERTEX_COUNT + count + BEZIER_CURVE_STEPS_COUNT,
                0, 5, count + MAIN_VERTEX_COUNT,
                3, 4, 0,
                3, 0, 1,
                3, 2, 1
            )
        )
        return index.map { it + offset }
    }

    override fun addBorders(): List<Int> {
        return mutableListOf<Int>().apply {
            for (i in MAIN_VERTEX_COUNT until BEZIER_CURVE_STEPS_COUNT + MAIN_VERTEX_COUNT) {
                addAll(addSurface(i, i + 1))
            }

            for (i in MAIN_VERTEX_COUNT + BEZIER_CURVE_STEPS_COUNT + 1 until 2 * BEZIER_CURVE_STEPS_COUNT + MAIN_VERTEX_COUNT + 1) {
                addAll(addSurface(i, i + 1))
            }

            addAll(addSurface(5, MAIN_VERTEX_COUNT + BEZIER_CURVE_STEPS_COUNT + 1))
            addAll(addSurface(4, MAIN_VERTEX_COUNT + 2 * BEZIER_CURVE_STEPS_COUNT + 1))
            addAll(addSurface(4, 5))
            addAll(addSurface(0, MAIN_VERTEX_COUNT))
            addAll(addSurface(3, MAIN_VERTEX_COUNT + BEZIER_CURVE_STEPS_COUNT))
            addAll(addSurface(0, 1))
            addAll(addSurface(1, 2))
            addAll(addSurface(2, 3))
        }
    }

    private companion object {
        private const val MAIN_VERTEX_COUNT = 6
    }
}