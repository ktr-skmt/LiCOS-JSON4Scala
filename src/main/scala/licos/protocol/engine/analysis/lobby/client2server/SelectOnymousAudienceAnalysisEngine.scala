package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.SelectOnymousAudienceProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait SelectOnymousAudienceAnalysisEngine {
  def process(box: LobbyBOX, selectOnymousAudienceProtocol: SelectOnymousAudienceProtocol): Try[LobbyMessageProtocol]
}

object SelectOnymousAudienceAnalysisEngine {
  val name: String = "lobby.client2server.SelectOnymousAudience"
}
