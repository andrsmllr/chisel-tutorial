// See LICENSE.txt for license details.
package problems

import chisel3._
import scala.collection.mutable.ArrayBuffer

// Problem:
//
// Implement a four-by-four multiplier using a look-up table.
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })
  val mulsValues = new ArrayBuffer[UInt]()

  // Calculate io.z = io.x * io.y by generating a table of values for mulsValues
  for (i <- 0 to math.pow(2, 4).toInt - 1) {
    for (j <- 0 to math.pow(2, 4).toInt - 1) {
      mulsValues += (i * j).asUInt
    }
  }

  // Implement below ----------
  val lut = VecInit(mulsValues) // had to peek at solution for this one
  io.z := lut((io.x << 4.U) | io.y)
  // Implement above ----------
}
