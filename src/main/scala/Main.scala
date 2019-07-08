import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object Main extends App {
  // Future[Either[E,T]]

  def process(input: Input) : Future[Either[Error, Unit]] = {
    for{
      r1 <- subProcess1(input)

      r2 <- r1 match {
        case Right(_) => subProcess2(input)
        case Left(err1) => Future.successful(Left(err1))
      }

      _ <- r2 match {
        case Right(_) => subProcess3(input)
        case Left(err2) => Future.successful(Left(err2))
      }

    } yield Right()
  }

  def subProcess1(input: Input): Future[Either[Error, Unit]] = ???
  def subProcess2(input: Input): Future[Either[Error, Unit]] = ???
  def subProcess3(input: Input): Future[Either[Error, Unit]] = ???

}


case class Input(){

}
