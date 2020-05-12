package ru.demin.logo3d.characters

class CharI : BaseChar() {
    override fun createVertex(z: Float): List<Float> {
        val vertex = mutableListOf<Float>()
        vertex.run {
            addAll(
                listOf(
                    1f, 4f, z,
                    1f, -4f, z,
                    -1f, -4f, z,
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
            addAll(addSurface(0, 3))
        }
    }

    override fun createSurfaceIndexArray(offset: Int): List<Int> {
        val index = mutableListOf<Int>()
        index.addAll(
            listOf(
                3, 0, 1,
                3, 2, 1
            )
        )
        return index.map { it + offset }
    }
}