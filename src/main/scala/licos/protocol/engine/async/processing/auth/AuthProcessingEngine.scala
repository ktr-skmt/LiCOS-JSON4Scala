package licos.protocol.engine.async.processing.auth

import com.typesafe.scalalogging.Logger
import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.auth.robot2server.AuthenticationAndAuthorizationRequestProtocol
import licos.protocol.element.auth.server2robot.{
  AuthenticationRequestResponseProtocol,
  AuthorizationRequestResponseProtocol
}
import licos.protocol.engine.async.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.async.analysis.auth.server2robot.{
  AuthenticationRequestResponseAnalysisEngine,
  AuthorizationRequestResponseAnalysisEngine
}
import licos.protocol.engine.async.processing.ProcessingEngine
import licos.protocol.engine.processing.auth.AuthBOX
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException}

import scala.concurrent.{ExecutionContext, Future}

final class AuthProcessingEngine(
    authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine],
    authenticationRequestResponseEngine:         Option[AuthenticationRequestResponseAnalysisEngine],
    authorizationRequestResponseEngine:          Option[AuthorizationRequestResponseAnalysisEngine]
) extends ProcessingEngine {

  private val logger = Logger[AuthProcessingEngine]

  @SuppressWarnings(
    Array[String](
      "org.wartremover.warts.ImplicitParameter",
      "org.wartremover.warts.Nothing",
      "org.wartremover.warts.Overloading"
    )
  )
  def process(box: AuthBOX, msg: AuthMessageProtocol)(implicit ec: ExecutionContext): Future[AuthMessageProtocol] = {

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
            Future.failed(new NoEngineException(AuthenticationAndAuthorizationRequestAnalysisEngine.name))
        }
      case protocol: AuthenticationRequestResponseProtocol =>
        authenticationRequestResponseEngine match {
          case Some(engine: AuthenticationRequestResponseAnalysisEngine) =>
            log(AuthenticationRequestResponseAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Future.failed(new NoEngineException(AuthenticationRequestResponseAnalysisEngine.name))
        }
      case protocol: AuthorizationRequestResponseProtocol =>
        authorizationRequestResponseEngine match {
          case Some(engine: AuthorizationRequestResponseAnalysisEngine) =>
            log(AuthorizationRequestResponseAnalysisEngine.name)
            engine.process(box, protocol)
          case None =>
            Future.failed(new NoEngineException(AuthorizationRequestResponseAnalysisEngine.name))
        }
      case _ =>
        Future.failed(new JSON2ProtocolException("No protocol"))
    }
  }
}
