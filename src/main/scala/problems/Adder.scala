// See LICENSE.txt for license details.
package problems

import chisel3._

// Problem:
//
// 'out' should be the sum of 'in0' and 'in1'
// Adder width should be parametrized
//
// Implement below ----------
class Adder(val w: Int) extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(w.W))
    val in1 = Input(UInt(w.W))
    val out = Output(UInt((2*w).W))
  })

  io.out := io.in0 + io.in1
}
// Implement above ----------
