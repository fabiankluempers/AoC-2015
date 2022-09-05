object Day06 : Puzzle, WithInput by Input(6) {
  private val instructions: List<Instruction> = input.map {
	with(instructionPattern.matchEntire(it)?.groups ?: error("$it doesn't match instruction pattern")) {
	  Instruction(
		Command.fromString(get(COMMAND)!!.value),
		get(FIRST_COORDINATE)!!.value.toVec2d(),
		get(SECOND_COORDINATE)!!.value.toVec2d(),
	  )
	}
  }

  override fun part1(): Any {
	val lightArray = Array(1000) { BooleanArray(1000) { false } }
	for (instruction in instructions) {
	  instruction.execute(lightArray)
	}
	return lightArray.sumOf { array -> array.count { it } }
  }

  override fun part2(): Any {
	val lightArray = Array(1000) { IntArray(1000) { 0 } }
	for (instruction in instructions) {
	  instruction.execute(lightArray)
	}
	return lightArray.sumOf { it.sum() }
  }
}

private const val COMMAND = 1

private const val FIRST_COORDINATE = 2

private const val SECOND_COORDINATE = 3

private val instructionPattern = Regex("(turn on|turn off|toggle) (\\d+,\\d+) through (\\d+,\\d+)")

private enum class Command {
  TOGGLE, ON, OFF;

  companion object {
	fun fromString(string: String) = when (string) {
	  "turn on"  -> ON
	  "turn off" -> OFF
	  "toggle"   -> TOGGLE
	  else       -> error("Can't create command from $string")
	}
  }
}

private fun String.toVec2d(): Vec2d {
  val (x, y) = split(',').map { it.toInt() }
  return Vec2d(x, y)
}

private data class Instruction(val command: Command, val firstCoordinate: Vec2d, val secondCoordinate: Vec2d) {
  fun execute(array: Array<BooleanArray>) {
	for (x in firstCoordinate.x..secondCoordinate.x) {
	  for (y in firstCoordinate.y..secondCoordinate.y) {
		array[x][y] = when (command) {
		  Command.ON     -> true
		  Command.OFF    -> false
		  Command.TOGGLE -> !array[x][y]
		}
	  }
	}
  }

  fun execute(array: Array<IntArray>) {
	for (x in firstCoordinate.x..secondCoordinate.x) {
	  for (y in firstCoordinate.y..secondCoordinate.y) {
		when (command) {
		  Command.ON     -> array[x][y]++
		  Command.OFF    -> array[x][y] = (array[x][y] - 1).coerceAtLeast(0)
		  Command.TOGGLE -> array[x][y] += 2
		}
	  }
	}
  }
}