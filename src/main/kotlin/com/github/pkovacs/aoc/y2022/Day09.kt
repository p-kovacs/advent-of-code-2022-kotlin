package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day09.txt")!!.readText().trim()

    println("Part 1: " + solve(input.lines(), 2))
    println("Part 2: " + solve(input.lines(), 10))
}

private fun solve(lines: List<String>, ropeLength: Int): Int {
    val rope = (1..ropeLength).map { Cell() }.toMutableList()
    val visited = mutableSetOf<Cell>()
    for (line in lines) {
        val cnt = line.substring(2).toInt()
        for (k in 1..cnt) {
            rope[0] = rope[0].neighbor(line[0])
            for (i in 1 until rope.size) {
                if (rope[i] != rope[i - 1] && rope[i - 1] !in rope[i].extendedNeighbors()) {
                    rope[i] = rope[i].extendedNeighbors().minBy { rope[i - 1].dist(it) }
                }
            }
            visited.add(rope[rope.size - 1])
        }
    }
    return visited.size
}
