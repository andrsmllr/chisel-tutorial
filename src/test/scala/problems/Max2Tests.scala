// See LICENSE.txt for license details.
package problems

import chisel3.iotesters.PeekPokeTester
import scala.util.Random

// Problem:
//
// Implement test with PeekPokeTester
//
class Max2Tests(c: Max2) extends PeekPokeTester(c) {
  val r = scala.util.Random
  for (i <- 0 until 10) {

    // Implement below ----------

    val a = r.nextInt(256)
    val b = r.nextInt(256)
    poke(c.io.in0, a)
    poke(c.io.in1, b)
    step(1)
    expect(c.io.out, if (a > b) a else b)

    // Implement above ----------
  }
}
