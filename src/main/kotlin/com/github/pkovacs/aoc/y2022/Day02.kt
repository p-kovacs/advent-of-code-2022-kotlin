package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day02.txt")!!.readText().trim()

    fun playRound(s1: Int, s2: Int) = (s2 - s1 + 4) % 3
    fun getScore(s1: Int, s2: Int) = playRound(s1, s2) * 3 + s2 + 1
    fun getSecondShape(s1: Int, result: Int) = (s1 + result + 2) % 3
    // or: fun getSecondShape(s1: Int, result: Int) = (0..2).find { playRound(s1, it) == result }!!

    fun solve(input: String, part: Int): Int {
        return input.lines()
            .map { it[0] - 'A' to it[2] - 'X' }
            .sumOf { (a, b) -> if (part == 1) getScore(a, b) else getScore(a, getSecondShape(a, b)) }
    }

    println("Part 1: " + solve(input, 1))
    println("Part 2: " + solve(input, 2))
}
