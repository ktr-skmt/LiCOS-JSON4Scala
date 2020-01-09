package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.CreateOnymousAudienceProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait CreateOnymousAudienceAnalysisEngine {
  def process(box: LobbyBOX, createOnymousAudienceProtocol: CreateOnymousAudienceProtocol): Try[LobbyMessageProtocol]
}

object CreateOnymousAudienceAnalysisEngine {
  val name: String = "lobby.client2server.CreateOnymousAudience"
}
