package licos.json.engine.processing

import licos.json.engine.analysis.auth.robot2server.AuthenticationAndAuthorizationRequestAnalysisEngine
import licos.json.engine.analysis.auth.server2robot.AuthenticationAndAuthorizationRequestResponseAnalysisEngine

@SuppressWarnings(Array[String]("org.wartremover.warts.Var", "org.wartremover.warts.Overloading"))
class AuthProcessingEngineFactory extends ProcessingEngineFactory {
  private var authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine] =
    None
  private var authenticationAndAuthorizationRequestResponseEngine
      : Option[AuthenticationAndAuthorizationRequestResponseAnalysisEngine] = None

  override def create: AuthProcessingEngine = {
    new AuthProcessingEngine(
      authenticationAndAuthorizationRequestEngine: Option[AuthenticationAndAuthorizationRequestAnalysisEngine],
      authenticationAndAuthorizationRequestResponseEngine: Option[
        AuthenticationAndAuthorizationRequestResponseAnalysisEngine
      ]
    )
  }

  def set(
      authenticationAndAuthorizationRequestEngine: AuthenticationAndAuthorizationRequestAnalysisEngine
  ): AuthProcessingEngineFactory = {
    this.authenticationAndAuthorizationRequestEngine = Option(authenticationAndAuthorizationRequestEngine)
    this
  }

  def set(
      authenticationAndAuthorizationRequestResponseEngine: AuthenticationAndAuthorizationRequestResponseAnalysisEngine
  ): AuthProcessingEngineFactory = {
    this.authenticationAndAuthorizationRequestResponseEngine = Option(
      authenticationAndAuthorizationRequestResponseEngine
    )
    this
  }
}
