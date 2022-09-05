import java.util.stream.Stream

object Day04 : Puzzle {
  const val input = "ckczppom"

  override fun part1(): Any = findSolution("00000")


  override fun part2(): Any = findSolution("000000")

  private fun findSolution(prefix: String) = Stream.iterate(0) { it + 1 }
	.parallel()
	.map {"$input$it".md5() to it}
	.filter{ (hash) -> hash.startsWith(prefix) }
	.findFirst()
	.get()
}

