package com.github.pkovacs.aoc.y2022

/**
 * Represents a direction in 2D space.
 * Provides methods to convert from/to characters and to rotate a direction.
 */
enum class Direction {

    NORTH, EAST, SOUTH, WEST;

    companion object {

        /**
         * Returns the direction corresponding to the given character.
         *
         * @param ch the direction character. One of 'N' (north), 'E' (east), 'S' (south), 'W' (west),
         * 'U' (up), 'R' (right), 'D' (down), 'L' (left), and their lowercase variants.
         */
        fun fromChar(ch: Char): Direction = when (ch) {
            'n', 'N', 'u', 'U' -> NORTH
            'e', 'E', 'r', 'R' -> EAST
            's', 'S', 'd', 'D' -> SOUTH
            'w', 'W', 'l', 'L' -> WEST
            else -> throw IllegalArgumentException("Unknown direction: '$ch'.")
        }

    }

    /**
     * Returns the uppercase character corresponding to this direction.
     *
     * @return 'N', 'E', 'S', or 'W'.
     */
    fun toChar(): Char = when (this) {
        NORTH -> 'N'
        EAST -> 'E'
        SOUTH -> 'S'
        WEST -> 'W'
    }

    /**
     * Returns the lowercase character corresponding to this direction.
     *
     * @return 'n', 'e', 's', or 'w'.
     */
    fun toLowercaseChar(): Char = when (this) {
        NORTH -> 'n'
        EAST -> 'e'
        SOUTH -> 's'
        WEST -> 'w'
    }

    /**
     * Rotates this direction 90 degrees to the right.
     */
    fun rotateRight(): Direction = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    /**
     * Rotates this direction 90 degrees to the left.
     */
    fun rotateLeft(): Direction = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }

    /**
     * Returns the opposite of this direction.
     */
    fun opposite(): Direction = when (this) {
        NORTH -> SOUTH
        EAST -> WEST
        SOUTH -> NORTH
        WEST -> EAST
    }

}
