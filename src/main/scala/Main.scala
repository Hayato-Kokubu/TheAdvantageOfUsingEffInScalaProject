import org.atnos.eff.{Eff, Fx}


import org.atnos.eff.either._
import org.atnos.eff.syntax.either._

import org.atnos.eff.syntax.eff._

import org.atnos.eff.all._


object Main extends App {

  type SE = Fx.fx1[String Either ?] // use kind-projector

  val mapE: Map[String, Int] = Map("key1" -> 10, "key2" -> 20)

  // intelliJ では解決してくれないけど動く
  def addKeys(key1: String, key2: String): Eff[SE, Int] = for {
    a <- optionEither(mapE.get(key1), s"'$key1' not found")
    b <- optionEither(mapE.get(key2), s"'$key2' not found")
  } yield a + b

  val c = (addKeys("key1", "key2").runEither.run, addKeys("key1", "missing").runEither.run)
  println(c)



  type E = Fx.fx1[TooBig Either ?]

  val i = 7

  val value: Eff[E, Int] =
    if (i > 5) left[E, TooBig, Int](TooBig(i))
    else       right[E, TooBig, Int](i)

  val action: Eff[E, Int] = catchLeft[E, TooBig, Int](value) { case TooBig(k) =>
    if (k < 10) right[E, TooBig, Int](k)
    else        left[E, TooBig, Int](TooBig(k))
  }

  action.runEither.run ==== Right(7)
}

case class TooBig(value: Int)