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
package test

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

object ListSpecification extends Properties("List") {

  /** Create List from Traversable. */
  def fromTraversable[A](xs: scala.Traversable[A]): List[A] =
    if (xs.isEmpty) Nil else xs.head :: fromTraversable(xs.tail)

  /** Check if List is equals to Traversable. */
  def equalsTraversable(xs: scala.Traversable[Int], ys: List[Int]): Boolean =
    ys match {
      case y :: ys if !xs.isEmpty => y == xs.head && equalsTraversable(xs.tail, ys)
      case Nil if xs.isEmpty => true
      case _ => false
    }

  property("List.apply1") = forAll { x: Int =>
    List(x) == (x :: Nil)
  }

  property("List.applyN") = forAll { xs: scala.Traversable[Int] =>
    val ys = fromTraversable(xs)
    val zs = List(xs.toSeq: _*)
    ys == zs
  }

  property("List.map") = forAll { xs: scala.Traversable[Int] =>
    val ys = fromTraversable(xs)
    equalsTraversable(xs.map(_ + 2), ys.map(_ + 2))
  }

  property("List.foldRight") = forAll { xs: scala.Traversable[Int] =>
    val ys = fromTraversable(xs)
    ys.foldRight(0)(_ + _) == xs.foldRight(0)(_ + _)
  }
}
