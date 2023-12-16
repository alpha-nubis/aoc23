package days

import ReadPuzzleInput
import readPuzzleInput

class PuzzleDay3 : ReadPuzzleInput {

    override val day: String = "day03"
    private val input = readPuzzleInput()

    // not 553035, 548403
    fun solvePartOne() = partNoWithPosition(input) { inp, nums -> validPartNo(inp, nums) }.sum()

    // not 77749730
    fun solvePartTwo(lines: List<String> = input): Int {
        val partNoWithPosition = getPartNoWithPosition(lines)
        val partAsRange = mapValidPartsIndicesToRages(partNoWithPosition)
        val gears = getGears(lines)
        val gearsRatio = mutableListOf<List<Int>>()
        gears.forEach { pair ->
            val list = mutableListOf<Pair<Int, Pair<Int, IntRange>>>()
            for (i in pair.first - 1..pair.first + 1) {
                for (j in pair.second - 1..pair.second + 1) {
                    val res = partAsRange.filter { it.second.first == i && j in it.second.second }
                    if (res.isNotEmpty()) {
                        res.forEach { list.add(it) }
                    }
                }
            }
            val normalized = list.toSet().map { it.first }
            gearsRatio.add(normalized)
        }
        return gearsRatio.filter { it.size == 2 }.map { it.fold(1) { a: Int, c -> a * c } }.sum()
    }

    private fun partNoWithPosition(
        lines: List<String> = input.take(5),
        block: (List<String>, List<Pair<String, Pair<Int, Int>>>) -> List<Int>
    ): List<Int> {
        val digitsWithIndices = getPartNoWithPosition(lines)
        return block(lines, digitsWithIndices.toList())
    }

    private fun getPartNoWithPosition(
        lines: List<String> = input.take(5),
    ): List<Pair<String, Pair<Int, Int>>> {
        val digitsWithIndices = mutableListOf<Pair<String, Pair<Int, Int>>>()
        val stack = ArrayDeque<Pair<Int, Int>>()
        var s = ""
        for ((i, line) in lines.withIndex()) {
            if (s.isNotEmpty()) digitsWithIndices.add(s to stack.removeLast())
            s = ""
            for ((j, c) in line.withIndex()) {
                if (c.isDigit()) {
                    if (s.isEmpty()) stack.add(i to j)
                    s += c.toString()
                } else {
                    if (s.isNotEmpty()) {
                        digitsWithIndices.add(s to stack.removeLast())
                        s = ""
                    }
                }
            }
        }
        if (s.isNotEmpty()) digitsWithIndices.add(s to stack.removeLast())
        return digitsWithIndices.toList()
    }


    private fun validPartNo(input: List<String>, numberInGrid: List<Pair<String, Pair<Int, Int>>>): List<Int> {
        return numberInGrid.map {
            val numLength = it.first.length
            val (x, y) = it.second
            // (3,(0,25))
            // dann -1..1  und 24..25+3
            for (i in (x - 1)..(x + 1)) {
                if (i < 0 || i >= input.size) continue
                for (j in (y - 1)..(y + numLength)) {
                    if (j < 0 || j >= input[i].length) continue
                    val c = input[i][j]
                    if (!c.isDigit() && c != '.') {
                        return@map it.first.toInt()
                    }
                }
            }
            -1
        }.filter { it != -1 }
    }

    private fun mapValidPartsIndicesToRages(numberInGrid: List<Pair<String, Pair<Int, Int>>>) =
        numberInGrid.map {
            Pair(it.first.toInt(), it.second.first to it.second.second..<it.second.second + it.first.length)
        }

    private fun getGears(lines: List<String>) =
        lines.flatMapIndexed { x, line ->
            line.mapIndexedNotNull { y, c ->
                if ("*" == c.toString()) Pair(x, y)
                else null
            }
        }

    companion object {
        private val testInput = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        private val testInput1 = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*...333",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        private val testInputPart2 = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598.."
        ) to 467835

        private val testInputPart3 = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*.100",
            ".664.598.*",
            ".664.598.5"
        ) to (467835 + 500)

        private val testInputPart4 = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            "*....+.58.",
            "100.......",
            "......755.",
            "...$.*.100",
            ".664.598.*",
            ".664.598.5"
        ) to (467835 + 500 + (617 * 100))

        private val testInputPart5 = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            "*....+.58.",
            "100.......",
            "......755.",
            "10.$.*.100",
            "*664.598.*",
            ".664*598.5"
        ) to (467835 + 500 + (617 * 100))
    }
}


