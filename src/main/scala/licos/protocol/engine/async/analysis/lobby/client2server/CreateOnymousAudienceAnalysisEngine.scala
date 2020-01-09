package licos.protocol.engine.async.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateOnymousAudienceProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.concurrent.{ExecutionContext, Future}

trait CreateOnymousAudienceAnalysisEngine {
  @SuppressWarnings(Array[String]("org.wartremover.warts.ImplicitParameter"))
  def process(box: LobbyBOX, createOnymousAudienceProtocol: CreateOnymousAudienceProtocol)(
      implicit ec: ExecutionContext
  ): Future[LobbyMessageProtocol]
}

object CreateOnymousAudienceAnalysisEngine {
  val name: String = "lobby.client2server.CreateOnymousAudience"
}
