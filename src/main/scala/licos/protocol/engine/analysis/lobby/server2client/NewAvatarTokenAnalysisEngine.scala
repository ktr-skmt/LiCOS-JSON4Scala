package licos.protocol.engine.analysis.lobby.server2client

import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.lobby.server2client.NewAvatarTokenProtocol
import licos.protocol.engine.analysis.lobby.LobbyMessageAnalysisEngine
import licos.protocol.engine.processing.lobby.LobbyBOX

import scala.util.Try

trait NewAvatarTokenAnalysisEngine extends LobbyMessageAnalysisEngine {
  def process(box: LobbyBOX, newAvatarTokenProtocol: NewAvatarTokenProtocol): Try[LobbyMessageProtocol]
}

object NewAvatarTokenAnalysisEngine {
  val name: String = "lobby.server2client.NewAvatarToken"
}
