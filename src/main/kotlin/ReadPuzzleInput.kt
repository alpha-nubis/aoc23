import kotlin.io.path.Path
import kotlin.io.path.readLines

interface ReadPuzzleInput {
    val day: String
}

fun ReadPuzzleInput.readPuzzleInputFromResource(): List<String> =
    javaClass.getResourceAsStream("${day}.txt")?.bufferedReader()?.readLines() ?: throw IllegalArgumentException()

fun ReadPuzzleInput.readPuzzleInput() : List<String> = Path("src/main/resources/$day.txt").readLines()
