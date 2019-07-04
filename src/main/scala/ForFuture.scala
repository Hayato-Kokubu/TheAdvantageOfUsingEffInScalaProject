import scala.concurrent.Future
import scala.util.{Failure, Success}

object ForFuture {

  //  val fut = Future{
  //    Thread.sleep(3000); 21 + 21
  //  }

  //  println(fut.isCompleted)
  //  println(fut) // <not completed>
  //  println(fut.value)
  //  Thread.sleep(5000)
  //  println("--- fut is finished ---")
  //  println(fut.isCompleted)
  //  println(fut.value)
  //  println(fut) // Future(Success(42))


  // Try型
  //  val futFailure = Future{Thread.sleep(3000); 21/0}
  //  println(futFailure.isCompleted)
  //  println(futFailure) // <not completed>
  //  println(futFailure.value)
  //  Thread.sleep(5000)
  //  println("--- fut is finished ---")
  //  println(futFailure.isCompleted)
  //  println(futFailure.value)
  //  println(futFailure) // Future(Success(42))

  val res = Future{
    Thread. sleep(300); if( Math.random * 100 < 50) "User fond!" else UserNotFound
  }

  println(res)
  println("---------------")
  Thread.sleep(1000)
  println("operation done!")
  println("---------------")
  println(res)
  println(res.value)

  val resOn = res.onComplete{
    case Success(msg) => s"res succeeded! => $msg"
    case Failure( e ) => s"res failed!    => ${e.printStackTrace}"
  }

  //  Scala2.13 ではdeprecate
  //  res onSuccess {
  //    case msg => println(s"res succeeded! => $msg")
  //    case _ => println("wwwwww")
  //  }
  //  res onFailure {
  //    case e => println(s"res failed! => ${e.printStackTrace}")
  //    case _ => println("wwwwww")
  //  }

}
