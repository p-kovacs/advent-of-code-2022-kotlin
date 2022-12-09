package com.github.pkovacs.aoc.y2022

/**
 * Represents a cell (position) in 2D space, typically associated with a table or matrix. It is an immutable
 * pair of {@code Int} values: row index and column index. It provides methods to get the neighbors of a cell and
 * the Manhattan distance between two cells. Lexicographical ordering is also supported (first by row index, then
 * by column index).
 */
data class Cell(val row: Int = 0, val col: Int = 0) : Comparable<Cell> {

    companion object {

        /**
         * Returns a list of cells within the given bounds. If both arguments are positive, then the first element
         * of the returned list is `(0, 0)`, and the last element is `(rowCount - 1, colCount - 1)`.
         * Otherwise, an empty list is returned.
         */
        fun list(rowCount: Int, colCount: Int): List<Cell> = list(0, 0, rowCount, colCount)

        /**
         * Returns a list of cells within the given bounds. If `startRow < endRow` and `startCol < endCol`, then
         * the first element of the returned list is `(startRow, startCol)`, and the last element is
         * `(endRow - 1, endCol - 1)`. Otherwise, an empty list is returned.
         */
        fun list(startRow: Int, startCol: Int, endRow: Int, endCol: Int): List<Cell> {
            val rowCount = endRow - startRow
            val colCount = endCol - startCol
            return if (rowCount <= 0 || colCount <= 0) {
                listOf()
            } else {
                (0 until rowCount * colCount).map { Cell(startRow + it / colCount, startCol + it % colCount) }
            }
        }

    }

    /**
     * Returns true if the indices of this cell are between zero (inclusive) and the given row/column count
     * (exclusive).
     */
    fun isValid(rowCount: Int, colCount: Int): Boolean = (row in 0 until rowCount) && (col in 0 until colCount)

    /**
     * Returns the neighbor of this cell in the given direction. (0, 0) represents the upper-left cell among
     * the ones with non-negative indices. The directions are interpreted accordingly, so "north" or "up" means
     * lower row index, while "south" or "down" means higher row index.
     */
    fun neighbor(dir: Direction): Cell = when (dir) {
        Direction.NORTH -> Cell(row - 1, col)
        Direction.EAST -> Cell(row, col + 1)
        Direction.SOUTH -> Cell(row + 1, col)
        Direction.WEST -> Cell(row, col - 1)
    }

    /**
     * Returns the neighbor of this cell in the given direction. (0, 0) represents the upper-left cell among
     * the ones with non-negative indices. The directions are interpreted accordingly, so "north" or "up" means
     * lower row index, while "south" or "down" means higher row index.
     *
     * @param dir the direction character. One of 'N' (north), 'E' (east), 'S' (south), 'W' (west),
     *         'U' (up), 'R' (right), 'D' (down), 'L' (left), and their lowercase variants.
     */
    fun neighbor(dir: Char): Cell = neighbor(Direction.fromChar(dir))

    /**
     * Returns the four neighbors of this cell.
     *
     * @return the four neighbor cells in clockwise order (N, E, S, W)
     */
    fun neighbors(): List<Cell> = listOf(
        Cell(row - 1, col),
        Cell(row, col + 1),
        Cell(row + 1, col),
        Cell(row, col - 1)
    )

    /**
     * Returns the valid neighbors of this cell with respect to the given row count and column count.
     *
     * @return the valid neighbor cells in clockwise order (N, E, S, W)
     */
    fun validNeighbors(rowCount: Int, colCount: Int): List<Cell> =
        neighbors().filter { c -> c.isValid(rowCount, colCount) }

    /**
     * Returns the eight "extended" neighbors of this cell, also including the diagonal ones.
     *
     * @return the eight "extended" neighbor cells in clockwise order (N, NE, E, SE, S, SW, W, NW)
     */
    fun extendedNeighbors(): List<Cell> = listOf(
        Cell(row - 1, col),
        Cell(row - 1, col + 1),
        Cell(row, col + 1),
        Cell(row + 1, col + 1),
        Cell(row + 1, col),
        Cell(row + 1, col - 1),
        Cell(row, col - 1),
        Cell(row - 1, col - 1)
    )

    /**
     * Returns the Manhattan distance between this cell and the given cell.
     */
    fun dist(other: Cell): Int = Math.abs(row - other.row) + Math.abs(col - other.col)

    override fun compareTo(other: Cell) = compareValuesBy(this, other, { it.row }, { it.col })

    override fun toString() = "($row, $col)"

}
