package licos.protocol.engine.processing.auth

import com.typesafe.scalalogging.Logger
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

import scala.util.{Failure, Try}

class AuthProcessingEngine(
    authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine],
    authenticationRequestResponseEngine:         Option[AuthenticationRequestResponseAnalysisEngine],
    authorizationRequestResponseEngine:          Option[AuthorizationRequestResponseAnalysisEngine]
) extends ProcessingEngine {

  private final val logger = Logger[AuthProcessingEngine]

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing", "org.wartremover.warts.Overloading"))
  def process(box: AuthBOX, msg: AuthMessageProtocol): Try[AuthMessageProtocol] = {

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    msg match {
      case protocol: AuthenticationAndAuthorizationRequestProtocol =>
        authenticationAndAuthorizationRequestEngine match {
          case Some(engine: AuthenticationAndAuthorizationRequestAnalysisEngine) =>
            log(AuthenticationAndAuthorizationRequestAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Failure(new NoEngineException(AuthenticationAndAuthorizationRequestAnalysisEngine.name))
        }
      case protocol: AuthenticationRequestResponseProtocol =>
        authenticationRequestResponseEngine match {
          case Some(engine: AuthenticationRequestResponseAnalysisEngine) =>
            log(AuthenticationRequestResponseAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Failure(new NoEngineException(AuthenticationRequestResponseAnalysisEngine.name))
        }
      case protocol: AuthorizationRequestResponseProtocol =>
        authorizationRequestResponseEngine match {
          case Some(engine: AuthorizationRequestResponseAnalysisEngine) =>
            log(AuthorizationRequestResponseAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Failure(new NoEngineException(AuthorizationRequestResponseAnalysisEngine.name))
        }
      case _ =>
        Failure(new JSON2ProtocolException("No protocol"))
    }
  }
}
