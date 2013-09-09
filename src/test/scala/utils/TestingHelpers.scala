package utils

import org.scalatest.matchers.BeMatcher
import org.scalatest.matchers.MatchResult

object TestingHelpers {

  def ofType[T: Manifest] = BeMatcher { obj: Any ⇒
    val cls = manifest[T].runtimeClass
    MatchResult(
      cls.isAssignableFrom(obj.getClass),
      obj.toString + " was not an instance of " + cls.toString,
      obj.toString + " was an instance of " + cls.toString
    )
  }

}
