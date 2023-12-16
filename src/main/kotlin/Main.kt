import days.PuzzleDay1
import days.PuzzleDay2
import days.PuzzleDay3
import days.PuzzleDay4

fun main() {
    fun pre(day: Int, part: Int) = "Solution for Day<$day> Part<$part>: "

    val day1 = PuzzleDay1()
    day1.solvePartOne().println(pre(1, 1))
    day1.solvePartTwo().println(pre(1, 2))

    val day2 = PuzzleDay2()
    day2.solvePartOne().println(pre(2, 1))
    day2.solvePartTwo().println(pre(2, 2))

    val day3 = PuzzleDay3()
    day3.solvePartOne().println(pre(3, 1))
    day3.solvePartTwo().println(pre(3, 2))

    val day4 = PuzzleDay4()
    day4.solvePartOne().println(pre(4, 1))
    day4.solvePartTwo().println(pre(4, 2))
}