import org.atnos.eff._ // to use |=

object Main extends App {

  // T |= R is an alias for MemberIn[T, R]
  // stating that effects of type T[_] can be injected in the effect stack R
  //                         型クラス KVStore はRに注入される？
  // It is also equivalent to MemberIn[KVStore, R]
  type _kvstore[R] = KVStore |= R // smart constructor を作成

  /** store returns nothing (i.e. Unit) */
  def store[T, R :_kvstore](key: String, value: T): Eff[R, Unit] =
    Eff.send[KVStore, R, Unit](Put(key, value))

  /** find returns a T value if the key exists */
  def find[T, R :_kvstore](key: String): Eff[R, Option[T]] =
    Eff.send[KVStore, R, Option[T]](Get(key))

  /** delete returns nothing (i.e. Unit) */
  def delete[T, R :_kvstore](key: String): Eff[R, Unit] =
    Eff.send(Delete(key))

  /** update composes get and put, and returns nothing. */
  def update[T, R :_kvstore](key: String, f: T => T): Eff[R, Unit] =
    for {
      ot <- find[T, R](key)
      _  <- ot.map(t => store[T, R](key, f(t))).getOrElse(Eff.pure(()))
    } yield ()


  def program[R :_kvstore]: Eff[R, Option[Int]] =
    for {
      _ <- store("wild-cats", 2)
      _ <- update[Int, R]("wild-cats", _ + 12)
      _ <- store("tame-cats", 5)
      n <- find[Int, R]("wild-cats")
      _ <- delete("tame-cats")
    } yield n


}

sealed trait KVStore[+A]

case class Put[T](key: String, value: T) extends KVStore[Unit]
case class Get[T](key: String) extends KVStore[Option[T]]
case class Delete(key: String) extends KVStore[Unit]
