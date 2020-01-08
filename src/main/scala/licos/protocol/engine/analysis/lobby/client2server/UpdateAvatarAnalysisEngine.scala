package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.UpdateAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait UpdateAvatarAnalysisEngine {
  def process(box: LobbyBOX, updateAvatarProtocol: UpdateAvatarProtocol): Try[LobbyMessageProtocol]
}

object UpdateAvatarAnalysisEngine {
  val name: String = "lobby.client2server.UpdateAvatar"
}
