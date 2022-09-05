import java.io.File

interface Puzzle {
  fun part1() : Any
  fun part2() : Any
}

interface WithInput {
  val input : List<String>
}

class Input(day : Int) : WithInput {
  override val input : List<String> by lazy {
    File("inputs/Day%02d.txt".format(day)).readLines()
  }
}