package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day03.txt")!!.readText().trim()

    println("Part 1: " + solve(input.lines(), 1))
    println("Part 2: " + solve(input.lines(), 2))
}

private fun solve(lines: List<String>, part: Int): Int {
    return if (part == 1)
        lines
            .map { it.chunked(it.length / 2) }
            .map { (a, b) -> a.toSet() intersect b.toSet() }
            .sumOf { getCost(it.single()) }
    else
        lines
            .chunked(3)
            .map { (a, b, c) -> a.toSet() intersect b.toSet() intersect c.toSet() }
            .sumOf { getCost(it.single()) }
}

private fun getCost(ch: Char) = if (ch in 'a'..'z') ch - 'a' + 1 else ch - 'A' + 27
