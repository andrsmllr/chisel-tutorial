package TutorialExamples

import Chisel._
import Chisel.iotesters._

//A n-bit adder with carry in and carry out
class Adder(val n:Int) extends Module {
  val io = new Bundle {
    val A    = UInt(INPUT, n)
    val B    = UInt(INPUT, n)
    val Cin  = UInt(INPUT, 1)
    val Sum  = UInt(OUTPUT, n)
    val Cout = UInt(OUTPUT, 1)
  }
  //create a vector of FullAdders
  val FAs   = Vec.fill(n)(Module(new FullAdder()).io)
  val carry = Wire(Vec(n+1, UInt(width = 1)))
  val sum   = Wire(Vec(n, Bool()))

  //first carry is the top level carry in
  carry(0) := io.Cin

  //wire up the ports of the full adders
  for (i <- 0 until n) {
    FAs(i).a := io.A(i)
    FAs(i).b := io.B(i)
    FAs(i).cin := carry(i)
    carry(i+1) := FAs(i).cout
    sum(i) := FAs(i).sum.toBool()
  }
  io.Sum := sum.toBits.toUInt()
  io.Cout := carry(n)
}

class AdderTests(c: Adder, b: Option[Backend] = None) extends ClassicTester(c, _backend=b) {
  for (t <- 0 until 4) {
    val rnd0 = rnd.nextInt(c.n)
    val rnd1 = rnd.nextInt(c.n)
    val rnd2 = rnd.nextInt(1)

    poke(c.io.A, rnd0)
    poke(c.io.B, rnd1)
    poke(c.io.Cin, rnd2)
    step(1)
    val rsum = rnd0 + rnd1 + rnd2
    expect(c.io.Sum, rsum & ~(BigInt(-1) << c.n))
    expect(c.io.Cout, rsum & (BigInt(1) << c.n))
  }
}
