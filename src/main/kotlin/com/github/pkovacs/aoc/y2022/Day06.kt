package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day06.txt")!!.readText().trim()

    println("Part 1: " + solve(input, 4))
    println("Part 2: " + solve(input, 14))
}

private fun solve(input: String, count: Int): Int {
    return (count..input.length).find { input.substring(it - count, it).toSet().size == count }!!
}
