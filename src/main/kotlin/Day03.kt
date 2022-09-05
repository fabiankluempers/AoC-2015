object Day03 : Puzzle, WithInput by Input(3) {

  override fun part1(): Any {
	var currentPos = Vec2d(0,0)
	var visitedHouses = 1
	val houses = mutableSetOf(currentPos)
	for (char in input.first()) {
	  currentPos = makeMove(char, currentPos)
	  visitedHouses += houses.visit(currentPos)
	}
	return visitedHouses
  }

  private fun MutableSet<Vec2d>.visit(pos : Vec2d) : Int {
	val isContained = contains(pos)
	add(pos)
	return if (isContained) 0 else 1
  }

  override fun part2(): Any {
	var santaPos = Vec2d(0,0)
	var roboPos = Vec2d(0,0)
	var isSanta = true
	var visitedHouses = 1
	val houses = mutableSetOf(santaPos)
	for (char in input.first()) {
	  val newPos = if (isSanta) makeMove(char, santaPos) else makeMove(char, roboPos)
	  visitedHouses += houses.visit(newPos)
	  if (isSanta) {
		santaPos = newPos
	  } else {
		roboPos = newPos
	  }
	  isSanta = !isSanta
	}
	return visitedHouses
  }

  private fun makeMove(char: Char, oldPos: Vec2d) = oldPos.let { (x, y) ->
	when (char) {
	  '^' ->  Vec2d(x, y + 1)
	  'v' ->  Vec2d(x, y - 1)
	  '>' ->  Vec2d(x + 1, y)
	  '<' ->  Vec2d(x - 1, y)
	  else -> error("invalid input")
	}
  }

}
