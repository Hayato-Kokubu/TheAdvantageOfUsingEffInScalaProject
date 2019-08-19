import cats._, data._
import org.atnos.eff._

import org.atnos.eff.all._
import org.atnos.eff.syntax.all._


object Main extends App {

  type ReaderInt[A] = Reader[Int, A]
  type WriterString[A] = Writer[String, A]

  type _readerInt[R]    = ReaderInt |= R     // make Member
  type _writerString[R] = WriterString |= R  // make Member

  type Stack = Fx.fx3[WriterString, ReaderInt, Eval]



  fromOption(Option(1)).runOption

  // EvalType にいる
  def program[R :_readerInt :_writerString :_eval]: Eff[R, Int] = for {
    // get the configuration
    n <- ask[R, Int]

    // log the current configuration value
    _ <- tell("the required power is "+n)

    // compute the nth power of 2
    a <- delay(math.pow(2, n.toDouble).toInt)

    // log the result
    _ <- tell("the result is "+a)
  } yield a

  // run the action with all the interpreters
  // each interpreter running one effect
  val a = program[Stack].runReader(6).runWriter.runEval.run
  // intelliJ ではimplicit値が解決されていないが、動く。
  // 特に、a の型は、intelliJ では読めない。。。

  println(a)
  // (64,List(the required power is 6, the result is 64))

}
