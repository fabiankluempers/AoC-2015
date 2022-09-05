import java.math.BigInteger
import java.security.MessageDigest

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
  .toString(16)
  .padStart(32, '0')

fun <T> Iterable<T>.peek(action: (T) -> Unit) : Iterable<T> {
  for (elem in this) { action(elem) }
  return this
}

data class Vec2d(val x: Int, val y: Int)