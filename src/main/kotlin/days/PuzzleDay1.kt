package days

import ReadPuzzleInput
import readPuzzleInput
import java.lang.StringBuilder

class PuzzleDay1 : ReadPuzzleInput {

    override val day: String = "day01"
    private val input = readPuzzleInput()

    fun solvePartOne(): Int =
        input
            .map { line -> line.filter { c -> c.isDigit() } }
            .sumOf { "${it.first()}${it.last()}".toInt() }


    fun solvePartTwo() =
        input
            .map { line ->
                var result = line
                validDigits.forEachIndexed { i, d ->
                    // replaces e.g jtk2vfsqsdhcpq9eightwojsc -> jtk2vfsqsdhcpq9{eig8ht}wojsc -> jtk2vfsqsdhcpq9eig8h{tw2o}jsc
                    result = result.replace(d, StringBuilder(d).insert(d.length / 2, i + 1).toString())
                }
                result.filter { it.isDigit() }
            }.sumOf { "${it.first()}${it.last()}".toInt() }


    companion object {
        private val validDigits =
            listOf(
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine"
            )

    }
}