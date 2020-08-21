package protocol.engine.async.auth

import java.net.URL
import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json2protocol.auth.Json2AuthMessageProtocol
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.engine.async.processing.auth.{AuthProcessingEngine, AuthProcessingEngineFactory}
import licos.protocol.engine.async.processing.{AuthPE, SpecificProcessingEngineFactory}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.Json
import protocol.element.AuthMessageTestProtocol
import protocol.engine.AuthExample
import protocol.engine.async.auth.analysis.robot2server.AuthenticationAndAuthorizationRequestAE
import protocol.engine.async.auth.analysis.server2robot.{
  AuthenticationRequestResponseAE,
  AuthorizationRequestResponseAE
}
import protocol.engine.auth.AuthBox
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import protocol.engine.auth.example.server2robot.{AuthenticationRequestResponse, AuthorizationRequestResponse}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.io.{Codec, Source}

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

  test("protocol.async.authProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: AuthExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            URL    = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url.toString)
      val source = Source.fromURL(url)
      val msg: String = source.getLines().mkString("\n")
      source.close()
      log.debug(msg)

      import scala.concurrent.ExecutionContext.Implicits.global

      Json2AuthMessageProtocol.toProtocolOpt(Json.parse(msg)) match {
        case Some(protocol: AuthMessageProtocol) =>
          Await.ready(
            processingEngine
              .process(new AuthBox(), protocol)
              .map { messageProtocol: AuthMessageProtocol =>
                messageProtocol match {
                  case p: AuthMessageTestProtocol =>
                    p.text shouldBe jsonType
                  case _ =>
                    fail("No AuthMessageTestProtocol")
                }
              }
              .recover {
                case error: Exception =>
                  fail(
                    List[String](
                      "No response is generated.",
                      error.getMessage,
                      msg
                    ).mkString("\n")
                  )
              },
            Duration.Inf
          )
        case _ =>
          fail("No protocol")
      }
    }
  }
}
