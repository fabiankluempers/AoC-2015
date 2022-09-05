import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

private val days = listOf(
  Day01,
  Day02,
  Day03,
  Day04,
  Day05,
  Day06,
)

@OptIn(ExperimentalTime::class)
fun main() {
  val day = 6
  val part1 = measureTimedValue {
    days[day - 1].part1()
  }
  println("Solution for Part 1 of day $day: $part1")
  val part2 = measureTimedValue {
    days[day - 1].part2()
  }
  println("Solution for Part 2 of day $day: $part2")
}