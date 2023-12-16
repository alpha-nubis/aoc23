package days

import ReadPuzzleInput
import println
import readPuzzleInput

class PuzzleDay4 : ReadPuzzleInput {
    override val day: String = "day04"

    private val input get() = readPuzzleInput()


    fun solvePartOne() = input.getWins().sumOf { wins ->
        if (wins > 0) 1.shl(wins - 1)  // 2.0.pow(wins-1)
        else 0
    }

    private fun List<String>.getWins() =
        convertToScratchCards().map { scratchCards ->
            scratchCards.numbers.count { it in scratchCards.winnings }
        }

    private fun List<String>.convertToScratchCards() =
        map { line ->
            val (numbers, winnings) = line.substringAfter(":").split(" | ")
            val number = numbers.split(" ").filter(String::isNotEmpty).map(String::toInt)
            val winning = winnings.split(" ").filter(String::isNotEmpty).map(String::toInt)
            ScratchCards(number, winning)
        }

    fun solvePartTwo(): Int {
        val winnings = input.getWins()
        val res = MutableList(winnings.size) { 1 }
        winnings.onEachIndexed { index, s ->
            if (s == 0) {
                res[s] = 1
            } else {
                for (x in 1..res[index]) {
                    for (i in index + 1..index + s) {
                        if (i < res.size) res[i] += 1
                    }
                }
            }
        }
        return res.sum()
    }


    companion object {

        private val test = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )
    }
}


fun main() {
    PuzzleDay4().solvePartOne().println()
    PuzzleDay4().solvePartTwo().println()

}

data class ScratchCards<T>(val numbers: List<T>, val winnings: List<T>)

