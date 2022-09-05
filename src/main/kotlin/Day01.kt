
object Day01 : Puzzle, WithInput by Input(1) {
  const val BASEMENT_LEVEL = -1

  override fun part1(): Any = input.first().fold(0) { acc, char ->
	when(char) {
	  '(' -> acc + 1
	  ')' -> acc - 1
	  else -> acc
	}
  }

  override fun part2(): Any {
	var level = 0
	var pos = 0
	for (char in input.first()) {
	  when(char) {
		'(' -> level ++
		')' -> level --
	  }
	  pos ++
	  if (level == BASEMENT_LEVEL) return pos
	}
	throw IllegalStateException("Santa never reached the basement!")
  }
}