package com.jobin.study

/**
  * Created by jobinantony on 6/03/2017.
  */
final case class AUD(value: Double)

trait MyMonoid[A] {
  def zero: A
  def op(a:A,b:A):A
}
object MonoidObjects {

  implicit object AudMonoid extends MyMonoid[AUD] {
    def zero = AUD(0)

    def op(a: AUD, b: AUD): AUD = AUD(a.value + b.value)
  }

  implicit class MonoidLib[T: MyMonoid](a: T) {
    def plus(o: T): T = implicitly[MyMonoid[T]].op(a, o)
  }

}

// Test

// import MonoidObjects._

//val a = AUD(20)
//val b = AUD(30)
//val c = AUD(50)
////
//(a plus b) plus c
//a plus (b plus c)

//(AudMonoid.zero plus AUD(50)) == AUD(50)
//(AUD(50) plus AudMonoid.zero) == AUD(50)
//
//def listMonoid[A] = new MyMonoid[List[A]] {
//  def op(a1: List[A], a2: List[A]) = a1 ++ a2
//  val zero = Nil
//}

//listMonoid.op(List(a,b) , List(b,c))
//res19: List[AUD] = List(AUD(20.0), AUD(30.0), AUD(30.0), AUD(50.0))