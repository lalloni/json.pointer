package json.pointer.parser

import org.parboiled.errors.ErrorUtils
import org.parboiled.scala._

import scalaz.syntax.validation._
import scalaz.Validation

sealed abstract class AstNode
case class ReferenceTokenNode(string: String) extends AstNode
case class JsonPointerNode(tokens: List[ReferenceTokenNode]) extends AstNode

object JsonPointerParser extends Parser {

  private def JsonPointer = rule { zeroOrMore("/" ~ ReferenceToken) ~~> JsonPointerNode ~ EOI }
  private def ReferenceToken = rule { zeroOrMore(Unescaped | Escaped) ~> ReferenceTokenNode }
  private def Unescaped = rule { "\u0000" - "\u002E" | "\u0030" - "\u007D" | "\u007F" - "\uFFFE" }
  private def Escaped = rule { "~" ~ ("0" | "1") }

  def parse(script: String): Validation[String, JsonPointerNode] = {
    val parsing = ReportingParseRunner(JsonPointer).run(script)
    parsing.result match {
      case Some(result) ⇒ result.success
      case None         ⇒ ErrorUtils.printParseErrors(parsing).fail
    }
  }

}
