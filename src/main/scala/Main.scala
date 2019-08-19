import org.atnos.eff._
import org.atnos.eff.eval._ // to create the Eval effect

import org.atnos.eff.option._ // to use fromOption
import org.atnos.eff.syntax.option._ // to use runOption

object Main extends App {


  fromOption(Option(1)).runOption

}
