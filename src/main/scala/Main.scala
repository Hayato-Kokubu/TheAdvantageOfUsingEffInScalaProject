import cats.Functor



import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object Main extends App {
//  // Future[Either[E,T]]
//
//  def process(input: Input) : Future[Either[Error, Unit]] = {
//    for{
//      r1 <- subProcess1(input)
//
//      r2 <- r1 match {
//        case Right(_) => subProcess2(input)
//        case Left(err1) => Future.successful(Left(err1))
//      }
//
//      _ <- r2 match {
//        case Right(_) => subProcess3(input)
//        case Left(err2) => Future.successful(Left(err2))
//      }
//
//    } yield Right()
//  }
//
//  def subProcess1(input: Input): Future[Either[Error, Unit]] = ???
//  def subProcess2(input: Input): Future[Either[Error, Unit]] = ???
//  def subProcess3(input: Input): Future[Either[Error, Unit]] = ???
  import FunctorOp._

  val f: Int => Int  = i => i + 1

  val n = 1
  val n2 = f(1)

  println(n2)



  val b = implicitly[Functor[Box]].map(Box(3))(_ * 2)
  println(b)

  val bt = implicitly[Functor[Bottle]].map(Bottle(3))(_ * 2)
  println(bt)


  val bbt = implicitly[Functor[Box[Bottle[_]]]]

}


case class Box[A](value: A){
}

case class Bottle[A](value: A){
}


object FunctorOp {
  implicit val boxFunctor:Functor[Box] = new Functor[Box]{
    override def map[A,B](fa: Box[A])(f: A => B): Box[B] = Box(f(fa.value))
  }
  implicit val bottleFunctor:Functor[Bottle] = new Functor[Bottle]{
    override def map[A,B](fa: Bottle[A])(f: A => B): Bottle[B] = Bottle(f(fa.value))
  }


  // Box と Bottle の合成

  implicit def BoxBottleFunctor: Functor[Box[Bottle]] = new Functor[Box[Bottle[_]]]{
    override def map[A,B](fa: Box[Bottle[A]])(f: A => B): Box[Bottle[B]] = {
      val b = implicitly[Functor[Box]]
      val bt = implicitly[Functor[Bottle]]
      
    }
  }

  val s = Seq(Seq(1,2,3), Seq(5,6)).flatten



//  implicit val ListFunctor:Functor[List] = new Functor[List]{
//    override def map[A,B](fa: List[A])(f: A => B): List[B] = Box(f(fa.head))
//  }

}
