package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.LobbyBOX

import scala.util.Try

trait AvatarInfoAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, avatarInfoProtocol: AvatarInfoProtocol): Try[LobbyMessageProtocol]
}

object AvatarInfoAnalysisEngine {
  val name: String = "lobby.server2client.AvatarInfo"
}
