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

trait Iterator[+A] {
  def next: (Iterator[A], Option[A])

  def map[B](f: A => B): Iterator[B]
}

trait NonEmptyIterator[+A] extends Iterator[A] {
  def next: (Iterator[A], Option[A])
}

case object EmptyIterator extends Iterator[Nothing] {
  def next: (Iterator[Nothing], Option[Nothing]) =
    (this, None)

  def map[B](f: Nothing => B): Iterator[B] = this
}

trait Iterable[+A] {
  def iterator: Iterator[A]
}
