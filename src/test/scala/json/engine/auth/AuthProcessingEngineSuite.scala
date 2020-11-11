package json.engine.auth

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.element.JsonTest
import json.engine.AuthExample
import json.engine.auth.analysis.robot2server.AuthenticationAndAuthorizationRequestAE
import json.engine.auth.analysis.server2robot.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import json.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import json.engine.auth.example.server2robot.{AuthenticationRequestResponse, AuthorizationRequestResponse}
import licos.json.engine.processing.{
  AuthPE,
  AuthProcessingEngine,
  AuthProcessingEngineFactory,
  SpecificProcessingEngineFactory
}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

final class AuthProcessingEngineSuite extends AnyFunSuite with Matchers with TableDrivenPropertyChecks {

  private val fractions: TableFor1[AuthExample] =
    Table(
      "jsonExample",
      AuthenticationAndAuthorizationRequest("authenticationAndAuthorizationRequest.json"),
      AuthenticationRequestResponse("authenticationRequestResponse.json"),
      AuthorizationRequestResponse("authorizationRequestResponse.json")
    )

  private val log: Logger = Logger[AuthProcessingEngineSuite]

  private val processingEngineFactory: AuthProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(AuthPE)
    .asInstanceOf[AuthProcessingEngineFactory]
    .set(new AuthenticationAndAuthorizationRequestAE())
    .set(new AuthenticationRequestResponseAE())
    .set(new AuthorizationRequestResponseAE())

  private val processingEngine: AuthProcessingEngine = processingEngineFactory.create

  test("json.authProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: AuthExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            String = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url)
      val source = Source.fromURL(url)
      val msg: String = source.getLines().mkString("\n")
      source.close()
      log.debug(msg)
      processingEngine.process(new AuthBox(jsonType), msg) match {
        case Right(jsValue: JsValue) =>
          parseJsonTest(jsValue) match {
            case Some(json: JsonTest) =>
              json.text shouldBe jsonType
            case None =>
              fail(
                List[String](
                  "Something is wrong right after parsing.",
                  msg
                ).mkString("\n")
              )
          }

        case Left(jsValue: JsValue) =>
          fail(
            List[String](
              "No response is generated.",
              msg,
              jsValue.toString
            ).mkString("\n")
          )
      }
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err: Throwable) =>
        fail(
          List[String](
            "Parsing failed.",
            err.getMessage,
            jsValue.toString
          ).mkString("\n")
        )
        Option.empty[JsonTest]
    }
  }
}
