package licos.protocol.engine.processing.auth

import com.typesafe.scalalogging.Logger
import licos.json.element.auth.robot2server.JsonAuthenticationAndAuthorizationRequest
import licos.json.element.auth.server2robot.{JsonAuthenticationRequestResponse, JsonAuthorizationRequestResponse}
import licos.json.flow.{AuthFlowController, FlowController}
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.element.auth.server2robot.{
  AuthenticationRequestResponseProtocol,
  AuthorizationRequestResponseProtocol
}
import licos.protocol.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.analysis.auth.server2robot.{
  AuthenticationRequestResponseAnalysisEngine,
  AuthorizationRequestResponseAnalysisEngine
}
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException, ProcessingEngine}
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Try}

class AuthProcessingEngine(
    authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine],
    authenticationRequestResponseEngine:         Option[AuthenticationRequestResponseAnalysisEngine],
    authorizationRequestResponseEngine:          Option[AuthorizationRequestResponseAnalysisEngine]
) extends ProcessingEngine {
  override protected val flowController: FlowController = new AuthFlowController()

  private final val logger = Logger[AuthProcessingEngine]

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(box: AuthBOX, msg: String): Try[AuthMessageProtocol] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Right(json: JsonAuthenticationAndAuthorizationRequest) =>
        log("JsonAuthenticationAndAuthorizationRequest")
        authenticationAndAuthorizationRequestEngine match {
          case Some(engine) =>
            log("AuthenticationAndAuthorizationRequestEngine")
            AuthenticationAndAuthorizationRequestProtocol.read(json) match {
              case Some(protocol) =>
                log("AuthenticationAndAuthorizationRequestProtocol")
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(AuthenticationAndAuthorizationRequestAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(AuthenticationAndAuthorizationRequestAnalysisEngine.name))
        }
      case Right(json: JsonAuthenticationRequestResponse) =>
        log("JsonAuthenticationRequestResponse")
        authenticationRequestResponseEngine match {
          case Some(engine) =>
            log("AuthenticationRequestResponseEngine")
            AuthenticationRequestResponseProtocol.read(json) match {
              case Some(protocol) =>
                log("AuthenticationRequestResponseProtocol")
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(AuthenticationRequestResponseAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(AuthenticationRequestResponseAnalysisEngine.name))
        }
      case Right(json: JsonAuthorizationRequestResponse) =>
        log("JsonAuthorizationRequestResponse")
        authorizationRequestResponseEngine match {
          case Some(engine) =>
            log("AuthorizationRequestResponseEngine")
            AuthorizationRequestResponseProtocol.read(json) match {
              case Some(protocol) =>
                log("AuthorizationRequestResponseProtocol")
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(AuthorizationRequestResponseAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(AuthorizationRequestResponseAnalysisEngine.name))
        }
      case _ => Failure(new NoEngineException("AnalysisEngine"))
    }
  }
}
