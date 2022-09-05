object Day02 : Puzzle, WithInput by Input(2) {
  private const val LENGTH = "length"
  private const val WIDTH = "width"
  private const val HEIGHT = "height"
  private val DIMENSIONS_REGEX = Regex("(?<$LENGTH>\\d+)x(?<$WIDTH>\\d+)x(?<$HEIGHT>\\d+)")

  data class Dimensions(val length: Int, val width: Int, val height: Int) {
	val asList by lazy { listOf(length, width, height) }
  }

  override fun part1() = input
	.map { it.extractDimensions() }
	.fold(0) { acc, (length, width, height) ->
	  val sideAreas = listOf(length * width, width * height, height * length)
	  val smallestSideArea = sideAreas.minOf { it }
	  acc + sideAreas.sumOf { it * 2 } + smallestSideArea
	}

  private fun String.extractDimensions() = with(
	DIMENSIONS_REGEX.matchEntire(this)?.groups
	  ?: throw IllegalArgumentException("Input line doesn't match Regex")
  ) {
	Dimensions(
	  get(LENGTH)!!.value.toInt(),
	  get(WIDTH)!!.value.toInt(),
	  get(HEIGHT)!!.value.toInt(),
	)
  }

  override fun part2() = input
	.map { it.extractDimensions() }
	.fold(0) { acc, dimensions ->
	  val sideLengths = dimensions.asList.map { it * 2 }
	  val longestSide = sideLengths.maxOf { it }
	  val forWrapping = sideLengths.sum() - longestSide
	  val forBow = dimensions.asList.reduce(Int::times)
	  acc + forWrapping + forBow
	}
}