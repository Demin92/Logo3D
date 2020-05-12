package ru.demin.logo3d.characters

class CharM(koef: Float) : BaseChar(koef) {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()
        vertex.run {
            addAll(
                listOf(
                    0f, 0f, z,
                    1f, 4f, z,
                    3f, 4f, z,
                    5f, -4f, z,
                    3f, -4f, z,
                    2f, 1f, z,
                    1f, -4f, z,
                    -1f, -4f, z,
                    -2f, 1f, z,
                    -3f, -4f, z,
                    -5f, -4f, z,
                    -3f, 4f, z,
                    -1f, 4f, z
                )
            )
        }
        return vertex
    }

    override fun addBorders(): List<Int> {
        return mutableListOf<Int>().apply {
            addAll(addSurface(0, 1))
            addAll(addSurface(1, 2))
            addAll(addSurface(2, 3))
            addAll(addSurface(3, 4))
            addAll(addSurface(4, 5))
            addAll(addSurface(5, 6))
            addAll(addSurface(6, 7))
            addAll(addSurface(7, 8))
            addAll(addSurface(8, 9))
            addAll(addSurface(9, 10))
            addAll(addSurface(10, 11))
            addAll(addSurface(11, 12))
            addAll(addSurface(12, 0))
        }
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        index.addAll(
            listOf(
                1, 2, 5,
                3, 2, 5,
                3, 4, 5,
                0, 1, 5,
                0, 6, 5,
                0, 6, 7,
                0, 8, 7,
                0, 8, 12,
                11, 8, 12,
                11, 8, 10,
                9, 8, 10
            )
        )
        return index.map { it + offset }
    }

    override fun createVertexColor(vertexSize: Int): FloatArray {
        val vertex = mutableListOf<Float>()
        for (i in 0 until vertexSize/2) {
            vertex.addAll(listOf(1f, 0f, 1f, 1f))
        }
        for (i in 0 until vertexSize/2) {
            vertex.addAll(listOf(0f, 1f, 1f, 1f))
        }
        return vertex.toFloatArray()
    }
}