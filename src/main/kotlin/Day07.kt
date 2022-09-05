object Day07 : Puzzle, WithInput by Input(7) {
  override fun part1(): Any {
	TODO("Not yet implemented")
  }

  override fun part2(): Any {
	TODO("Not yet implemented")
  }
}

private enum class Operator {
  AND, OR, LSHIFT, RSHIFT
}

private const val signalPattern = "\\d+|\\w+"

private val instructionPattern =
  Regex("($signalPattern) (${Operator.values().joinToString("|")}) ($signalPattern) -> ($signalPattern)")