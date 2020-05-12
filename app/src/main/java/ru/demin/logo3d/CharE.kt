package ru.demin.logo3d

class CharE : BaseChar() {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()
        vertex.run {
            addAll(
                listOf(
                    -2f, -4f, z,
                    4f, -4f, z,
                    4f, -2f, z,
                    0f, -2f, z,
                    0f, -1f, z,
                    2f, -1f, z,
                    2f, 1f, z,
                    0f, 1f, z,
                    0f, 2f, z,
                    4f, 2f, z,
                    4f, 4f, z,
                    -2f, 4f, z
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
            addAll(addSurface(11, 0))
        }
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        index.addAll(
            listOf(
                0, 3, 1,
                2, 3, 1,
                0, 3, 7,
                0, 3, 7,
                4, 5, 7,
                6, 5, 7,
                0, 11, 7,
                8, 11, 7,
                8, 11, 10,
                8, 9, 10
            )
        )
        return index.map { it + offset }
    }
}