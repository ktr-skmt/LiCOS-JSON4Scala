package protocol.engine.auth

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import licos.json.parser.AuthParser
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.engine.processing.auth.{AuthProcessingEngine, AuthProcessingEngineFactory}
import licos.protocol.engine.processing.{AuthPE, SpecificProcessingEngineFactory}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import protocol.element.AuthMessageTestProtocol
import protocol.engine.AuthExample
import protocol.engine.auth.analysis.robot2server.AuthenticationAndAuthorizationRequestAE
import protocol.engine.auth.analysis.server2robot.{AuthenticationRequestResponseAE, AuthorizationRequestResponseAE}
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest
import protocol.engine.auth.example.server2robot.{AuthenticationRequestResponse, AuthorizationRequestResponse}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object AuthProcessingEngineSuite {
  @DataPoints
  def exampleSeq: Array[AuthExample] = Array[AuthExample](
    AuthenticationAndAuthorizationRequest("authenticationAndAuthorizationRequest.json"),
    AuthenticationRequestResponse("authenticationRequestResponse.json"),
    AuthorizationRequestResponse("authorizationRequestResponse.json")
  )
}

@RunWith(classOf[Theories])
class AuthProcessingEngineSuite extends AssertionsForJUnit with AuthParser {

  private final val log: Logger = Logger[AuthProcessingEngineSuite]

  private val processingEngineFactory: AuthProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(AuthPE)
    .asInstanceOf[AuthProcessingEngineFactory]
    .set(new AuthenticationAndAuthorizationRequestAE())
    .set(new AuthenticationRequestResponseAE())
    .set(new AuthorizationRequestResponseAE())

  private val processingEngine: AuthProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: AuthExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new AuthBox(), msg) match {
      case Success(protocol: AuthMessageProtocol) =>
        protocol match {
          case p: AuthMessageTestProtocol =>
            assert(p.text == jsonType)
          case _ =>
            fail(
              Seq[String](
                "No AuthMessageTestProtocol"
              ).mkString("\n")
            )
        }
      case Failure(error: Throwable) =>
        fail(
          Seq[String](
            "No response is generated.",
            error.getMessage,
            msg
          ).mkString("\n")
        )
    }
  }
}
