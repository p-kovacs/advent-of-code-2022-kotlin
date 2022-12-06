package com.github.pkovacs.aoc.y2022

fun main() {
    val input = {}::class.java.getResource("day05.txt")!!.readText().trim()

    fun solve(input: String, part: Int): String {
        val blocks = input.split("\n\n").map { it.lines() }

        val stackCount = blocks[0].last().length / 4 + 1;
        val stacks = (0 until stackCount).map { mutableListOf<Char>() }

        val stackLines = blocks[0].dropLast(1).reversed()
        for (line in stackLines) {
            line.chunked(4).forEachIndexed { i, chunk ->
                if (chunk[1] != ' ') {
                    stacks[i].add(chunk[1])
                }
            }
        }

        for (step in blocks[1]) {
            val d = step.split(" ")
            val cnt = d[1].toInt()
            val from = stacks[d[3].toInt() - 1]
            val to = stacks[d[5].toInt() - 1]

            if (part == 1) {
                repeat(cnt) { to.add(from.removeLast()) }
            } else {
                to.addAll(from.subList(from.size - cnt, from.size))
                repeat(cnt) { from.removeLast() }
            }
        }

        return stacks.map { it.last() }.joinToString(separator = "")
    }

    println("Part 1: " + solve(input, 1))
    println("Part 2: " + solve(input, 2))
}
