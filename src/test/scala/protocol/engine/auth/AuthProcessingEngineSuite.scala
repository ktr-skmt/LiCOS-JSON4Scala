package protocol.engine.auth

import java.net.URL
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json2protocol.auth.Json2AuthMessageProtocol
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.engine.processing.auth.{AuthProcessingEngine, AuthProcessingEngineFactory}
import licos.protocol.engine.processing.{AuthPE, SpecificProcessingEngineFactory}
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.Json
import protocol.element.AuthMessageTestProtocol
import protocol.engine.AuthExample
import protocol.engine.auth.analysis.robot2server.AuthenticationAndAuthorizationRequestAE
import protocol.engine.auth.analysis.server2robot.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import protocol.engine.auth.example.server2robot.{AuthenticationRequestResponse, AuthorizationRequestResponse}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

final class AuthProcessingEngineSuite extends FunSuite with Matchers with TableDrivenPropertyChecks {

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

  test("protocol.authProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: AuthExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            URL    = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url.toString)
      val source = Source.fromURL(url)
      val msg: String = source.getLines.mkString("\n")
      source.close()
      log.debug(msg)
      Json2AuthMessageProtocol.toProtocolOpt(Json.parse(msg)) match {
        case Some(protocol: AuthMessageProtocol) =>
          processingEngine.process(new AuthBox(), protocol) match {
            case Success(protocol: AuthMessageProtocol) =>
              protocol match {
                case p: AuthMessageTestProtocol =>
                  p.text shouldBe jsonType
                case _ =>
                  fail("No AuthMessageTestProtocol")
              }
            case Failure(error: Throwable) =>
              fail(
                List[String](
                  "No response is generated.",
                  error.getMessage,
                  msg
                ).mkString("\n")
              )
          }
        case _ =>
          fail("No protocol")
      }
    }
  }

}
