package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day04.txt")!!.readText().trim()

    fun solve(lines: List<String>, part: Int): Int {
        return lines
            .map { it.split(",", "-").map(String::toInt) }
            .map { (a1, a2, b1, b2) -> (a1..a2).toSet() to (b1..b2).toSet() }
            .count { (a, b) ->
                if (part == 1) {
                    a intersect b == a || a intersect b == b
                } else {
                    (a intersect b).isNotEmpty()
                }
            }
    }

    println("Part 1: " + solve(input.lines(), 1))
    println("Part 2: " + solve(input.lines(), 2))
}
