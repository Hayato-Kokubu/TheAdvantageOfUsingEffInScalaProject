import cats._, data._

import org.atnos.eff.eval._        // use delay
import org.atnos.eff.syntax.eval._ // use runEval
import org.atnos.eff.syntax.eff._  // use run
object Main extends App {

  val a = delay(1 + 1).runEval.run
  println(a)


}
