package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day01.txt")!!.readText().trim()

    println("Part 1: " + solve(input, 1))
    println("Part 2: " + solve(input, 3))
}

private fun solve(input: String, count: Int): Int {
    return input.split("\n\n")
        .map { it.lines().sumOf(String::toInt) }
        .sortedDescending()
        .take(count)
        .sum()
}
