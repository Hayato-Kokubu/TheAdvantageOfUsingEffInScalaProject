//import cats._
//import data._
import org.atnos.eff.{Eff, Fx}

import org.atnos.eff.eval._          // use delay
import org.atnos.eff.syntax.eval._   // use runEval

import org.atnos.eff.option._        // use fromOption
import org.atnos.eff.syntax.option._ // use runOption

import org.atnos.eff.syntax.eff._    // use run





object Main extends App {
  type S = Fx.fx1[Option]

  val evalEff = delay(1 + 1).runEval.run
  println(evalEff)


  val map: Map[String, Int] =
    Map("key1" -> 10, "key2" -> 20)


  def addKeys(key1: String, key2: String): Eff[S, Int] = for {
    a <- fromOption(map.get(key1))
    b <- fromOption(map.get(key2))
  } yield a + b

  val optionEff = (addKeys("key1", "key2").runOption.run, addKeys("key1", "missing").runOption.run)
  println(optionEff)

}
