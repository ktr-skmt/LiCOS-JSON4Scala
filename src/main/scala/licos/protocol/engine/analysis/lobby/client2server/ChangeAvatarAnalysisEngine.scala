package licos.protocol.engine.analysis.lobby.client2server

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.client2server.ChangeAvatarProtocol
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait ChangeAvatarAnalysisEngine {
  def process(box: LobbyBOX, changeAvatarProtocol: ChangeAvatarProtocol): Try[LobbyMessageProtocol]
}

object ChangeAvatarAnalysisEngine {
  val name: String = "lobby.client2server.ChangeAvatar"
}
