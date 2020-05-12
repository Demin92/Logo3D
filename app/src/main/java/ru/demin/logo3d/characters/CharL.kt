package ru.demin.logo3d.characters

class CharL : BaseChar() {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()
        vertex.run {
            addAll(
                listOf(
                    -2f, -4f, z,
                    4f, -4f, z,
                    4f, -2f, z,
                    0f, -2f, z,
                    0f, 4f, z,
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
            addAll(addSurface(5, 0))
        }
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        index.addAll(
            listOf(
                3, 0, 1,
                3, 2, 1,
                3, 0, 5,
                3, 4, 5
            )
        )
        return index.map { it + offset }
    }
}