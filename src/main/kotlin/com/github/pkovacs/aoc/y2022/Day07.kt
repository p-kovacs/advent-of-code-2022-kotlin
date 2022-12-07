package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day07.txt")!!.readText().trim()

    val dirs = mutableMapOf<String, Long>()
    val paths = mutableListOf<String>()
    for (line in input.lines()) {
        if (line == "$ cd ..") {
            paths.removeLast()
        } else if (line.startsWith("$ cd")) {
            val dirName = line.split(" ")[2]
            paths.add(if (paths.isEmpty()) dirName else paths.last() + dirName + "/")
        } else if (!line.startsWith("$") && !line.startsWith("dir")) {
            val size = line.split(" ")[0].toLong()
            paths.forEach { p -> dirs[p] = (dirs[p] ?: 0) + size }
        }
    }

    val minToFree = dirs.getValue("/") - 40000000L

    println("Part 1: " + dirs.values.filter { v -> v <= 100_000L }.sum())
    println("Part 2: " + dirs.values.filter { v -> v >= minToFree }.min())
}
