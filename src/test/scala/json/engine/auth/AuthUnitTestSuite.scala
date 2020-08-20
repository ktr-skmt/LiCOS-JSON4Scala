package json.engine.auth

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.AuthUnitTestExample
import json.engine.auth.unitTestExample.{ProgrammingLanguage, SourceCode}
import json.parser.AuthUnitTestParser
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

final class AuthUnitTestSuite extends FunSuite with Matchers with TableDrivenPropertyChecks with AuthUnitTestParser {

  private val fractions: TableFor1[AuthUnitTestExample] =
    Table(
      "jsonExample",
      ProgrammingLanguage("programmingLanguage.json"),
      SourceCode("sourceCode.json")
    )

  private val log: Logger = Logger[AuthUnitTestSuite]

  test("json.authUnitTestSuite") {
    forEvery(fractions) { jsonExample: AuthUnitTestExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            String = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url)
      val source = Source.fromURL(url)
      val msg: String = source.getLines.mkString("\n")
      source.close()
      log.debug(msg)
      val json: JsValue = Json.parse(msg)

      jsonType match {
        case "unitTest/SourceCode" =>
          parseSourceCode(json).nonEmpty shouldBe true
        case "unitTest/ProgrammingLanguage" =>
          parseProgrammingLanguage(json).nonEmpty shouldBe true
        case _ =>
          fail("no json type")
      }
    }
  }
}
