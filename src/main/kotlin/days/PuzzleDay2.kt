package days

import ReadPuzzleInput
import readPuzzleInput

class PuzzleDay2 : ReadPuzzleInput {

    override val day: String = "day02"
    private val input = readPuzzleInput()

    private val games
        get() = input
            .map { game ->
                val gameNo = game.substringBefore(":").filter { it.isDigit() }.toInt()
                val rounds =
                    game.substringAfter(":")
                        .split(";")
                        .map { it.split(",") }
                val res = rounds.map { x -> x.map { y -> y.convertToGameRound() } }
                Game(gameNo, res)
            }

    fun solvePartOne() =
        games.map { game -> Game(game.id, game.impossibleRounds()) }.filter { it.rounds.isEmpty() }.sumOf { it.id }

    fun solvePartTwo() =
        games.map { game ->
            game.rounds.flatten().groupBy({ it.second }, { it.first }).map { it.value.max() }
        }.sumOf { max ->
            var mul = 1
            max.forEach { mul *= it }
            mul
        }

}

data class Game(val id: Int, val rounds: List<List<Pair<Int, Color>>>) {
    enum class Color {
        RED,
        GREEN,
        BLUE
    }

    companion object {
        const val MAX_RED = 12
        const val MAX_GREEN = 13
        const val MAX_BLUE = 14
    }

    fun impossibleRounds() = rounds.map { round ->
        round.filter {
            when (it.second) {
                Color.RED -> it.first > MAX_RED
                Color.GREEN -> it.first > MAX_GREEN
                Color.BLUE -> it.first > MAX_BLUE
            }
        }
    }.filter { it.isNotEmpty() }
}

private fun String.convertToGameRound(): Pair<Int, Game.Color> {
    val (x, y) = this.trim().split(" ")
    val color = when (y) {
        "red" -> Game.Color.RED
        "blue" -> Game.Color.BLUE
        "green" -> Game.Color.GREEN
        else -> Game.Color.GREEN
    }
    return x.toInt() to color
}