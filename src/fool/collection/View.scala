/*      _______  _______  _______  _                                          *\
**     (  ____ \(  ___  )(  ___  )( \          Functional                     **
**     | (    \/| (   ) || (   ) || (          Object-Oriented                **
**     | (__    | |   | || |   | || |          Library                        **
**     |  __)   | |   | || |   | || |                                         **
**     | (      | |   | || |   | || |          A Scala Standard               **
**     | )      | (___) || (___) || (____/\    Library Replacement            **
**     |/       (_______)(_______)(_______/                                   **
\*                                                                            */

package fool
package collection

trait View[+A] extends Iterable[A]

object View {
  sealed trait Trans[+A, +B] extends View[B]

  case class Map[A,B] private (val inner: Iterable[A], val f: A => B) extends Trans[A, B] {
    def iterator = inner.iterator.map(f)
  }
}

trait FromIterable[+C[X] <: Iterable[X]] {
  def fromIterable[B](it: Iterable[B]): View[B]
}
