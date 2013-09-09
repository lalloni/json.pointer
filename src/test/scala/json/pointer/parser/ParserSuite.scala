package json.pointer.parser

import org.scalatest.Matchers
import org.scalatest.WordSpec

import scalaz._
import utils.TestingHelpers._

class ParserSuite extends WordSpec with Matchers {

  "A JsonPointerParser" when {
    "given ''" should {
      "parse correctly" in {
        val r = JsonPointerParser.parse("")
        r should equal (Success(JsonPointerNode(List())))
      }
    }

    "given '//a'" should {
      "parse correctly" in {
        val r = JsonPointerParser.parse("//a")
        r should equal (Success(JsonPointerNode(List(ReferenceTokenNode(""), ReferenceTokenNode("a")))))
      }
    }

    "given '/a/b/c'" should {
      "parse correctly" in {
        val r = JsonPointerParser.parse("/a/b/c")
        r should equal (Success(JsonPointerNode(List(ReferenceTokenNode("a"), ReferenceTokenNode("b"), ReferenceTokenNode("c")))))
      }
    }

    "given '/a/~b/c'" should {
      "fail" in {
        val r = JsonPointerParser.parse("/a/~b/c")
        r should be (ofType[Failure[String, Any]])
      }
    }

  }

}
