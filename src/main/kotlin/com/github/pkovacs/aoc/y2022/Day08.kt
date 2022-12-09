package com.github.pkovacs.aoc.y2022

import kotlin.math.min

fun main() {
    val input = {}::class.java.getResource("day08.txt")!!.readText().trim()

    val table = input.lines()
    val cells = Cell.list(table.size, table[0].length)

    println("Part 1: " + cells.count { isVisible(table, it) })
    println("Part 2: " + cells.maxOf { getScore(table, it) })
}

private fun isVisible(table: List<String>, cell: Cell): Boolean {
    val current = table[cell.row][cell.col]
    return Direction.values().any { dir -> collectTrees(table, cell, dir).all { it < current } }
}

private fun getScore(table: List<String>, cell: Cell): Long {
    val current = table[cell.row][cell.col]
    var prod: Long = 1
    for (dir in Direction.values()) {
        val trees = collectTrees(table, cell, dir)
        prod *= min(trees.takeWhile { it < current }.size + 1, trees.size)
    }
    return prod
}

private fun collectTrees(table: List<String>, cell: Cell, dir: Direction): List<Char> {
    return generateSequence(cell.neighbor(dir)) { it.neighbor(dir) }
        .takeWhile { (i, j) -> i in table.indices && j in table[0].indices }
        .map { (i, j) -> table[i][j] }
        .toList()
}
