package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetSettingsProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait GetSettingsAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, getSettingsProtocol: GetSettingsProtocol): Try[LobbyMessageProtocol]
}

object GetSettingsAnalysisEngine {
  val name: String = "lobby.client2server.GetSettings"
}
