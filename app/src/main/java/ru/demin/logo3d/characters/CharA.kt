package ru.demin.logo3d.characters

class CharA : BaseChar() {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()
        vertex.run {
            addAll(
                listOf(
                    -4f, -4f, z,
                    -2f, -4f, z,
                    -1f, -1f, z,
                    1f, -1f, z,
                    2f, -4f, z,
                    4f, -4f, z,
                    1f, 4f, z,
                    -1f, 4f, z,
                    -1f, 1f, z,
                    1f, 1f, z,
                    0f, 3f, z
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
            addAll(addSurface(7, 0))
            addAll(addSurface(8, 9))
            addAll(addSurface(9, 10))
            addAll(addSurface(10, 8))
        }
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        index.addAll(
            listOf(
                0, 1, 8,
                2, 1, 8,
                2, 3, 8,
                9, 3, 8,
                9, 3, 4,
                9, 5, 4,
                9, 5, 6,
                9, 10, 6,
                7, 10, 6,
                7, 10, 8,
                7, 0, 8
            )
        )
        return index.map { it + offset }
    }
}