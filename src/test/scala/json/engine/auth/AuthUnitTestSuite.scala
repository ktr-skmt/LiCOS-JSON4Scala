package json.engine.auth

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.AuthUnitTestExample
import json.engine.auth.unitTestExample.{ProgrammingLanguage, SourceCode}
import json.parser.AuthUnitTestParser
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsValue, Json}

import scala.io.{Codec, Source}

object AuthUnitTestSuite {

  @DataPoints
  def exampleSeq: Array[AuthUnitTestExample] = Array[AuthUnitTestExample](
    ProgrammingLanguage("programmingLanguage.json"),
    SourceCode("sourceCode.json")
  )

}

@RunWith(classOf[Theories])
final class AuthUnitTestSuite extends AssertionsForJUnit with AuthUnitTestParser {

  private val log: Logger = Logger[AuthUnitTestSuite]

  @Theory
  def process(jsonExample: AuthUnitTestExample): Unit = {
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
        assert(parseSourceCode(json).nonEmpty)
      case "unitTest/ProgrammingLanguage" =>
        assert(parseProgrammingLanguage(json).nonEmpty)
      case _ =>
        fail("no json type")
    }
  }
}
