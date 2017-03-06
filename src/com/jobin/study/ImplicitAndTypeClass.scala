package com.jobin.study

/**
  * Created by jobinantony on 6/03/2017.
  */
final case class AUD(value: Double)
final case class USD(value: Double)
final case class INR(value: Double)

trait CanConvert[T] {
  def getINR(x: T): INR
}

object CurrencyAddon {
  implicit object AudCanConvert  extends CanConvert[AUD]{
    def getINR(x: AUD):INR = INR(x.value * 48)
  }

  implicit object UsdCanConvert extends CanConvert[USD]{
    def getINR(x: USD):INR = INR(x.value * 68)
  }

  implicit class CurrencyUtil[T: CanConvert](x: T)  {
    def getINR: INR = {
      implicitly[CanConvert[T]].getINR(x)
    }
  }
}

// Test

//import CurrencyAddon._
//AUD(20).getINR
//val a =INR(20)
//val b =AUD(20)
//val c =USD(20)
//val d =INR(20)

// Explanation

//final case class AUD(value: Double)
//final case class USD(value: Double)
//final case class INR(value: Double)
//
//
//// No Type class
//object AudCanConvert {
//  def getINR(x: AUD):INR = INR(x.value * 48)
//}
//
//object UsdCanConvert {
//  def getINR(x: USD):INR = INR(x.value * 68)
//}
//
//AudCanConvert.getINR(AUD(30))
//UsdCanConvert.getINR(USD(30))
//
//// with Type class
//
//trait CanConvert[T] {
//  def getINR(x: T): INR
//}
//
//object AudCanConvert  extends CanConvert[AUD]{
//  def getINR(x: AUD):INR = INR(x.value * 48)
//}
//
//object UsdCanConvert extends CanConvert[USD]{
//  def getINR(x: USD):INR = INR(x.value * 68)
//}
//
//
//object CurrencyUtil {
//  def getINR[T](x: T, conversion: CanConvert[T]) = {
//    conversion.getINR(x)
//  }
//}
//
//CurrencyUtil.getINR(AUD(3),AudCanConvert)
//CurrencyUtil.getINR(USD(3),UsdCanConvert)
//
//object SBICanConvertAUD  extends CanConvert[AUD]{
//  def getINR(x: AUD):INR = INR(x.value * 49)
//}
//
//CurrencyUtil.getINR(AUD(3),SBICanConvertAUD)
//
//object CurrencyUtil {
//  def getINR[T](x: T)(implicit conversion: CanConvert[T]) = {
//    conversion.getINR(x)
//  }
//}
//
//CurrencyUtil.getINR(AUD(3))
//
//object CurrencyAddon {
//  implicit object AudCanConvert  extends CanConvert[AUD]{
//    def getINR(x: AUD):INR = INR(x.value * 48)
//  }
//
//  implicit object UsdCanConvert extends CanConvert[USD]{
//    def getINR(x: USD):INR = INR(x.value * 68)
//  }
//}
//
//import CurrencyAddon._
//CurrencyUtil.getINR(AUD(3))
//
//AUD(3).getINR
//
//object CurrencyAddon {
//  implicit object AudCanConvert  extends CanConvert[AUD]{
//    def getINR(x: AUD):INR = INR(x.value * 48)
//  }
//
//  implicit object UsdCanConvert extends CanConvert[USD]{
//    def getINR(x: USD):INR = INR(x.value * 68)
//  }
//
//  implicit class CurrencyUtil[T](x: T) {
//    def getINR(implicit conversion: CanConvert[T]) = {
//      conversion.getINR(x)
//    }
//  }
//}
//
//AUD(3).getINR
//AUD(3).getINR(SBICanConvertAUD)
//:-)
//
//final case class BHD(value: Double)
//BHD(3).getINR
//
//implicit object BhdCanConvert extends CanConvert[BHD]{
//  def getINR(x: BHD):INR = INR(x.value * 168)
//}
//
//BHD(3).getINR