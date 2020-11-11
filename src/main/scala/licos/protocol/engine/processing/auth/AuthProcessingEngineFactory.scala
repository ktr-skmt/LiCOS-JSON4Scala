package licos.protocol.engine.processing.auth

import licos.protocol.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.protocol.engine.analysis.auth.server2robot.{
  AuthenticationRequestResponseAnalysisEngine,
  AuthorizationRequestResponseAnalysisEngine
}
import licos.protocol.engine.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class AuthProcessingEngineFactory extends ProcessingEngineFactory {
  private var authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine] =
    Option.empty[AuthenticationAndAuthorizationRequestAnalysisEngine]
  private var authenticationRequestResponseEngine: Option[AuthenticationRequestResponseAnalysisEngine] =
    Option.empty[AuthenticationRequestResponseAnalysisEngine]
  private var authorizationRequestResponseEngine: Option[AuthorizationRequestResponseAnalysisEngine] =
    Option.empty[AuthorizationRequestResponseAnalysisEngine]

  override def create: AuthProcessingEngine = {
    new AuthProcessingEngine(
      authenticationAndAuthorizationRequestEngine,
      authenticationRequestResponseEngine,
      authorizationRequestResponseEngine
    )
  }

  def set(
      authenticationAndAuthorizationRequestEngine: AuthenticationAndAuthorizationRequestAnalysisEngine
  ): AuthProcessingEngineFactory = {
    this.authenticationAndAuthorizationRequestEngine = Option(authenticationAndAuthorizationRequestEngine)
    this
  }

  def set(
      authenticationRequestResponseAnalysisEngine: AuthenticationRequestResponseAnalysisEngine
  ): AuthProcessingEngineFactory = {
    this.authenticationRequestResponseEngine = Option(authenticationRequestResponseAnalysisEngine)
    this
  }

  def set(
      authorizationRequestResponseAnalysisEngine: AuthorizationRequestResponseAnalysisEngine
  ): AuthProcessingEngineFactory = {
    this.authorizationRequestResponseEngine = Option(authorizationRequestResponseAnalysisEngine)
    this
  }
}
