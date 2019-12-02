package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.GetAvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait GetAvatarInfoAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, getAvatarInfoProtocol: GetAvatarInfoProtocol): Try[LobbyMessageProtocol]
}

object GetAvatarInfoAnalysisEngine {
  val name: String = "lobby.client2server."
}
