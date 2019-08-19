import org.atnos.eff.{Eff, Fx}

//import org.atnos.eff.eval._          // use delay
//import org.atnos.eff.syntax.eval._   // use runEval

//import org.atnos.eff.option._        // use fromOption
//import org.atnos.eff.syntax.option._ // use runOption

//import org.atnos.eff.either._        // use optionEither
//import org.atnos.eff.syntax.either._ // use runEither

//import org.atnos.eff.syntax.eff._    // use run


import org.atnos.eff.all._
import org.atnos.eff.syntax.all._


object Main extends App {
  type SO = Fx.fx1[Option]

  val evalEff = delay(1 + 1).runEval.run
  println(evalEff)


  val mapO: Map[String, Int] =
    Map("key1" -> 10, "key2" -> 20)


  def addKeys(key1: String, key2: String): Eff[SO, Int] = for {
    a <- fromOption(mapO.get(key1))
    b <- fromOption(mapO.get(key2))
  } yield a + b

  val optionEff = (addKeys("key1", "key2").runOption.run, addKeys("key1", "missing").runOption.run)

  println(optionEff)

  type SE = Fx.fx1[String Either ?] // use kind-projector

  val mapE: Map[String, Int] = Map("key1" -> 10, "key2" -> 20)

  def addKeys(key1: String, key2: String): Eff[SE, Int] = for {
    a <- optionEither(mapE.get(key1), s"'$key1' not found")
    b <- optionEither(mapE.get(key2), s"'$key2' not found")
  } yield a + b

  (addKeys("key1", "key2").runEither.run, addKeys("key1", "missing").runEither.run)



}
