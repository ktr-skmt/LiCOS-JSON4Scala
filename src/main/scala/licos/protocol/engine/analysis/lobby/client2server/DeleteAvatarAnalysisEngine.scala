package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.DeleteAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait DeleteAvatarAnalysisEngine {
  def process(box: LobbyBOX, deleteAvatarProtocol: DeleteAvatarProtocol): Try[LobbyMessageProtocol]
}

object DeleteAvatarAnalysisEngine {
  val name: String = "lobby.client2server.DeleteAvatar"
}
