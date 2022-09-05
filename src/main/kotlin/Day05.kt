object Day05 : Puzzle, WithInput by Input(5) {

  override fun part1(): Any = input.count { it.isNiceP1 }

  override fun part2(): Any = input.count { it.isNiceP2 }

}

private val forbiddenWords = setOf("ab", "cd", "pq", "xy")

private val vowels = setOf('a', 'e', 'i', 'o', 'u')

private val String.isNiceP1: Boolean
  get() {
	var vowelCount = 0
	var containsTwiceInRow = false
	toList()
	  .peek { if (vowels.contains(it)) vowelCount++ }
	  .windowed(2, 1)
	  .forEach { (first, second) ->
		if (first == second) {
		  containsTwiceInRow = true
		}
		if (forbiddenWords.contains("$first$second")) {
		  return false
		}
	  }
	return vowelCount >= 3 && containsTwiceInRow
  }

private val String.isNiceP2: Boolean
  get() {
	val containsPattern = toList().windowed(3, 1).any { (first, _, second) -> first == second }
	val pairs = toList().windowed(2, 1)
	for ((first, second) in pairs) {
	  val regex = Regex("\\w*$first$second\"\\w*$first$second\"\\w*")
	  if (matches(regex)) {
		return containsPattern
	  }
	}
	return false
  }

