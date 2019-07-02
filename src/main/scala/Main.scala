import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App{

  val fut = Future{
    Thread.sleep(3000); 21 + 21
  }
  println(fut.isCompleted)
  println(fut) // <not completed>
  println(fut.value)
  Thread.sleep(5000)
  println("--- fut is finished ---")
  println(fut.isCompleted)
  println(fut.value)
  println(fut) // Future(Success(42))
}
