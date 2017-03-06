/**
 * 1) type mismatch
 * 2) parameter mismatch
 * 3) enrich functionality
 */

import scala.language.implicitConversions

// case #1 - type mismatch
// copy paste line by line in scala REPL and test
final case class AUD(value: Double)
final case class USD(value: Double)
final case class INR(value: Double){
  def +(o: INR)= INR(value+o.value)
}

val a = INR(30)
val b = INR(20)
val c = AUD(20)
val d = USD(20)

a+b
// res0: INR = INR(50.0)

a+c
//<console>:14: error: type mismatch;
//  found   : AUD
//  required: INR
implicit def audToINR(a: AUD):INR = INR(a.value * 50)
a+c
// res2: INR = INR(1030.0)

implicit def usdToINR(a: USD):INR = INR(a.value * 68)
a+d

//res3: INR = INR(1390.0)


// case #2 - parameter mismatch

//def getTotalInrFromAUD(a:Double)(b: AUD) = INR(a*b.value)
//getTotalInrFromAUD(20)(AUD(48))
//getTotalInrFromAUD(20)

//<console>:19: error: missing arguments for method getTotalInrFromAUD;
//  follow this method with `_' if you want to treat it as a partially applied function
//  getTotalInrFromAUD(20)

def getTotalInrFromAUD(a:Double)(implicit b: AUD) = INR(a*b.value)
getTotalInrFromAUD(20)(AUD(48))
getTotalInrFromAUD(20)
//<console>:19: error: could not find implicit value for parameter b: AUD
//  getTotalInrFromAUD(20)
implicit val marchAUD: AUD = AUD(48)
getTotalInrFromAUD(20)
// res10: INR = INR(960.0)

// # case 3 - enrich functionality

val e = AUD(20)
c+e
//res16: INR = INR(2000.0)

implicit class utilAUD(a: AUD){
  def +(o: AUD)= AUD(a.value+o.value)
}

c+e
// res17: AUD = AUD(40.0)
